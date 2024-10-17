package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Importa la clase HttpSession
import modelo.User;
import modelo.UserDao;

public class Validar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        UserDao userDao = new UserDao();
        User usuario = userDao.validar(user, pass); // Verifica las credenciales
        
        if (usuario != null) {
            // Almacena el nombre de usuario en la sesión
            HttpSession session = request.getSession(); // Crea o recupera la sesión
            session.setAttribute("nombreUsuario", usuario.getUser()); // Almacena el nombre de usuario
            
            // Las credenciales son válidas, redirige a la página principal
            response.sendRedirect("vista/main/main.jsp");
        } else {
            // Las credenciales no son válidas, redirige de vuelta al formulario con un mensaje de error
            response.sendRedirect("index.html?error=true");
        }
    }
}
