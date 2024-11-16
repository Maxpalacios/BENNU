package Controlador;

import Modelo.DAOPROVEEDOR;
import Modelo.proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "srvProveedor", urlPatterns = {"/srvProveedor"})
public class srvProveedor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listar":
                        listarProveedores(request, response);
                        break;
                    case "nuevo":
                        presentarFormulario(request, response);
                        break;
                    case "registrar":
                        registrarProveedor(request, response);
                        break;
                    case "editar":
                        presentarProveedor(request, response);
                        break;
                    case "actualizar":
                        actualizarProveedor(request, response);
                        break;
                    case "eliminar":
                        eliminarProveedor(request, response);
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("msje", "Error en el proceso: " + e.getMessage());
        }
    }

    private void listarProveedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAOPROVEEDOR dao = new DAOPROVEEDOR();
        List<proveedor> proveedores;
        try {
            proveedores = dao.listarProveedores();
            request.setAttribute("proveedores", proveedores);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/proveedores.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los proveedores: " + e.getMessage());
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/nuevoProveedor.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista: " + e.getMessage());
        }
    }

    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAOPROVEEDOR dao;
        proveedor prov = new proveedor();

        try {
            prov.setNombre(request.getParameter("txtNombre"));
            prov.setDireccion(request.getParameter("txtDireccion"));
            prov.setTelefono(request.getParameter("txtTelefono"));
            prov.setRuc(request.getParameter("txtRuc"));
            prov.setEstado(request.getParameter("chkEstado") != null);

            dao = new DAOPROVEEDOR();
            dao.registrarProveedor(prov);

            response.sendRedirect("srvProveedor?accion=listar");
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo registrar el proveedor: " + e.getMessage());
            request.setAttribute("proveedor", prov);
            this.presentarFormulario(request, response);
        }
    }

    private void presentarProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAOPROVEEDOR dao;
        proveedor prov;
        if (request.getParameter("id") != null) {
            prov = new proveedor();
            prov.setIdProveedor(Integer.parseInt(request.getParameter("id")));
            dao = new DAOPROVEEDOR();
            try {
                prov = dao.leerProveedor(prov);
                if (prov != null) {
                    request.setAttribute("proveedor", prov);
                } else {
                    request.setAttribute("msje", "No se encontró el proveedor");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos: " + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        this.getServletConfig().getServletContext()
                .getRequestDispatcher("/vistas/editarProveedor.jsp").forward(request, response);
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAOPROVEEDOR dao;
        proveedor prov = new proveedor();

        try {
            prov.setIdProveedor(Integer.parseInt(request.getParameter("hId")));
            prov.setNombre(request.getParameter("txtNombre"));
            prov.setDireccion(request.getParameter("txtDireccion"));
            prov.setTelefono(request.getParameter("txtTelefono"));
            prov.setRuc(request.getParameter("txtRuc"));
            prov.setEstado(request.getParameter("chkEstado") != null);

            dao = new DAOPROVEEDOR();
            dao.actualizarProveedor(prov);

            response.sendRedirect("srvProveedor?accion=listar");
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo actualizar el proveedor: " + e.getMessage());
            request.setAttribute("proveedor", prov);
            this.presentarProveedor(request, response);
        }
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAOPROVEEDOR dao = new DAOPROVEEDOR();
        proveedor prov = new proveedor();

        try {
            prov.setIdProveedor(Integer.parseInt(request.getParameter("id")));
            dao.eliminarProveedor(prov);
            response.sendRedirect("srvProveedor?accion=listar");
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo eliminar el proveedor: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para la gestión de proveedores";
    }
}
