package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private conexion cn = new conexion(); // Inicializa la conexión
    private Connection con; // Variable Connection
    private PreparedStatement ps; 
    private ResultSet rs;

    public User validar(String user, String password) {
        User us = null; // Cambia a null por defecto
        String sql = "SELECT * FROM usuarios WHERE username=? AND password=?"; // Consulta SQL

        try {
            con = cn.abrir_conexion(); // Usa el método para abrir conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password); // Usa la contraseña que ingresó el usuario
            rs = ps.executeQuery();

            if (rs.next()) { // Si hay un resultado, significa que el usuario existe
                us = new User();
                us.setId(rs.getInt("id_usuario"));
                us.setUser(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores de consulta
        } finally {
            cn.cerrar_conexion(); // Cierra la conexión
        }

        return us; // Devuelve el objeto User o null si no se encontró
    }
}
