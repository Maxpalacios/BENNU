package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOPROVEEDOR extends Conexion {

    // Método para registrar un nuevo proveedor
    public void registrarProveedor(proveedor prov) throws Exception {
        String sql = "INSERT INTO proveedores (nombre, direccion, telefono, ruc, estado) "
                + "VALUES ('" + prov.getNombre() + "', '"
                + prov.getDireccion() + "', '"
                + prov.getTelefono() + "', '"
                + prov.getRuc() + "', "
                + (prov.isEstado() ? "1" : "0") + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    // Método para listar todos los proveedores
    public List<proveedor> listarProveedores() throws Exception {
        List<proveedor> proveedores;
        proveedor prov;
        ResultSet rs = null;
        String sql = "SELECT * FROM proveedores ORDER BY idProveedor";
        
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            proveedores = new ArrayList<>();
            while (rs.next()) {
                prov = new proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setRuc(rs.getString("ruc"));
                prov.setEstado(rs.getBoolean("estado"));
                proveedores.add(prov);
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return proveedores;
    }

    // Método para leer un proveedor específico
    public proveedor leerProveedor(proveedor prov) throws Exception {
        proveedor provResult = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM proveedores WHERE idProveedor = " + prov.getIdProveedor();
        
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next()) {
                provResult = new proveedor();
                provResult.setIdProveedor(rs.getInt("idProveedor"));
                provResult.setNombre(rs.getString("nombre"));
                provResult.setDireccion(rs.getString("direccion"));
                provResult.setTelefono(rs.getString("telefono"));
                provResult.setRuc(rs.getString("ruc"));
                provResult.setEstado(rs.getBoolean("estado"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return provResult;
    }

    // Método para actualizar un proveedor existente
    public void actualizarProveedor(proveedor prov) throws Exception {
        String sql = "UPDATE proveedores SET nombre = '"
                + prov.getNombre() + "', direccion = '"
                + prov.getDireccion() + "', telefono = '"
                + prov.getTelefono() + "', ruc = '"
                + prov.getRuc() + "', estado = "
                + (prov.isEstado() ? "1" : "0")
                + " WHERE idProveedor = " + prov.getIdProveedor();
        
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    // Método para eliminar un proveedor
    public void eliminarProveedor(proveedor prov) throws Exception {
        String sql = "DELETE FROM proveedores WHERE idProveedor = " + prov.getIdProveedor();
        
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
}
