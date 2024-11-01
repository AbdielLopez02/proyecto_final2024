package controlador;

import java.io.IOException;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Venta;
import modelo.Venta_detalle;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import modelo.Producto;
import modelo.conexion;

public class sr_ventas extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idVentaStr = request.getParameter("id_venta");
        String modalType = request.getParameter("modal_type");

        if ("modal_venta".equals(modalType) && idVentaStr != null) {
            try {
                int idVenta = Integer.parseInt(idVentaStr);
                Venta_detalle detalleVenta = new Venta_detalle();
                List<Venta_detalle> detallesVenta = detalleVenta.obtenerDetallesPorIdVenta(idVenta);

                Gson gson = new Gson();
                String json = gson.toJson(detallesVenta);

                response.setContentType("application/json");
                response.getWriter().write(json);
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de venta no válido.");
                return;
            }
        }
 if ("ver_detalle".equals(modalType) && idVentaStr != null) {
        try {
            int idVenta = Integer.parseInt(idVentaStr);
            Venta_detalle detalleVenta = new Venta_detalle();
            List<Venta_detalle> detallesVenta = detalleVenta.obtenerDetallesPorIdVenta(idVenta);

            Gson gson = new Gson();
            String json = gson.toJson(detallesVenta);

            response.setContentType("application/json");
            response.getWriter().write(json);
            return; 
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de venta no válido.");
            return;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los detalles de la venta.");
            return;
        }
    }
 
if ("eliminar_detalle".equals(modalType)) {
    int idVentaDetalles = Integer.parseInt(request.getParameter("id_venta_detalle"));
    
    // Crear instancia de la clase Venta_detalle
    Venta_detalle ventaDetalle = new Venta_detalle();
    int resultado = ventaDetalle.eliminarPorIdDetalle(idVentaDetalles);
    
    if (resultado > 0) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Eliminación exitosa");
    } else {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Error al eliminar el detalle de la venta");
    }
}


