/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kenneth
 */
public class Marca {
    
    
    int id;
    String marca;
    conexion cn;

   
    
    public Marca(){}
    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public HashMap<String, String> drop_marcas() {
        HashMap<String, String> drop = new HashMap<>();
        try {
            String query = "SELECT id_marca, marca FROM marcas;";
            cn = new conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            while (consulta.next()) {
                drop.put(consulta.getString("id_marca"), consulta.getString("marca"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_marcas: " + ex.getMessage());
        }
        return drop;
    }
public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            String query = "SELECT id_marca, marca FROM marcas;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            String[] encabezado = {"ID marca", "marca"};
            tabla.setColumnIdentifiers(encabezado);

            String[] datos = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_marca");
                datos[1] = consulta.getString("marca");
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
            String query = "INSERT INTO marcas (marca) VALUES (?);";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getMarca());
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
            String query = "UPDATE marcas SET marca = ? WHERE id_marca = ?;";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getMarca());
            parametro.setInt(2, getId());
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
            String query = "DELETE FROM marcas WHERE id_marca = ?;";
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
}
