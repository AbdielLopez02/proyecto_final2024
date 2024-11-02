/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Compra;
import modelo.Compra_detalle;
import modelo.Venta_detalle;
import modelo.conexion;

/**
 *
 * @author Kenneth
 */
public class sr_compras extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idCompraStr = request.getParameter("id_compra");
        String modalType = request.getParameter("modal_type");

        if ("modal_venta".equals(modalType) && idCompraStr != null) {
            try {
                int idCompra = Integer.parseInt(idCompraStr);
                Compra_detalle detalleCompra = new Compra_detalle();
                List<Compra_detalle> detallesCompra = detalleCompra.obtenerDetallesPorIdCompra(idCompra);

                Gson gson = new Gson();
                String json = gson.toJson(detallesCompra);

                response.setContentType("application/json");
                response.getWriter().write(json);
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de venta no válido.");
                return;
            }
        }
  if ("ver_detalle".equals(modalType) && idCompraStr != null) {
            try {
                int idCompra = Integer.parseInt(idCompraStr);
                Compra_detalle detalleCompra = new Compra_detalle();
                List<Compra_detalle> detallesCompra = detalleCompra.obtenerDetallesPorIdCompra(idCompra);

                Gson gson = new Gson();
                String json = gson.toJson(detallesCompra);

                response.setContentType("application/json");
                response.getWriter().write(json);
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de venta no válido.");
                return;
            }
        }
 
if ("eliminar_detalle".equals(modalType)) {
    int idCompraDetalles = Integer.parseInt(request.getParameter("id_compra_detalle"));
    
    // Crear instancia de la clase Venta_detalle
    Compra_detalle compraDetalle = new Compra_detalle();
    int resultado = compraDetalle.eliminarPorIdDetalle(idCompraDetalles);
    
    if (resultado > 0) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Eliminación exitosa");
    } else {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Error al eliminar el detalle de la venta");
    }
}


