package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int id;
    private String nombre;
    private String url;
    private int idPadre;
    private String icono; // Nuevo campo para el icono
    private List<Menu> submenus; // Lista para almacenar submenús
    conexion cn;

    public Menu() {}

    public Menu(int id, String nombre, String url, int idPadre, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.idPadre = idPadre;
        this.icono = icono;
        this.submenus = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<Menu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(List<Menu> submenus) {
        this.submenus = submenus;
    }

    // Método para obtener los menús desde la base de datos
    public List<Menu> obtenerMenus() {
        List<Menu> allMenus = new ArrayList<>();
        try {
            cn = new conexion();
            cn.abrir_conexion();

            String query = "SELECT id, nombre, url, id_padre, icono FROM menus";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            ResultSet rs = parametro.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("url"), 
                    rs.getInt("id_padre"), 
                    rs.getString("icono")
                );
                allMenus.add(menu);
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener menús: " + ex.getMessage());
        }
        return allMenus;
    }

    // Método separado para construir jerarquías
    public List<Menu> construirJerarquia(List<Menu> allMenus) {
        List<Menu> listaMenusPrincipales = new ArrayList<>();

        // Construir la jerarquía de menús
        for (Menu menu : allMenus) {
            if (menu.getIdPadre() == 0) {
                listaMenusPrincipales.add(menu); // Añadir los menús principales
            } else {
                // Añadir el menú como submenú del menú correspondiente
                for (Menu parentMenu : allMenus) {
                    if (parentMenu.getId() == menu.getIdPadre()) {
                        parentMenu.getSubmenus().add(menu);
                        break;
                    }
                }
            }
        }
        return listaMenusPrincipales;
    }
}
