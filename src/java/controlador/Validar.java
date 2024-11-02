package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.User;
import modelo.UserDao;

public class Validar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verificación del referer
        String referrer = request.getHeader("referer");
        if (referrer == null || !referrer.contains(request.getServerName())) {
        response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }

        // Obtener parámetros del formulario
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        // Validar credenciales
        UserDao userDao = new UserDao();
        User usuario = userDao.validar(user, pass);
        
        if (usuario != null) {
            // Almacena el nombre de usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", usuario.getUser());
            
            // Redirige a la página principal
            response.sendRedirect("vista/main/main.jsp");
        } else {
            // Credenciales inválidas, redirige al formulario con un mensaje de error
            response.sendRedirect("index.html?error=true");
        }
    }
    
 
}
