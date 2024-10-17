package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Empleado extends Persona {

    private String dpi, direccion, fecha_nacimiento, fecha_inicio_labores;
    private int id, id_puesto;

    conexion cn;

    public Empleado() {}

    public Empleado(String dpi, String direccion, String fecha_nacimiento, String fecha_inicio_labores, int id, int id_puesto, String nombres, String apellidos, String telefono, int genero, String fecha_ingreso) {
        super(nombres, apellidos, telefono, genero, fecha_ingreso); // `genero` se maneja en la clase padre Persona
        this.dpi = dpi;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.id = id;
        this.id_puesto = id_puesto;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    @Override
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();
            String query = "SELECT e.id_empleado AS id, e.nombres, e.apellidos, e.direccion, e.telefono, e.dpi, e.genero, e.fecha_nacimiento, p.puesto, p.id_puesto, e.fecha_inicio_labores, e.fecha_ingreso FROM empleados AS e INNER JOIN puestos AS p ON e.id_puesto = p.id_puesto;";

            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            String encabezado[] = {"id", "nombres", "apellidos", "direccion", "telefono", "dpi", "genero", "nacimiento", "puesto", "id_puesto", "inicio_labores", "ingreso"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[12];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("dpi");

            int genero = consulta.getInt("genero");
            if (genero == 1) {
                datos[6] = "Femenino";
            } else {
                datos[6] = "Masculino";
            }

                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("puesto");
                datos[9] = consulta.getString("id_puesto");
                datos[10] = consulta.getString("fecha_inicio_labores");
                datos[11] = consulta.getString("fecha_ingreso");

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
            String query = "INSERT INTO empleados(`nombres`, `apellidos`, `direccion`, `telefono`, `dpi`, `genero`, `fecha_nacimiento`, `id_puesto`, `fecha_inicio_labores`, `fecha_ingreso`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setInt(6, getGenero()); // Manejado como boolean en Java
            parametro.setString(7, getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getFecha_inicio_labores());
            parametro.setString(10, getFecha_ingreso());
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
            String query = "UPDATE empleados SET dpi = ?, direccion = ?, nombres = ?, apellidos = ?, telefono = ?, fecha_nacimiento = ?, genero = ?, id_puesto = ?, fecha_inicio_labores = ?, fecha_ingreso = ? WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getDpi());
            parametro.setString(2, getDireccion());
            parametro.setString(3, getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getGenero()); // Manejado como boolean en Java
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getFecha_inicio_labores());
            parametro.setString(10, getFecha_ingreso());
            parametro.setInt(11, getId());
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
            String query = "DELETE FROM empleados WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            System.out.println("Eliminación exitosa: " + retorno);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
}
