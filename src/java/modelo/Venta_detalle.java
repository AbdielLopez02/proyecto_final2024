/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kenneth
 */
public class Venta_detalle {
    private String producto; 

    
    private int id_venta_detalle, id_venta, id_producto, cantidad;
    private double precio_unitario;
    conexion cn;
    
    public Venta_detalle(){}

    public Venta_detalle(int id_venta_detalle, int id_venta, int id_producto, int cantidad, double precio_unitario) {
        this.id_venta_detalle = id_venta_detalle;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public Venta_detalle(String producto, int id_venta_detalle, int id_venta, int id_producto, int cantidad, double precio_unitario) {
        this.producto = producto;
        this.id_venta_detalle = id_venta_detalle;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_venta_detalle() {
        return id_venta_detalle;
    }

    public void setId_venta_detalle(int id_venta_detalle) {
        this.id_venta_detalle = id_venta_detalle;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    
    
  public List<Venta_detalle> obtenerDetallesPorIdVenta(int idVenta) {
    List<Venta_detalle> detalles = new ArrayList<>();
    try {
        cn = new conexion();
        cn.abrir_conexion();

        // Modifica la consulta para incluir el nombre del producto
        String query = "SELECT vd.id_venta_detalle, vd.id_venta, vd.id_producto, vd.cantidad, vd.precio_unitario, p.producto " +
                       "FROM ventas_detalle vd " +
                       "INNER JOIN productos p ON vd.id_producto = p.id_producto " +
                       "WHERE vd.id_venta = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVenta);
        ResultSet rs = parametro.executeQuery();

        while (rs.next()) {
            // Cambia el constructor de Venta_detalle para incluir el nombre del producto
            Venta_detalle detalle = new Venta_detalle(
                rs.getString("producto"), // Añade el nombre del producto aquí
                rs.getInt("id_venta_detalle"),
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_unitario")
            );

            detalles.add(detalle);
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error al obtener detalles de la venta: " + ex.getMessage());
    }
    return detalles;
}







   
   
   public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "SELECT vd.id_venta_detalle, vd.id_venta, vd.id_producto, p.producto, vd.cantidad, vd.precio_unitario " +
                       "FROM ventas_detalle vd " +
                       "INNER JOIN productos p ON vd.id_producto = p.id_producto " +
                       "ORDER BY vd.id_venta_detalle ASC;";

        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        String encabezado[] = {"id_venta_detalle", "id_venta", "id_producto", "producto", "cantidad", "precio_unitario"};
        tabla.setColumnIdentifiers(encabezado);

        String datos[] = new String[6];  
        while (consulta.next()) {
            datos[0] = consulta.getString("id_venta_detalle"); 
            datos[1] = consulta.getString("id_venta");
            datos[2] = consulta.getString("id_producto"); 
            datos[3] = consulta.getString("producto"); 
            datos[4] = consulta.getString("cantidad");         
            datos[5] = consulta.getString("precio_unitario");

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
        String query = "INSERT INTO ventas_detalle(`id_venta`, `id_producto`, `cantidad`, `precio_unitario`) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

       
        parametro.setInt(1, getId_venta());
        parametro.setInt(2, getId_producto()); 
        parametro.setInt(3, getCantidad()); 
        parametro.setDouble(4, getPrecio_unitario()); 

        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return retorno;
}



   public int agregarDetalle(int idVenta, int idProducto, int cantidad, double precio) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "INSERT INTO ventas_detalle (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVenta);
        parametro.setInt(2, idProducto);
        parametro.setInt(3, cantidad);
        parametro.setDouble(4, precio);
        retorno = parametro.executeUpdate();
        System.out.println("Agregado detalle de venta ID: " + idVenta + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en agregarDetalle: " + ex.getMessage());
    }
    return retorno;
}

   
   
   
   
   
   
   


    
 public int actualizar(int id_venta_detalle) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "UPDATE ventas_detalle SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id_venta_detalle = ?;";
        parametro = cn.conexionDB.prepareStatement(query);

        // Establecer los parámetros en el orden correcto
        parametro.setInt(1, getId_venta()); // Asegúrate de que este método esté definido en tu clase
        parametro.setInt(2, getId_producto());
        parametro.setInt(3, getCantidad());
        parametro.setDouble(4, getPrecio_unitario());
        parametro.setInt(5, id_venta_detalle); // Establecer id_venta_detalle como parámetro
        
        retorno = parametro.executeUpdate();
        System.out.println("Resultado de la actualización de detalle: " + retorno);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el detalle: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion(); // Asegurarse de cerrar la conexión
    }
    return retorno; // Retorna el número de filas afectadas
}






   public int eliminar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "DELETE FROM ventas_detalle WHERE id_venta_detalle = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, getId_venta_detalle()); // Asegúrate de que este método devuelve el ID correcto
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación exitosa de detalle con ID: " + getId_venta_detalle() + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    }
    return retorno;
}

  public int eliminarPorIdDetalle(int idVentaDetalles) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        conexion cn = new conexion();
        String query = "DELETE FROM ventas_detalle WHERE id_venta_detalle = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVentaDetalles);
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación de detalle de venta ID: " + idVentaDetalles + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminarPorIdDetalle: " + ex.getMessage());
    }
    return retorno;
}
   
public int eliminarPorIdVenta(int idVenta) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "DELETE FROM ventas_detalle WHERE id_venta = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVenta);
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación de detalles para la venta ID: " + idVenta + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminarPorIdVenta: " + ex.getMessage());
    }
    return retorno;
}


public void actualizarStockProducto(int idProducto, int cantidadVendida) {
    try {
        conexion cn = new conexion();
        cn.abrir_conexion();

        // Primero, verificar la existencia del producto
        int existenciaActual = obtenerExistenciaProducto(idProducto);
        if (existenciaActual < cantidadVendida) {
            System.out.println("No hay suficiente existencia del producto para realizar la venta.");
            return; // Salir del método si no hay suficiente stock
        }

        // Actualiza la existencia del producto restando la cantidad vendida
        String query = "UPDATE productos SET existencia = existencia - ? WHERE id_producto = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, cantidadVendida);
        parametro.setInt(2, idProducto);

        int resultado = parametro.executeUpdate();
        if (resultado > 0) {
            System.out.println("Stock del producto actualizado con éxito.");
        } else {
            System.out.println("No se actualizó el stock del producto.");
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el stock del producto: " + ex.getMessage());
    }
}

// Método para obtener la existencia del producto
private int obtenerExistenciaProducto(int idProducto) {
    int existencia = 0;
    try {
        conexion cn = new conexion();
        cn.abrir_conexion();

        String sql = "SELECT existencia FROM productos WHERE id_producto = ?";
        PreparedStatement stmt = cn.conexionDB.prepareStatement(sql);
        stmt.setInt(1, idProducto);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            existencia = rs.getInt("existencia");
        }

        cn.cerrar_conexion();
    } catch (SQLException e) {
        System.out.println("Error al obtener la existencia del producto: " + e.getMessage());
    }
    return existencia;
}

public static void eliminarPorVenta(int idVenta) {
    conexion cn = new conexion();
    try {
        cn.abrir_conexion();
        String query = "DELETE FROM ventas_detalle WHERE id_venta = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idVenta);
        parametro.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error al eliminar detalles de la venta: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
}

}