package controller;

import model.Categoria;
import model.Marca;
import model.Producto;
import model.data.dao.ProductoDAO;
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;
public class ProductoController {

    private static final String nombreDataBase = "ProductosDB2";

    public static boolean registrarProducto(int id, String nombre, Marca marca, Categoria categoria, double precio) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!ProductoDAO.validarExistenciaProducto(query, "ID", id)) {
            Producto producto = new Producto(id, nombre, marca, categoria, precio);
            ProductoDAO.registrarProducto(query, producto);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static String[][] mostrarTodosLosProductos() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerTodosLosProductos(query);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static String[][] mostrarProductosID(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductosID(query, id);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static String[][] mostrarProductosNombre(String nombre) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductosNombre(query, nombre);
        DBConnector.closeConnection();
        return datosProductos;
    }

    // mostrarProductosMarca si marca es un enum
    public static String[][] mostrarProductosMarca(Marca marca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductosMarca(query, marca);
        DBConnector.closeConnection();
        return datosProductos;
    }

    // mostrarProductosCategoria si categoria es un enum
    public static String[][] mostrarProductosCategoria(Categoria categoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductosCategoria(query, categoria);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static void modificarProducto(int id, String nombre, double precio) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        ProductoDAO.modificarProducto(query, id, nombre, precio);
        DBConnector.closeConnection();
    }

    public static void eliminarProducto(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        ProductoDAO.eliminarProducto(query, id);
        DBConnector.closeConnection();
    }
}
