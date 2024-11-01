package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;

/**
 *
 * @author Kenneth
 */
public class sr_cliente extends HttpServlet {
    Cliente cliente;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    try {
        // Inicializar el objeto Cliente con los parámetros recibidos
        cliente = new Cliente(
            request.getParameter("txt_nit"), 
            request.getParameter("txt_correo"), //
            Integer.parseInt(request.getParameter("txt_id")), // id
            request.getParameter("txt_nombres"), // nombres
            request.getParameter("txt_apellidos"), // apellidos
            request.getParameter("txt_telefono"), // telefono
            Integer.parseInt(request.getParameter("txt_genero")), // genero
            request.getParameter("txt_fi") // fecha_ingreso
        );

        // Manejar las operaciones según el botón presionado
        if ("crear".equals(request.getParameter("btn_crear"))) {
            if (cliente.crear() > 0) {
                response.sendRedirect("vista/ventas/clientes.jsp?status=success&action=crear");
            } else {
                response.sendRedirect("vista/ventas/clientes.jsp?status=error&action=crear");
            }
        }

        if ("actualizar".equals(request.getParameter("btn_actualizar"))) {
            if (cliente.actualizar() > 0) {
                response.sendRedirect("vista/ventas/clientes.jsp?status=success&action=actualizar");
            } else {
                response.sendRedirect("vista/ventas/clientes.jsp?status=error&action=actualizar");
            }
        }

        if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
            if (cliente.eliminar() > 0) {
                response.sendRedirect("vista/ventas/clientes.jsp?status=success&action=eliminar");
            } else {
                response.sendRedirect("vista/ventas/clientes.jsp?status=error&action=eliminar");
            }
        }
    } catch (NumberFormatException e) {
        response.sendRedirect("vista/ventas/clientes.jsp?status=error&action=invalid&message=Datos%20inválidos");
    } catch (Exception e) {
        response.sendRedirect("vista/ventas/clientes.jsp?status=error&action=exception&message=" + e.getMessage());
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
