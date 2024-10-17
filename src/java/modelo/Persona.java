package modelo;

import javax.swing.table.DefaultTableModel;

abstract class Persona {
    private String nombres;
    private String apellidos;
    private String telefono;
    private int genero;
    private String fecha_ingreso;
    
     public Persona() {
    }

    public Persona(String nombres, String apellidos, String telefono, int genero, String fecha_ingreso) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.genero = genero;
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

  
    
    
    // Métodos CRUD vacíos
    protected int crear() {return 0;}
    protected DefaultTableModel leer() { return null; }
    protected int actualizar() {return 0;}
    protected int eliminar() {return 0;}
}

