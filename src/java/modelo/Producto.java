package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kenneth
 */
public class Producto {
    private int id_producto;
    private String producto;
    private int id_marca;
    private String descripcion;
    private String marca;
    private String imagen;
    private double precio_costo;
    private double precio_venta;
    private int existencia;
    private String fecha_ingreso;
    conexion cn;
    // Constructor vacío
    public Producto() {}

    public Producto(int id_producto, String producto, int id_marca, String descripcion, String imagen, double precio_costo, double precio_venta, int existencia, String fecha_ingreso) {
        this.id_producto = id_producto;
        this.producto = producto;
        this.id_marca = id_marca;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
    }

    public Producto(int id_producto, String producto, int id_marca, String descripcion, String marca, String imagen, double precio_costo, double precio_venta, int existencia, String fecha_ingreso) {
        this.id_producto = id_producto;
        this.producto = producto;
        this.id_marca = id_marca;
        this.descripcion = descripcion;
        this.marca = marca;
        this.imagen = imagen;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    
   

  public Producto leerPorId(int idProducto) {
    Producto producto = null;
    conexion cn = new conexion();
    
    try {
        cn.abrir_conexion();

        String query = "SELECT p.id_producto AS id, p.producto AS producto, m.id_marca AS id_marca, m.marca AS marca, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso " +
                       "FROM productos AS p INNER JOIN marcas AS m ON p.id_marca = m.id_marca WHERE p.id_producto = ?";
        PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, idProducto);

        ResultSet rs = parametro.executeQuery();

        if (rs.next()) {
            producto = new Producto(
                rs.getInt("id"), // id_producto
                rs.getString("producto"), // producto
                rs.getInt("id_marca"), // id_marca
                rs.getString("marca"), // nombre de la marca
                rs.getString("descripcion"), // descripcion
                rs.getString("imagen"), // URL de la imagen
                rs.getDouble("precio_costo"), // precio_costo
                rs.getDouble("precio_venta"), // precio_venta
                rs.getInt("existencia"), // existencia
                rs.getString("fecha_ingreso") // fecha_ingreso
            );
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error al obtener producto por ID: " + ex.getMessage());
    }
    
    return producto;
}


   

   

    public HashMap<String, String> drop_productos() {
        HashMap<String, String> drop = new HashMap<>();
        try {
            String query = "SELECT id_producto, id_marca, producto, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso FROM productos;";
            cn = new conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

            while (consulta.next()) {
                drop.put(consulta.getString("id_producto"), consulta.getString("producto"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_productos: " + ex.getMessage());
        }
        return drop;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            cn = new conexion();
            cn.abrir_conexion();

            String query = "SELECT p.id_producto AS id, p.producto AS producto, m.id_marca AS id_marca, m.marca AS marca, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso FROM productos AS p INNER JOIN marcas AS m ON p.id_marca = m.id_marca;";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            ResultSet rs = parametro.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("producto"),
                    rs.getInt("id_marca"),
                    rs.getString("marca"),
                    rs.getString("descripcion"),
                    rs.getString("imagen"),
                    rs.getDouble("precio_costo"),
                    rs.getDouble("precio_venta"),
                    rs.getInt("existencia"),
                    rs.getString("fecha_ingreso")
                );

                listaProductos.add(producto);
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener productos: " + ex.getMessage());
        }
        return listaProductos;
    }

    
 public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "SELECT p.id_producto AS id, p.producto AS producto, m.id_marca AS id_marca, m.marca AS marca, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso FROM productos AS p INNER JOIN marcas AS m ON p.id_marca = m.id_marca;";
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        String encabezado[] = {"id", "producto", "id_marca", "marca", "descripcion", "imagen", "precio_costo", "precio_venta", "existencia", "fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);

        String datos[] = new String[10];
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("producto");
            datos[2] = consulta.getString("id_marca");
            datos[3] = consulta.getString("marca");
            datos[4] = consulta.getString("descripcion");
            datos[5] = consulta.getString("imagen");
            datos[6] = consulta.getString("precio_costo");
            datos[7] = consulta.getString("precio_venta");
            datos[8] = consulta.getString("existencia");
            datos[9] = consulta.getString("fecha_ingreso");
            

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
            String query = "INSERT INTO productos(producto, id_marca, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);

            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());
            parametro.setString(4, getImagen());
            parametro.setDouble(5, getPrecio_costo());
            parametro.setDouble(6, getPrecio_venta());
            parametro.setInt(7, getExistencia());
            parametro.setString(8, getFecha_ingreso());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al crear producto: " + ex.getMessage());
        }
        return retorno;
    }

    public int actualizar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "UPDATE productos SET producto = ?, id_marca = ?, descripcion = ?, imagen = ?, precio_costo = ?, precio_venta = ?, existencia = ?, fecha_ingreso = ? WHERE id_producto = ?;";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);
            
            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());
            parametro.setString(4, getImagen()); // Asegúrate de que la URL de la imagen se actualice
            parametro.setDouble(5, getPrecio_costo());
            parametro.setDouble(6, getPrecio_venta());
            parametro.setInt(7, getExistencia());
            parametro.setString(8, getFecha_ingreso());
            parametro.setInt(9, getId_producto());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar producto: " + ex.getMessage());
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new conexion();
            String query = "DELETE FROM productos WHERE id_producto = ?;";
            cn.abrir_conexion();
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_producto());
            
            retorno = parametro.executeUpdate();
            System.out.println("Eliminación exitosa: " + retorno);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar producto: " + ex.getMessage());
        }
        return retorno;
    }
}
