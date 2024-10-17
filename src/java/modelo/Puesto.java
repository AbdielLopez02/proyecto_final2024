package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class Puesto {
    
    private int id_puesto;
    private String puesto; 
    conexion cn;

    public Puesto() {}

    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }

    // Getters y Setters
    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    // Método para obtener puestos en un HashMap
    public HashMap<String, String> drop_puesto() {
        HashMap<String, String> drop = new HashMap<>();
        try {
            String query = "SELECT id_puesto, puesto FROM puestos;";
            cn = new conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            while (consulta.next()) {
                drop.put(consulta.getString("id_puesto"), consulta.getString("puesto"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_puesto: " + ex.getMessage());
        }
        return drop;
    }

    // Leer todos los puestos y mostrarlos en una tabla
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            String query = "SELECT id_puesto, puesto FROM puestos;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            String[] encabezado = {"ID Puesto", "Puesto"};
            tabla.setColumnIdentifiers(encabezado);

            String[] datos = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_puesto");
                datos[1] = consulta.getString("puesto");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        }
        return tabla;
    }

    // Crear un nuevo puesto
    public int crear() {
        int retorno = 0;
        try {
            cn = new conexion();
            String query = "INSERT INTO puestos (puesto) VALUES (?);";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getPuesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en crear: " + ex.getMessage());
        }
        return retorno;
    }

    // Actualizar un puesto existente
    public int actualizar() {
        int retorno = 0;
        try {
            cn = new conexion();
            String query = "UPDATE puestos SET puesto = ? WHERE id_puesto = ?;";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getPuesto());
            parametro.setInt(2, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en actualizar: " + ex.getMessage());
        }
        return retorno;
    }

    // Eliminar un puesto por su ID
    public int eliminar() {
        int retorno = 0;
        try {
            cn = new conexion();
            String query = "DELETE FROM puestos WHERE id_puesto = ?;";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
}
