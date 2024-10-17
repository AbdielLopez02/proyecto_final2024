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
import modelo.Puesto;

/**
 *
 * @author Kenneth
 */
public class sr_puestos extends HttpServlet {
    // Declaración de la variable global
    Puesto puesto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Inicialización de la variable global con los parámetros del request
        puesto = new Puesto(
            Integer.parseInt(request.getParameter("txt_id_puesto")), // id_puesto
            request.getParameter("txt_puesto") // puesto
        );

        // Manejo de las operaciones según el botón presionado
        if ("crear".equals(request.getParameter("btn_crear"))) {
            if (puesto.crear() > 0) {
                response.sendRedirect("vista/ventas/puestos.jsp?status=success&action=crear");
            } else {
                response.sendRedirect("vista/ventas/puestos.jsp?status=error&action=crear");
            }
        }

        if ("actualizar".equals(request.getParameter("btn_actualizar"))) {
            if (puesto.actualizar() > 0) {
                response.sendRedirect("vista/ventas/puestos.jsp?status=success&action=actualizar");
            } else {
                response.sendRedirect("vista/ventas/puestos.jsp?status=error&action=actualizar");
            }
        }

        if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
            if (puesto.eliminar() > 0) {
                response.sendRedirect("vista/ventas/puestos.jsp?status=success&action=eliminar");
            } else {
                response.sendRedirect("vista/ventas/puestos.jsp?status=error&action=eliminar");
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