if ("actualizar_detalle".equals(modalType)) {
    int idCompra = Integer.parseInt(request.getParameter("id_compra"));
    String[] productos = request.getParameterValues("drop_productos");
    String[] cantidades = request.getParameterValues("txt_cantidad");
    String[] precios = request.getParameterValues("txt_precio_costo_unitario");

    Compra_detalle compraDetalle = new Compra_detalle();

    // Primero, eliminar los detalles existentes, si es necesario
    // Si quieres reemplazar los detalles existentes, descomenta la siguiente línea:
    // ventaDetalle.eliminarDetallesPorIdVenta(idVenta);

    // Agregar cada producto a la base de datos
    for (int i = 0; i < productos.length; i++) {
        // Asegúrate de que la cantidad y el precio son válidos antes de convertirlos
        int cantidad = Integer.parseInt(cantidades[i]);
        double precio = Double.parseDouble(precios[i]);

        // Lógica para agregar nuevos detalles
        compraDetalle.agregarDetalle(idCompra, Integer.parseInt(productos[i]), cantidad, precio);
    }

    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("Detalles actualizados exitosamente");
}






  
  
  
  
  
        try {
            int id_compra = 0;
            int id_compra_detalle = 0;

            String btnAction = request.getParameter("btn_crear");
            if (request.getParameter("btn_actualizar") != null) {
                btnAction = request.getParameter("btn_actualizar");
            } else if (request.getParameter("btn_eliminar") != null) {
                btnAction = request.getParameter("btn_eliminar");
            }
            
            
            
            
if (request.getParameter("btn_actualizar") != null) {
    btnAction = request.getParameter("btn_actualizar");
} else if (request.getParameter("btn_eliminar") != null) {
    btnAction = request.getParameter("btn_eliminar");
} 
            
            
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            
                Compra_detalle compraDetalle = new Compra_detalle(
                id_compra_detalle,
                id_compra,                      
                Integer.parseInt(request.getParameter("drop_productos")),
                Integer.parseInt(request.getParameter("txt_cantidad")),
                Double.parseDouble(request.getParameter("txt_precio_costo_unitario"))             
            );

                Compra compra = new Compra(
                request.getParameter("txt_fecha_orden"),
                request.getParameter("txt_fecha_ingreso"),
                id_compra, 
                Integer.parseInt(request.getParameter("drop_proveedor")),
                Integer.parseInt(request.getParameter("txt_no_orden"))
            );
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
                
                
                
                
                
                
                
      if ("crear".equals(btnAction)) {
    String[] productos = request.getParameterValues("drop_productos");
    String[] cantidades = request.getParameterValues("txt_cantidad");
    String[] precios = request.getParameterValues("txt_precio_costo_unitario");

    // Crear la compra y obtener el ID de la compra creada
    int idCompraCreada = compra.crear();
    if (idCompraCreada > 0) {
        // Crear los detalles de la compra
        for (int i = 0; i < productos.length; i++) {
            int idProducto = Integer.parseInt(productos[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            double precioCostoNuevo = Double.parseDouble(precios[i]);

            // Crear el detalle de la compra
            Compra_detalle comprasDetalle = new Compra_detalle(
                productos[i], 
                0, 
                idCompraCreada, 
                idProducto, 
                cantidad, 
                precioCostoNuevo
            );
            comprasDetalle.crear();

            // Actualizar saldo, precio costo y precio venta
            actualizarSaldoProducto(idProducto, cantidad);
            actualizarPrecioCostoProducto(idProducto, precioCostoNuevo);
            actualizarPrecioVentaProducto(idProducto, precioCostoNuevo * 1.25);
        }
        response.sendRedirect("vista/compras/compras.jsp?status=success&action=crear");
    } else {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('Error al crear la compra.');</script>");
        out.println("<script>window.location.href='vista/compras/compras.jsp';</script>");
    }
}
           
           
      else if ("actualizar".equals(btnAction)) {
    try {
        // Obtener los parámetros de la solicitud
        String strIdCompra = request.getParameter("id_compra");
        id_compra = Integer.parseInt(strIdCompra);

        // Obtener detalles del producto
        String[] productos = request.getParameterValues("drop_productos");
        String[] cantidades = request.getParameterValues("txt_cantidad");
        String[] precios = request.getParameterValues("txt_precio_costo_unitario");

        String strFechaOrden = request.getParameter("txt_fecha_orden");
        String strFechaIngreso = request.getParameter("txt_fecha_ingreso");
        int proveedorId = Integer.parseInt(request.getParameter("drop_proveedor"));
        int No_orden = Integer.parseInt(request.getParameter("txt_no_orden"));

        // Crear objeto Compra para actualizar
        Compra compraActualizada = new Compra(strFechaOrden, strFechaIngreso, id_compra, proveedorId, No_orden);
        compraActualizada.actualizar(id_compra); // Actualizar la compra

        // Eliminar detalles existentes
        Compra_detalle.eliminarPorCompra(id_compra); 

        // Agregar nuevos detalles a la compra
        for (int i = 0; i < productos.length; i++) {
            int id_producto = Integer.parseInt(productos[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            double precio_costo_unitario = Double.parseDouble(precios[i]);

            // Crear y guardar el nuevo detalle de compra
            String productoNombre = "nombre_producto"; // Establecer el nombre del producto según sea necesario
            Compra_detalle nuevoDetalle = new Compra_detalle(productoNombre, 0, id_compra, id_producto, cantidad, precio_costo_unitario);
            nuevoDetalle.crear(); // Guardar el nuevo detalle en la base de datos

            // Actualizar saldo, precio de costo y precio de venta
            actualizarSaldoProducto(id_producto, cantidad);
            actualizarPrecioCostoProducto(id_producto, precio_costo_unitario);
            double nuevoPrecioVenta = precio_costo_unitario * 1.25; // Calcular precio de venta con un 25% adicional
            actualizarPrecioVentaProducto(id_producto, nuevoPrecioVenta);
        }

        // Redirigir según el resultado de la actualización
        response.sendRedirect("vista/compras/compras.jsp?status=success&action=actualizar");
        
    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.sendRedirect("vista/compras/compras.jsp?status=error&message=Datos inválidos");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("vista/compras/compras.jsp?status=error&message=Error inesperado");
    }
}









            else if ("eliminar".equals(btnAction)) {
                id_compra = Integer.parseInt(request.getParameter("id_compra")); 
                System.out.println("Intentando eliminar compra con ID: " + id_compra); 

                Compra_detalle detalleEliminar = new Compra_detalle(); 
                int detallesEliminados = detalleEliminar.eliminarPorIdCompra(id_compra);
                System.out.println("Eliminación de detalles para la venta ID: " + id_compra + " - Resultados: " + detallesEliminados);

                if (detallesEliminados > 0) {
                    if (compra.eliminar(id_compra) > 0) {
                        response.sendRedirect("vista/compras/compras.jsp?status=success&action=eliminar");
                    } else {
                        System.out.println("Error al eliminar la venta con ID: " + id_compra);
                        response.sendRedirect("vista/compras/compras.jsp?status=error&action=eliminar");
                    }
                } else {
                    System.out.println("No se eliminaron detalles para la venta ID: " + id_compra);
                    response.sendRedirect("vista/compras/compras.jsp?status=error&action=detalles_no_eliminados");
                }
            }



               

        } catch (NumberFormatException e) {
            response.sendRedirect("vista/compras/compras.jsp?status=error&action=invalid&message=Datos%20inválidos");
        } catch (Exception e) {
            response.sendRedirect("vista/compras/compras.jsp?status=error&action=exception&message=" + e.getMessage());
        }
    }

    
    
    public List<Compra_detalle> obtenerDetallesPorIdCompra(int idCompra) {
        List<Compra_detalle> detalles = new ArrayList<>();
        try {
            conexion cn = new conexion();
            cn.abrir_conexion();

            String query = "SELECT * FROM compras_detalle WHERE id_compra_detalle = ?";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, idCompra);

            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Compra_detalle detalle = new Compra_detalle(
                consulta.getString("producto"), // Añade el nombre del producto aquí
                consulta.getInt("id_compra_detalle"),
                consulta.getInt("id_compra"),
                consulta.getInt("id_producto"),
                consulta.getInt("cantidad"),
                consulta.getDouble("precio_costo_unitario")
            );
                detalles.add(detalle);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener detalles: " + ex.getMessage());
        }
        return detalles;
    }
    
    
    
    
    
    
    
    
    private void actualizarSaldoProducto(int idProducto, int cantidad) {
    conexion cn = new conexion();
    cn.abrir_conexion();

    try {
        String query = "UPDATE productos SET existencia = existencia + ? WHERE id_producto = ?";
        PreparedStatement stmt = cn.conexionDB.prepareStatement(query);
        stmt.setInt(1, cantidad);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el saldo del producto: " + e.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
}

// Método para actualizar el precio de costo del producto
private void actualizarPrecioCostoProducto(int idProducto, double nuevoPrecioCosto) {
    conexion cn = new conexion();
    cn.abrir_conexion();

    try {
        String query = "UPDATE productos SET precio_costo = ? WHERE id_producto = ?";
        PreparedStatement stmt = cn.conexionDB.prepareStatement(query);
        stmt.setDouble(1, nuevoPrecioCosto);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el precio de costo del producto: " + e.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
}

// Método para actualizar el precio de venta del producto
private void actualizarPrecioVentaProducto(int idProducto, double nuevoPrecioVenta) {
    conexion cn = new conexion();
    cn.abrir_conexion();

    try {
        String query = "UPDATE productos SET precio_venta = ? WHERE id_producto = ?";
        PreparedStatement stmt = cn.conexionDB.prepareStatement(query);
        stmt.setDouble(1, nuevoPrecioVenta);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el precio de venta del producto: " + e.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
}
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
