package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Cliente extends Persona {

    private String nit, correo_electronico;
    private int id_cliente;

    conexion cn;

    public Cliente() {}

    public Cliente(String nit, String correo_electronico, int id_cliente, String nombres, String apellidos, String telefono, int genero, String fecha_ingreso) {
        super(nombres, apellidos, telefono, genero, fecha_ingreso);
        this.nit = nit;
        this.correo_electronico = correo_electronico;
        this.id_cliente = id_cliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            String query = "SELECT id_cliente, nombres, apellidos, nit, genero, telefono, correo_electronico, fecha_ingreso FROM clientes;";

            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            String encabezado[] = {"id_cliente", "nombres", "apellidos", "nit", "genero", "telefono", "correo_electronico", "fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[12];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_cliente");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("nit");

            int genero = consulta.getInt("genero");
            if (genero == 1) {
                datos[4] = "Femenino";
            } else {
                datos[4] = "Masculino";
            }

                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("correo_electronico");
                datos[7] = consulta.getString("fecha_ingreso");

                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        }
        return tabla;
    }

    @Override
    public int crear() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "INSERT INTO clientes('id_cliente', `nombres`, `apellidos`, 'nit', `genero`, 'telefono', 'correo_electronico', 'fecha_ingreso') VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

            parametro.setInt(1, getId_cliente());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getNit());
            parametro.setInt(5, getGenero());
            parametro.setString(6, getTelefono()); // Manejado como boolean en Java
            parametro.setString(7, getCorreo_electronico());
            parametro.setString(8, getFecha_ingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public int actualizar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "UPDATE clientes SET id_cliente = ?, nombres = ?, apellidos = ?, genero = ?, telefono = ?, correo_electronico = ?, fecha_ingreso = ? WHERE id_cliente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_cliente());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getNit());
            parametro.setInt(5, getGenero()); // Manejado como boolean en Java
            parametro.setString(6, getTelefono());
            parametro.setString(7, getCorreo_electronico());            
            parametro.setString(8, getFecha_ingreso());            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "DELETE FROM clientes WHERE id_cliente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_cliente());
            retorno = parametro.executeUpdate();
            System.out.println("Eliminación exitosa: " + retorno);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }

}
