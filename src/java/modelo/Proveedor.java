package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Proveedor{

    private String nit, proveedor, direccion, telefono;
    private int id_proveedor;

    private conexion cn;

    public Proveedor() {}

    public Proveedor(String nit, String proveedor, String direccion, String telefono, int id_proveedor, conexion cn) {
        this.nit = nit;
        this.proveedor = proveedor;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id_proveedor = id_proveedor;
        this.cn = cn;
    }
    
       public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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


    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            String query = "SELECT id_proveedor, proveedor, nit, direccion, telefono FROM proveedores;";

            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            String encabezado[] = {"id_proveedor", "proveedor", "nit", "direccion", "telefono"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[12];
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

    public int crear() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "INSERT INTO proveedores('id_proveedor', `proveedor`, 'nit', `direccion`, 'telefono') VALUES (?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

            parametro.setInt(1, getId_proveedor());
            parametro.setString(2, getProveedor());
            parametro.setString(3, getNit());
            parametro.setString(5, getDireccion());
            parametro.setString(4, getTelefono());           
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int actualizar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "UPDATE proveedores SET id_proveedor = ?, proveedor = ?, nit = ?, direccion = ?, telefono = ? WHERE id_proveedor = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_proveedor());            
            parametro.setString(3, getProveedor());
            parametro.setString(4, getNit());
            parametro.setString(2, getDireccion());
            parametro.setString(5, getTelefono());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "DELETE FROM proveedores WHERE id_proveedor = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
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