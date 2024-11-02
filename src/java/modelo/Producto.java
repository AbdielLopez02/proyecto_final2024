/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import jakarta.servlet.http.Part;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juanjo SR
 */
public class Producto {
    conexion cn;
    private int id_producto;
    private String produto;
    private int id_marca;
    private String descripcion;
    private String imagen;
    private float precio_costo;
    private float precio_venta;
    private int existencia;
    private String fecha_ingreso;
    private static final String IMAGE_FOLDER_PATH = "C:\\Users\\Juanjo\\Documents\\NetBeansProjects\\proyecto_final2024-main\\web\\resources\\img\\";
    //private String pathRel = "../../resources/img/";
    
    public Producto(){}
    public Producto(int id_producto,String produto, int id_marca, String descripcion,float precio_costo, float precio_venta, int existencia, String fecha_ingreso) {
        this.id_producto = id_producto;
        this.produto = produto;
        this.id_marca = id_marca;
        this.descripcion = descripcion;
        //this.imagen = imagen;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
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
    
    
        public DefaultTableModel mostrar(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new conexion();
            cn.abrir_conexion();
            String query = "select p.id_producto,p.producto,m.id_marca,m.marca,p.descripcion,p.imagen,p.precio_costo,p.precio_venta,p.existencia,p.fecha_ingreso "
                    + "from proyecto_empresa2.productos p "
                    + "inner join proyecto_empresa2.marcas m on p.id_marca = m.id_marca "
                    + "order by p.id_producto asc";
            ResultSet consulta =  cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"ID","PRODUCTO","ID_MARCA","MARCA","DESCRIPCION","IMAGEN","PRECIO_C","PRECIO_V","EXISTENCIA","FECHA_ING"}; 
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10];
            while(consulta.next()){
                datos[0] = consulta.getString("ID_PRODUCTO");
                datos[1] = consulta.getString("ID_MARCA");
                datos[2] = consulta.getString("PRODUCTO");
                datos[3] = consulta.getString("MARCA");
                datos[4] = consulta.getString("DESCRIPCION");
                datos[5] = consulta.getString("IMAGEN");
                datos[6] = consulta.getString("PRECIO_COSTO");
                datos[7] = consulta.getString("PRECIO_VENTA");
                datos[8] = consulta.getString("EXISTENCIA");
                datos[9] = consulta.getString("FECHA_INGRESO");
                
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return tabla;
    }
        
        public int agregar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new conexion();
            //String query = "insert into proyecto_empresa2.productos (producto,id_marca,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso) values (?,?,?,?,?,?,?,?)";
            String query = "insert into proyecto_empresa2.productos (producto,id_marca,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso) values (?,?,?,?,?,?,?,?)";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, this.getProduto());
            parametro.setString(2, String.valueOf(this.getId_marca()));
            parametro.setString(3, this.getDescripcion());
            parametro.setString(4, this.getImagen());
            parametro.setString(5, String.valueOf(this.getPrecio_costo()));
            parametro.setString(6, String.valueOf(this.getPrecio_venta()));
            parametro.setString(7, String.valueOf(this.getExistencia()));
            parametro.setString(8, this.getFecha_ingreso());
//            parametro.setInt(9, this.getId_producto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    public int modificar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new conexion();
            String query = "update productos set producto = ?,id_marca = ?,descripcion = ?,precio_costo = ?,precio_venta = ?,existencia = ?,fecha_ingreso = ? where id_producto = ?";
            //String query = "update productos set producto = ?,id_marca = ?,descripcion = ?,imagen = ?,precio_costo = ?,precio_venta = ?,existencia = ?,fecha_ingreso = ? where id_producto = ?";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, this.getProduto());
            parametro.setString(2, String.valueOf(this.getId_marca()));
            parametro.setString(3, this.getDescripcion());
            //parametro.setString(4, this.getImagen());
            parametro.setString(4, String.valueOf(this.getPrecio_costo()));
            parametro.setString(5, String.valueOf(this.getPrecio_venta()));
            parametro.setInt(6, this.getExistencia());
            parametro.setString(7, this.getFecha_ingreso());
            parametro.setString(8, String.valueOf(this.getId_producto()));
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    public int modificarCImg(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new conexion();
            //String query = "update productos set producto = ?,id_marca = ?,descripcion = ?,precio_costo = ?,precio_venta = ?,existencia = ?,fecha_ingreso = ? where id_producto = ?";
            String query = "update productos set producto = ?,id_marca = ?,descripcion = ?,imagen = ?,precio_costo = ?,precio_venta = ?,existencia = ?,fecha_ingreso = ? where id_producto = ?";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, this.getProduto());
            parametro.setString(2, String.valueOf(this.getId_marca()));
            parametro.setString(3, this.getDescripcion());
            parametro.setString(4, this.getImagen());
            parametro.setString(5, String.valueOf(this.getPrecio_costo()));
            parametro.setString(6, String.valueOf(this.getPrecio_venta()));
            parametro.setInt(7, this.getExistencia());
            parametro.setString(8, this.getFecha_ingreso());
            parametro.setString(9, String.valueOf(this.getId_producto()));
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new conexion();
            String query = "delete from productos where id_producto = ?";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, this.getId_producto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
        
        
    public String guardaImg(Part imgPart) throws IOException, SQLException {
        String nombreArchivo = generarNombreUnico(imgPart.getSubmittedFileName());
        String pathArchivo = IMAGE_FOLDER_PATH + nombreArchivo;
        String pathArchivoRel = "../../resources/img/" + nombreArchivo;
        this.setImagen(pathArchivoRel);
        
        guardaImgLocal(imgPart, pathArchivo);


        return pathArchivo;
    }

    
    private String generarNombreUnico(String nombreOriginal) {
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }

    
    private void guardaImgLocal(Part imgPart, String pathArchivo) throws IOException {
        File Archivo = new File(pathArchivo);
        try (InputStream inputStream = imgPart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(Archivo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    } 
}
