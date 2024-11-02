/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Proveedor;

/**
 *
 * @author Kenneth
 */

   public class sr_proveedor extends HttpServlet {
    Proveedor proveedor;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Inicializar el objeto Proveedor con los parámetros recibidos
            proveedor = new Proveedor(
                request.getParameter("txt_proveedor"), // proveedor
                request.getParameter("txt_nit"), // nit
                request.getParameter("txt_direccion"), // direccion
                request.getParameter("txt_telefono"), // telefono
                Integer.parseInt(request.getParameter("txt_id")) // id_proveedor
            );

            // Manejar las operaciones según el botón presionado
            if ("crear".equals(request.getParameter("btn_crear"))) {
                if (proveedor.crear() > 0) {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=success&action=crear");
                } else {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=error&action=crear");
                }
            }

            if ("actualizar".equals(request.getParameter("btn_actualizar"))) {
                if (proveedor.actualizar() > 0) {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=success&action=actualizar");
                } else {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=error&action=actualizar");
                }
            }

            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (proveedor.eliminar() > 0) {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=success&action=eliminar");
                } else {
                    response.sendRedirect("vista/compras/proveedores.jsp?status=error&action=eliminar");
                }
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("vista/compras/proveedores.jsp?status=error&action=invalid&message=Datos%20inválidos");
        } catch (Exception e) {
            response.sendRedirect("vista/compras/proveedores.jsp?status=error&action=exception&message=" + e.getMessage());
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

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de Proveedores";
    }
}
