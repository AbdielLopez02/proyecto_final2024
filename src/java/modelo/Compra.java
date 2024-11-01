/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kenneth
 */
public class Compra {
    
    private String fecha_orden, fecha_ingreso;
    private int id, id_proveedor, no_orden_compra;
    conexion cn;

    
public Compra(){}
    
    public Compra(String fecha_orden, String fecha_ingreso, int id, int id_proveedor, int no_orden_compra) {
        this.fecha_orden = fecha_orden;
        this.fecha_ingreso = fecha_ingreso;
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.no_orden_compra = no_orden_compra;
    }

    // Getters y Setters...

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            
            String query = "SELECT c.id_compra, c.fecha_orden, c.fecha_ingreso, c.id_proveedor, cd.proveedor, c.no_orden_compra " +
                           "FROM compras c " +
                           "INNER JOIN proveedores cd ON c.id_proveedor = cd.id_proveedor " +
                           "ORDER BY c.id_compra ASC;"; 
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            
            String encabezado[] = {"ID", "No Orden Compra","Proveedor", "Fecha Orden", "Fecha Ingreso", "ID Proveedor"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[6];  
            while (consulta.next()) {
                datos[0] = consulta.getString("id_compra"); 
                datos[1] = consulta.getString("no_orden_compra"); 
                datos[2] = consulta.getString("proveedor"); 
                datos[3] = consulta.getString("fecha_orden");
                datos[4] = consulta.getString("fecha_ingreso");
                datos[5] = consulta.getString("id_proveedor"); 
               
                
                
                tabla.addRow(datos);
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        }
        return tabla;
    }

    public int crear() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "INSERT INTO compras(`fecha_orden`, `fecha_ingreso`, `id_proveedor`, `no_orden_compra`) VALUES (?, ?, ?, ?);";
            
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Asignamos los parámetros
            parametro.setString(1, fecha_orden); 
            parametro.setString(2, fecha_ingreso); 
            parametro.setInt(3, id_proveedor); 
            parametro.setInt(4, no_orden_compra); 

            // Ejecutamos la inserción
            parametro.executeUpdate();
            
            // Obtenemos el ID generado
            ResultSet keys = parametro.getGeneratedKeys();
            if (keys.next()) {
                retorno = keys.getInt(1); // El primer campo de GeneratedKeys es el ID generado
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en crear: " + ex.getMessage());
        }
        return retorno; // Retorna el ID de la compra creada
    }

    public int actualizar(int id_compra) {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            cn.abrir_conexion();
            String query = "UPDATE compras SET fecha_orden = ?, fecha_ingreso = ?, id_proveedor = ?, no_orden_compra = ? WHERE id_compra = ?;";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

            // Establecer los parámetros en el orden correcto
            parametro.setString(1, fecha_orden);
            parametro.setString(2, fecha_ingreso);
            parametro.setInt(3, id_proveedor);
            parametro.setInt(4, no_orden_compra);
            parametro.setInt(5, id); // Usar el ID de la compra que se está actualizando

            retorno = parametro.executeUpdate();
            System.out.println("Resultado de la actualización de compra: " + retorno);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la compra: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion(); // Asegurarse de cerrar la conexión
        }
        return retorno; // Retorna el número de filas afectadas
    }

    public int eliminar(int idCompra) {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "DELETE FROM compras WHERE id_compra = ?;"; 
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, idCompra); // Usamos idCompra directamente
            retorno = parametro.executeUpdate();
            System.out.println("Eliminación exitosa: " + retorno);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
}

    
    
    
    

