package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement;


public class Venta {

    private String serie, fecha_factura, fecha_ingreso;
    private int id, id_cliente, id_empleado, no_factura;

    conexion cn;

    
    public Venta(){}
    
    public Venta(String serie, String fecha_factura, String fecha_ingreso, int id, int id_cliente, int id_empleado, int no_factura) {
        this.serie = serie;
        this.fecha_factura = fecha_factura;
        this.fecha_ingreso = fecha_ingreso;
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.no_factura = no_factura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    

   
   
   public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
        
        // Incluir id_producto y producto en la consulta
         String query = "SELECT v.id_venta, v.no_factura, v.serie, v.fecha_factura, " +
               "c.nombres AS nombre_cliente, e.nombres AS nombre_empleado, " +
               "v.fecha_ingreso, c.id_cliente, e.id_empleado " +
               "FROM ventas v " +
               "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
               "INNER JOIN empleados e ON v.id_empleado = e.id_empleado " +
               "ORDER BY v.id_venta ASC;";


        
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        // Aumentar encabezado para id_producto y producto
        String encabezado[] = {"id_venta", "no_factura", "serie", "fecha_factura", "nombre_cliente", "nombre_empleado", "fecha_ingreso", "id_cliente", "id_empleado",};
        tabla.setColumnIdentifiers(encabezado);

        // Aumentar el tamaño del array de datos para incluir id_producto y producto
        String datos[] = new String[10];  
        while (consulta.next()) {
            datos[0] = consulta.getString("id_venta"); 
            datos[1] = consulta.getString("no_factura");
            datos[2] = consulta.getString("serie");
            datos[3] = consulta.getString("fecha_factura");
            datos[4] = consulta.getString("nombre_cliente"); 
            datos[5] = consulta.getString("nombre_empleado"); 
            datos[6] = consulta.getString("fecha_ingreso");
            datos[7] = consulta.getString("id_cliente");  
            datos[8] = consulta.getString("id_empleado");  
            
           ;     

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
        String query = "INSERT INTO ventas(`no_factura`, `serie`, `fecha_factura`, `id_cliente`, `id_empleado`, `fecha_ingreso`) VALUES (?, ?, ?, ?, ?, ?);";
        
        cn.abrir_conexion();
        // Preparamos la declaración para obtener el ID generado
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        // Asignamos los parámetros
        parametro.setInt(1, getNo_factura()); 
        parametro.setString(2, getSerie()); 
        parametro.setString(3, getFecha_factura()); 
        parametro.setInt(4, getId_cliente()); 
        parametro.setInt(5, getId_empleado()); 
        parametro.setString(6, getFecha_ingreso()); 

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
    return retorno; // Retorna el ID de la venta creada
}



    
  public int actualizar(int id_venta) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        String query = "UPDATE ventas SET no_factura = ?, serie = ?, fecha_factura = ?, id_cliente = ?, id_empleado = ?, fecha_ingreso = ? WHERE id_venta = ?;";
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

        // Establecer los parámetros en el orden correcto
        parametro.setInt(1, getNo_factura());
        parametro.setString(2, getSerie());
        parametro.setString(3, getFecha_factura());
        parametro.setInt(4, getId_cliente());
        parametro.setInt(5, getId_empleado());
        parametro.setString(6, getFecha_ingreso());
        parametro.setInt(7, id_venta); // Usar el ID de la venta que se está actualizando

        retorno = parametro.executeUpdate();
        System.out.println("Resultado de la actualización de venta: " + retorno);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar la venta: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion(); // Asegurarse de cerrar la conexión
    }
    return retorno; // Retorna el número de filas afectadas
}



   
 public int eliminar(int idVenta) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "DELETE FROM ventas WHERE id_venta = ?;"; 
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVenta); // Usamos idVenta directamente
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación exitosa: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    }
    return retorno;
}

}
