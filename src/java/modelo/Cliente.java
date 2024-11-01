package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashMap;

public class Cliente extends Persona {

    private String nit, correo_electronico;
    private int id;

    conexion cn;

    public Cliente(){}

    public Cliente(String nit, String correo_electronico, int id, String nombres, String apellidos, String telefono, int genero, String fecha_ingreso) {
        super(nombres, apellidos, telefono, genero, fecha_ingreso);
        this.nit = nit;
        this.correo_electronico = correo_electronico;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

     public HashMap<String, String> drop_clientes() {
        HashMap<String, String> drop = new HashMap<>();
        try {
        String query = "SELECT id_cliente, nombres, apellidos, nit, genero, telefono, correo_electronico, fecha_ingreso FROM clientes;";
            cn = new conexion(); // Asegúrate de que la clase conexion esté implementada
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            // Recorre los resultados y almacena los empleados en el HashMap
            while (consulta.next()) {
                drop.put(consulta.getString("id_cliente"), consulta.getString("nombres") + " - " + consulta.getString("nit"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_clientes: " + ex.getMessage());
        }
        return drop;
    }

    

    @Override
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "SELECT id_cliente, nombres, apellidos, nit, genero, telefono, correo_electronico, fecha_ingreso FROM clientes;";
        System.out.println("Ejecutando consulta: " + query); // Mensaje de depuración
        
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        // Definir encabezados de la tabla
        String encabezado[] = {"id", "nombres", "apellidos", "nit", "genero", "telefono", "correo_electronico", "ingreso"};
        tabla.setColumnIdentifiers(encabezado);

        String datos[] = new String[8]; // Cambiado a 8 porque solo hay 8 columnas
        int filaCount = 0; // Contador de filas

        while (consulta.next()) {
            // Recuperar datos de la consulta
            datos[0] = consulta.getString("id_cliente"); // Cambiado a id_cliente
            datos[1] = consulta.getString("nombres");
            datos[2] = consulta.getString("apellidos");
            datos[3] = consulta.getString("nit");
            int genero = consulta.getInt("genero");
            datos[4] = (genero == 1) ? "Femenino" : "Masculino"; // Asignar género
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("correo_electronico");
            datos[7] = consulta.getString("fecha_ingreso");

            // Agregar fila a la tabla
            tabla.addRow(datos);
            filaCount++; // Incrementar contador de filas

            // Mensaje de depuración para cada fila
            System.out.println("Fila " + filaCount + ": " + String.join(", ", datos));
        }

        System.out.println("Total de filas recuperadas: " + filaCount); // Mensaje de total de filas
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
        String query = "INSERT INTO clientes(nombres, apellidos, nit, telefono, genero, correo_electronico, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

        // Asigna los valores del objeto Cliente a los parámetros de la consulta
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getNit());
        parametro.setString(4, getTelefono());
        parametro.setInt(5, getGenero()); // Manejado como boolean en Java
        parametro.setString(6, getCorreo_electronico()); // Cambiado a correo_electronico
        parametro.setString(7, getFecha_ingreso());

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
        String query = "UPDATE clientes SET nombres = ?, apellidos = ?, nit = ?, telefono = ?, genero = ?, correo_electronico = ?, fecha_ingreso = ? WHERE id_cliente = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
        
        // Asigna los valores del objeto Cliente a los parámetros de la consulta
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getNit());
        parametro.setString(4, getTelefono());
        parametro.setInt(5, getGenero()); // Manejado como boolean en Java
        parametro.setString(6, getCorreo_electronico()); // Cambiado a correo_electronico
        parametro.setString(7, getFecha_ingreso());
        parametro.setInt(8, getId()); // Asegúrate de que el ID sea correcto

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
        parametro.setInt(1, getId()); // Asegúrate de que el ID sea correcto
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación exitosa: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    }
    return retorno;
}

}
