package Modelo;

public class proveedor {
    private int idProveedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ruc;
    private boolean estado;

    // Constructor vacío
    public proveedor() {
    }

    // Constructor con parámetros
    public proveedor(int idProveedor, String nombre, String direccion, String telefono, String ruc, boolean estado) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ruc = ruc;
        this.estado = estado;
    }

    // Métodos getter y setter

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
