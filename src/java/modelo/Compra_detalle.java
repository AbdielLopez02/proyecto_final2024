/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kenneth
 */
public class Compra_detalle {
      private String producto; 

    
    private int id_compra_detalle, id_compra, id_producto, cantidad;
    private double precio_costo_unitario;
    conexion cn;

    
    public Compra_detalle(){}
    
    public Compra_detalle(int id_compra_detalle, int id_compra, int id_producto, int cantidad, double precio_costo_unitario) {
        this.id_compra_detalle = id_compra_detalle;
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public Compra_detalle(String producto, int id_compra_detalle, int id_compra, int id_producto, int cantidad, double precio_costo_unitario) {
        this.producto = producto;
        this.id_compra_detalle = id_compra_detalle;
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_compra_detalle() {
        return id_compra_detalle;
    }

    public void setId_compra_detalle(int id_compra_detalle) {
        this.id_compra_detalle = id_compra_detalle;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
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

    public double getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(double precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    



public int crear() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "INSERT INTO compras_detalle(`id_compra`, `id_producto`, `cantidad`, `precio_costo_unitario`) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);

       
        parametro.setInt(1, getId_compra());
        parametro.setInt(2, getId_producto()); 
        parametro.setInt(3, getCantidad()); 
        parametro.setDouble(4, getPrecio_costo_unitario()); 

        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return retorno;
}

   
 public int actualizar(int id_compra_detalle) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "UPDATE compras_detalle SET id_compra = ?, id_producto = ?, cantidad = ?, precio_costo_unitario = ? WHERE id_compra_detalle = ?;";
        parametro = cn.conexionDB.prepareStatement(query);



        parametro.setInt(1, getId_compra());
        parametro.setInt(2, getId_producto()); 
        parametro.setInt(3, getCantidad()); 
        parametro.setInt(5, id_compra_detalle); // Establecer id_venta_detalle como parámetro
        
        retorno = parametro.executeUpdate();
        System.out.println("Resultado de la actualización de detalle: " + retorno);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el detalle: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion(); // Asegurarse de cerrar la conexión
    }
    return retorno; // Retorna el número de filas afectadas
}



































    
   public List<Compra_detalle> obtenerDetallesPorIdCompra(int idCompra) {
    List<Compra_detalle> detalles = new ArrayList<>();
    try {
        cn = new conexion();
        cn.abrir_conexion();

        // Modifica la consulta para incluir el nombre del producto
        String query = "SELECT vd.id_compra_detalle, vd.id_compra, vd.id_producto, vd.cantidad, vd.precio_costo_unitario, p.producto " +
                       "FROM compras_detalle vd " +
                       "INNER JOIN productos p ON vd.id_producto = p.id_producto " +
                       "WHERE vd.id_compra = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idCompra);
        ResultSet rs = parametro.executeQuery();

        while (rs.next()) {
            // Cambia el constructor de Venta_detalle para incluir el nombre del producto
            Compra_detalle detalle = new Compra_detalle(
                rs.getString("producto"), // Añade el nombre del producto aquí
                rs.getInt("id_compra_detalle"),
                rs.getInt("id_compra"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_costo_unitario")
            );

            detalles.add(detalle);
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error al obtener detalles de la compra: " + ex.getMessage());
    }
    return detalles;
} 
    
    
    
    
   
   
   
   
   
   
   
   
   
   
   
   
   
    public int agregarDetalle(int idCompra, int idProducto, int cantidad, double precio) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "INSERT INTO compras_detalle (id_compra, id_producto, cantidad, precio_costo_unitario) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idCompra);
        parametro.setInt(2, idProducto);
        parametro.setInt(3, cantidad);
        parametro.setDouble(4, precio);
        retorno = parametro.executeUpdate();
        System.out.println("Agregado detalle de venta ID: " + idCompra + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en agregarDetalle: " + ex.getMessage());
    }
    return retorno;
}
    
    public int eliminarPorIdDetalle(int idCompraDetalles) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        conexion cn = new conexion();
        String query = "DELETE FROM compras_detalle WHERE id_compra_detalle = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idCompraDetalles);
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación de detalle de compra ID: " + idCompraDetalles + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminarPorIdDetalle: " + ex.getMessage());
    }
    return retorno;
}
    
     public int eliminar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "DELETE FROM compras_detalle WHERE id_compra_detalle = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, getId_compra_detalle()); // Asegúrate de que este método devuelve el ID correcto
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación exitosa de detalle con ID: " + getId_compra_detalle() + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    }
    return retorno;
}

  
   
public int eliminarPorIdCompra(int idCompra) {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new conexion();
        String query = "DELETE FROM compras_detalle WHERE id_compra = ?;";
        cn.abrir_conexion();
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idCompra);
        retorno = parametro.executeUpdate();
        System.out.println("Eliminación de detalles para la compra ID: " + idCompra + " - Resultados: " + retorno);
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminarPorIdCompra: " + ex.getMessage());
    }
    return retorno;
}


    public static void eliminarPorCompra(int idCompra) {
    conexion cn = new conexion();
    try {
        cn.abrir_conexion();
        String query = "DELETE FROM compras_detalle WHERE id_compra = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idCompra);
        parametro.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error al eliminar detalles de la compra: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
}
    
}
