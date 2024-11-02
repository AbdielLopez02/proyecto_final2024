/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juanjo SR
 */
public class Marca {
    conexion cn;
    private int id_marca;
    private String marca;
    
    public Marca(){}
    public Marca(int id_marca, String marca) {
        this.id_marca = id_marca;
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
    public DefaultTableModel mostrar(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new conexion();
            cn.abrir_conexion();
            String query = "select id_marca as id,marca from proyecto_empresa2.marcas order by id_marca asc";
            ResultSet consulta =  cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"ID","MARCA"}; 
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while(consulta.next()){
                datos[0] = consulta.getString("ID");
                datos[1] = consulta.getString("MARCA");                
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
            String query = "insert into marcas(marca) values (?)";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, this.getMarca());
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
            String query = "update marcas set marca = ? where id_marca = ?";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, this.getMarca());
            parametro.setInt(2, this.getId_marca());
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
            String query = "delete from marcas where id_marca = ?";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, this.getId_marca());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    
    public HashMap drop_marca(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new conexion();
            String query = "select id_marca as id,marca from proyecto_empresa2.marcas";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }

}

