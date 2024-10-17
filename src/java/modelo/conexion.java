package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    // Detalles de conexión
    private final String puerto = "3306";
    private final String db = "proyecto_empresa";
    private final String urlConexion = String.format("jdbc:mysql://127.0.0.1:%s/%s?serverTimezone=UTC", puerto, db);
    private final String usuario = "root2";
    private final String contra = "1234b";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    public Connection conexionDB;

    // Método para abrir la conexión
    public Connection abrir_conexion() {
        try {
            Class.forName(jdbc);
            conexionDB = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Algo salió mal: " + ex.getMessage());
        }
        return conexionDB; // Retorna la conexión
    }

    // Método para cerrar la conexión
    public void cerrar_conexion() {
        try {
            if (conexionDB != null && !conexionDB.isClosed()) {
                conexionDB.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            System.out.println("Algo salió mal: " + ex.getMessage());
        }
    }
}
