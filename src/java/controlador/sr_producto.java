package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Producto;

@MultipartConfig
public class sr_producto extends HttpServlet {
    Producto producto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Manejo de la carga de imagen
            String imagenPath = null;
            Part filePart = request.getPart("txt_imagen"); // Obtiene el archivo de imagen

            if (filePart != null && filePart.getSize() > 0) { // Verifica si hay un archivo
                // Define la ruta donde se guardará la imagen
                String uploadPath = "C:\\Users\\Kenneth\\Documents\\NetBeansProjects\\proyecto_f\\web\\resources\\img";
                String fileName = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
                imagenPath = "resources/img/" + fileName; // Ruta relativa a almacenar en la base de datos

                // Crea el archivo en el sistema
                File file = new File(uploadPath, fileName);
                try (InputStream fileContent = filePart.getInputStream();
                     FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                // Si no se ha subido una nueva imagen, se mantiene la imagen actual
                int idProducto = Integer.parseInt(request.getParameter("txt_id"));
                producto = new Producto().leerPorId(idProducto); // Cargar datos actuales del producto
                imagenPath = producto.getImagen(); // Mantener la imagen existente
            }

            // Inicializar el objeto Producto con los parámetros recibidos
            producto = new Producto(
                Integer.parseInt(request.getParameter("txt_id")), // id_producto
                request.getParameter("txt_producto"), // producto
                Integer.parseInt(request.getParameter("drop_marcas")), // id_marca
                request.getParameter("txt_descripcion"), // descripcion
                imagenPath, // imagen (URL)
                Double.parseDouble(request.getParameter("txt_precio_costo")), // precio_costo
                Double.parseDouble(request.getParameter("txt_precio_venta")), // precio_venta
                Integer.parseInt(request.getParameter("txt_existencia")), // existencia
                request.getParameter("txt_fecha_ingreso") // fecha_ingreso
            );

            // Manejar las operaciones según el botón presionado
            if ("crear".equals(request.getParameter("btn_crear"))) {
                if (producto.crear() > 0) {
                    response.sendRedirect("vista/productos/productos.jsp?status=success&action=crear");
                } else {
                    response.sendRedirect("vista/productos/productos.jsp?status=error&action=crear");
                }
            }

            if ("actualizar".equals(request.getParameter("btn_actualizar"))) {
                if (producto.actualizar() > 0) {
                    response.sendRedirect("vista/productos/productos.jsp?status=success&action=actualizar");
                } else {
                    response.sendRedirect("vista/productos/productos.jsp?status=error&action=actualizar");
                }
            }

            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (producto.eliminar() > 0) {
                    response.sendRedirect("vista/productos/productos.jsp?status=success&action=eliminar");
                } else {
                    response.sendRedirect("vista/productos/productos.jsp?status=error&action=eliminar");
                }
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("vista/productos/productos.jsp?status=error&action=invalid&message=Datos%20inválidos");
        } catch (Exception e) {
            response.sendRedirect("vista/productos/productos.jsp?status=error&action=exception&message=" + e.getMessage());
        }
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
}
