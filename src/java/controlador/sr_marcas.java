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
import modelo.Marca;

/**
 *
 * @author Kenneth
 */
public class sr_marcas extends HttpServlet {
    // Declaración de la variable global
    Marca marca;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Inicialización de la variable global con los parámetros del request
        marca = new Marca(
            Integer.parseInt(request.getParameter("txt_id_marca")), // id_puesto
            request.getParameter("txt_marca") // puesto
        );

        // Manejo de las operaciones según el botón presionado
        if ("crear".equals(request.getParameter("btn_crear"))) {
            if (marca.crear() > 0) {
                response.sendRedirect("vista/productos/marcas.jsp?status=success&action=crear");
            } else {
                response.sendRedirect("vista/productos/marcas.jsp?status=error&action=crear");
            }
        }

        if ("actualizar".equals(request.getParameter("btn_actualizar"))) {
            if (marca.actualizar() > 0) {
                response.sendRedirect("vista/productos/marcas.jsp?status=success&action=actualizar");
            } else {
                response.sendRedirect("vista/productos/marcas.jsp?status=error&action=actualizar");
            }
        }

        if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
            if (marca.eliminar() > 0) {
                response.sendRedirect("vista/productos/marcas.jsp?status=success&action=eliminar");
            } else {
                response.sendRedirect("vista/productos/marcas.jsp?status=error&action=eliminar");
            }
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
        return "Short description";
    }
}

