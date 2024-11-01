package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Menu;

public class sr_menu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Obtener la lista de menús
        Menu menu = new Menu(); // Asumiendo que el constructor no requiere parámetros
        List<Menu> menus = menu.obtenerMenus();
        List<Menu> menusJerarquicos = menu.construirJerarquia(menus); // Construir jerarquía
        request.setAttribute("menus", menusJerarquicos); // Establecer la lista jerárquica como atributo

        // Imprimir nombres de los menús y submenús en la respuesta HTTP
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Menús</title></head>");
            out.println("<body>");
            out.println("<h1>Menús obtenidos</h1>");

            // Depuración: imprimir los menús antes de imprimir en la respuesta
            Set<String> menuNames = new HashSet<>(); // Para almacenar nombres únicos
            for (Menu m : menus) {
                menuNames.add(m.getNombre());
            }
            out.println("<p>Menús únicos: " + menuNames + "</p>");

            // Imprimir menús en formato jerárquico
            printMenus(out, menusJerarquicos);

            out.println("</body>");
            out.println("</html>");
        }
    }

    // Método para imprimir menús y submenús de manera jerárquica con solo nombres y URLs
    private void printMenus(PrintWriter out, List<Menu> menus) {
        if (menus != null && !menus.isEmpty()) {
            out.println("<ul>");
            for (Menu m : menus) {
                out.println("<li><a href=\"" + m.getUrl() + "\">" + m.getNombre() + "</a></li>");

                // Si hay submenús, imprimirlos de forma recursiva
                if (m.getSubmenus() != null && !m.getSubmenus().isEmpty()) {
                    printMenus(out, m.getSubmenus());
                }
            }
            out.println("</ul>");
        } else {
            out.println("<p>No se encontraron menús.</p>");
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
        return "Servlet para manejar el menú";
    }
}
