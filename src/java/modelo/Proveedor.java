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
public class Proveedor {
    
   private String proveedor, nit, direccion, telefono;
    private int id_proveedor;
    conexion cn;

    
    public Proveedor(){}
    
    public Proveedor(String proveedor, String nit, String direccion, String telefono, int id_proveedor) {
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id_proveedor = id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    
    
    
    
    public HashMap<String, String> drop_proveedor() {
        HashMap<String, String> drop = new HashMap<>();
        try {
        String query = "SELECT id_proveedor,proveedor , nit, direccion, telefono FROM proveedores;";
            cn = new conexion(); // Asegúrate de que la clase conexion esté implementada
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            // Recorre los resultados y almacena los empleados en el HashMap
            while (consulta.next()) {
                drop.put(consulta.getString("id_proveedor"), consulta.getString("proveedor"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_proveedor: " + ex.getMessage());
        }
        return drop;
    }

    

      public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            
            String query = "SELECT id_proveedor, proveedor, nit, direccion, telefono FROM proveedores;";
            System.out.println("Ejecutando consulta: " + query); 
            
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"ID", "Proveedor", "NIT", "Dirección", "Teléfono"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_proveedor");
                datos[1] = consulta.getString("proveedor");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        }
        return tabla;
    }

    // Método para crear un nuevo registro
    public int crear() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "INSERT INTO proveedores(proveedor, nit, direccion, telefono) VALUES (?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);

            parametro.setString(1, getProveedor());
            parametro.setString(2, getNit());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en crear: " + ex.getMessage());
        }
        return retorno;
    }

    // Método para actualizar un registro existente
    public int actualizar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "UPDATE proveedores SET proveedor = ?, nit = ?, direccion = ?, telefono = ? WHERE id_proveedor = ?;";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);

            parametro.setString(1, getProveedor());
            parametro.setString(2, getNit());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setInt(5, getId_proveedor());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en actualizar: " + ex.getMessage());
        }
        return retorno;
    }

    // Método para eliminar un registro
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "DELETE FROM proveedores WHERE id_proveedor = ?;";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);

            parametro.setInt(1, getId_proveedor());
            retorno = parametro.executeUpdate();
            System.out.println("Eliminación exitosa: " + retorno);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
    

    
    
}