if ("actualizar_detalle".equals(modalType)) {
    int idVenta = Integer.parseInt(request.getParameter("id_venta"));
    String[] productos = request.getParameterValues("drop_productos");
    String[] cantidades = request.getParameterValues("txt_cantidad");
    String[] precios = request.getParameterValues("txt_precio_unitario");

    // Crear instancia de la clase Venta_detalle
    Venta_detalle ventaDetalle = new Venta_detalle();

    // Primero, eliminar los detalles existentes, si es necesario
    // Si quieres reemplazar los detalles existentes, descomenta la siguiente línea:
    // ventaDetalle.eliminarDetallesPorIdVenta(idVenta);

    // Agregar cada producto a la base de datos
    for (int i = 0; i < productos.length; i++) {
        // Asegúrate de que la cantidad y el precio son válidos antes de convertirlos
        int cantidad = Integer.parseInt(cantidades[i]);
        double precio = Double.parseDouble(precios[i]);

        // Lógica para agregar nuevos detalles
        ventaDetalle.agregarDetalle(idVenta, Integer.parseInt(productos[i]), cantidad, precio);
    }

    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("Detalles actualizados exitosamente");
}






  
  
  
  
  
        try {
            int id_venta = 0;
            int id_venta_detalle = 0;

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
} else if ("verificar_stock".equals(request.getParameter("btn_verificar_stock"))) {
    try {
        int id_producto = Integer.parseInt(request.getParameter("drop_productos"));
        int cantidadSolicitada = Integer.parseInt(request.getParameter("txt_cantidad"));

        if (verificarStock(id_producto, cantidadSolicitada)) {
            if (actualizarStockProducto(id_producto, cantidadSolicitada)) {
                response.getWriter().write("Stock actualizado correctamente.");
            } else {
                response.getWriter().write("Error al actualizar el stock.");
            }
        } else {
            response.getWriter().write("Stock insuficiente para el producto con ID: " + id_producto);
        }
        return; 
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto o cantidad no válidos.");
        return;
    } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al verificar el stock.");
        return;
    }
}
            
            
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            
            Venta_detalle detalle = new Venta_detalle(
                id_venta_detalle,
                id_venta,                      
                Integer.parseInt(request.getParameter("drop_productos")),
                Integer.parseInt(request.getParameter("txt_cantidad")),
                Double.parseDouble(request.getParameter("txt_precio_unitario"))             
            );

                Venta venta = new Venta(
                request.getParameter("txt_serie"),
                request.getParameter("txt_fecha_factura"),
                request.getParameter("txt_fecha_ingreso"),
                id_venta, 
                Integer.parseInt(request.getParameter("drop_clientes")),
                Integer.parseInt(request.getParameter("drop_empleados")),
                Integer.parseInt(request.getParameter("txt_no_factura"))
            );
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
                
                
                
                
                
                
                
        if ("crear".equals(btnAction)) {
         String[] productos = request.getParameterValues("drop_productos");
         String[] cantidades = request.getParameterValues("txt_cantidad");
         String[] precios = request.getParameterValues("txt_precio_unitario");

         boolean stockSuficiente = true;
         for (int i = 0; i < productos.length; i++) {
             int id_producto = Integer.parseInt(productos[i]);
             int cantidad = Integer.parseInt(cantidades[i]);

             if (!verificarStock(id_producto, cantidad)) {
                 stockSuficiente = false;
                 break; //
             }
         }

        if (stockSuficiente) {
            int idVentaCreada = venta.crear();
            if (idVentaCreada > 0) {
                for (int i = 0; i < productos.length; i++) {
                    Venta_detalle detalleCreado = new Venta_detalle(
                        productos[i], 
                        0, 
                        idVentaCreada, 
                        Integer.parseInt(productos[i]), 
                        Integer.parseInt(cantidades[i]), 
                        Double.parseDouble(precios[i]) 
                    );
                    detalleCreado.crear();

                    actualizarStockProducto(Integer.parseInt(productos[i]), Integer.parseInt(cantidades[i]));
                }
                response.sendRedirect("vista/ventas/ventas.jsp?status=success&action=crear");
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('Error al crear la venta.');</script>");
                out.println("<script>window.location.href='vista/ventas/ventas.jsp';</script>");
            }
        } else {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('Error: Stock insuficiente para uno o más productos.');</script>");
        out.println("<script>window.location.href='vista/ventas/ventas.jsp';</script>");
    }
}
   
           
           
      else if ("actualizar".equals(btnAction)) {
    try {
        // Obtener los parámetros de la solicitud
        String strIdVenta = request.getParameter("id_venta");
        id_venta = Integer.parseInt(strIdVenta);

        // Obtener detalles del producto
        String[] productos = request.getParameterValues("drop_productos");
        String[] cantidades = request.getParameterValues("txt_cantidad");
        String[] precios = request.getParameterValues("txt_precio_unitario");

        // Crear el objeto Venta
        String strSerie = request.getParameter("txt_serie");
        String strFechaFactura = request.getParameter("txt_fecha_factura");
        String strFechaIngreso = request.getParameter("txt_fecha_ingreso");
        int clienteId = Integer.parseInt(request.getParameter("drop_clientes"));
        int empleadoId = Integer.parseInt(request.getParameter("drop_empleados"));
        int noFactura = Integer.parseInt(request.getParameter("txt_no_factura"));

        Venta ventaActualizada = new Venta(
            strSerie, strFechaFactura, strFechaIngreso, id_venta, clienteId, empleadoId, noFactura
        );

        ventaActualizada.actualizar(id_venta);

        Venta_detalle.eliminarPorVenta(id_venta); 

        // Agregar nuevos detalles a la venta
        for (int i = 0; i < productos.length; i++) {
            int id_producto = Integer.parseInt(productos[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            double precio_unitario = Double.parseDouble(precios[i]);

            // Verificar stock
            if (!verificarStock(id_producto, cantidad)) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('Error: Stock insuficiente para el producto " + id_producto + ".');</script>");
                out.println("<script>window.location.href='vista/ventas/ventas.jsp';</script>");
                return;
            }

            // Crear y guardar el nuevo detalle de venta
            String productoNombre = "nombre_producto"; // Establecer el nombre del producto según sea necesario
            Venta_detalle nuevoDetalle = new Venta_detalle(productoNombre, 0, id_venta, id_producto, cantidad, precio_unitario);
            nuevoDetalle.crear(); // Guardar el nuevo detalle en la base de datos
        }

        // Redirigir según el resultado de la actualización
        response.sendRedirect("vista/ventas/ventas.jsp?status=success&action=actualizar");
        
    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.sendRedirect("vista/ventas/ventas.jsp?status=error&message=Datos inválidos");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("vista/ventas/ventas.jsp?status=error&message=Error inesperado");
    }
}








            else if ("eliminar".equals(btnAction)) {
                id_venta = Integer.parseInt(request.getParameter("id_venta")); 
                System.out.println("Intentando eliminar venta con ID: " + id_venta); 

                Venta_detalle detalleEliminar = new Venta_detalle(); 
                int detallesEliminados = detalleEliminar.eliminarPorIdVenta(id_venta);
                System.out.println("Eliminación de detalles para la venta ID: " + id_venta + " - Resultados: " + detallesEliminados);

                if (detallesEliminados > 0) {
                    if (venta.eliminar(id_venta) > 0) {
                        response.sendRedirect("vista/ventas/ventas.jsp?status=success&action=eliminar");
                    } else {
                        System.out.println("Error al eliminar la venta con ID: " + id_venta);
                        response.sendRedirect("vista/ventas/ventas.jsp?status=error&action=eliminar");
                    }
                } else {
                    System.out.println("No se eliminaron detalles para la venta ID: " + id_venta);
                    response.sendRedirect("vista/ventas/ventas.jsp?status=error&action=detalles_no_eliminados");
                }
            }



               

        } catch (NumberFormatException e) {
            response.sendRedirect("vista/ventas/ventas.jsp?status=error&action=invalid&message=Datos%20inválidos");
        } catch (Exception e) {
            response.sendRedirect("vista/ventas/ventas.jsp?status=error&action=exception&message=" + e.getMessage());
        }
    }


    
    //Obtener los datos por medio de Ajax del modal en JSP

    public List<Venta_detalle> obtenerDetallesPorIdVenta(int idVenta) {
        List<Venta_detalle> detalles = new ArrayList<>();
        try {
            conexion cn = new conexion();
            cn.abrir_conexion();

            String query = "SELECT * FROM ventas_detalle WHERE id_venta_detalle = ?";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, idVenta);

            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Venta_detalle detalle = new Venta_detalle(
                    consulta.getString("producto"),
                    consulta.getInt("id_venta_detalle"),
                    consulta.getInt("id_venta"),
                    consulta.getInt("id_producto"),
                    consulta.getInt("cantidad"),
                    consulta.getDouble("precio_unitario")
                );
                detalles.add(detalle);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener detalles: " + ex.getMessage());
        }
        return detalles;
    }

    
    
    
    
 //Método para actualizar el stock del producto
 private boolean verificarStock(int idProducto, int cantidadSolicitada) {
        conexion cn = new conexion();
        cn.abrir_conexion();
        boolean hayStock = false;

        try {
            String query = "SELECT existencia FROM productos WHERE id_producto = ?";
            PreparedStatement stmt = cn.conexionDB.prepareStatement(query);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int existenciaActual = rs.getInt("existencia");
                if (existenciaActual >= cantidadSolicitada) {
                    hayStock = true; // Hay stock suficiente
                } else {
                    System.out.println("Stock insuficiente para el producto con ID: " + idProducto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el stock: " + e.getMessage());
        } finally {
            cn.cerrar_conexion();
        }

        return hayStock;
    }

 
 // Actualiza la existencia del producto restando la cantidad vendida
    private boolean actualizarStockProducto(int idProducto, int cantidadVendida) {
        boolean actualizado = false;
        conexion cn = new conexion();
        cn.abrir_conexion(); 

        try {
            
            String sql = "UPDATE productos SET existencia = existencia - ? WHERE id_producto = ?";
            PreparedStatement stmt = cn.conexionDB.prepareStatement(sql);
            stmt.setInt(1, cantidadVendida);
            stmt.setInt(2, idProducto);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                actualizado = true;
                System.out.println("Stock del producto actualizado con éxito.");
            } else {
                System.out.println("No se actualizó el stock del producto.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el stock del producto: " + e.getMessage());
        } finally {
            cn.cerrar_conexion(); 
        }
        return actualizado;
    }

    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
