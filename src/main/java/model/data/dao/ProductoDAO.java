package model.data.dao;

import model.Categoria;
import model.Marca;
import model.Producto;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import java.util.ArrayList;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class ProductoDAO {

    public static void registrarProducto(DSLContext query, Producto producto){
        Table tablaProducto= table(name("producto"));
        Field[] columnas = tablaProducto.fields("ID", "nombre","marca","Categoria","precio");
        query.insertInto(tablaProducto, columnas[0], columnas[1],columnas[2],columnas[3], columnas[4])
                .values(producto.getId(), producto.getNombre(),producto.getMarca().getMarca(),producto.getCategoria().getCategoria(),producto.getPrecio())
                .execute();
    }


    public static boolean validarExistenciaProducto(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("producto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }

    public static void modificarProducto(DSLContext query, int id, String nombre, double precio){
        query.update(DSL.table("producto")).set(DSL.field("nombre"),nombre).set(DSL.field("precio"),precio).
                where(DSL.field("Id").eq(id)).execute();
    }

    public static void eliminarProducto(DSLContext query, int id){
        query.delete(DSL.table("producto")).where(DSL.field("Id").eq(id)).execute();
    }

    public List obtenerProductos(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaProductos(resultados);
    }

    private List obtenerListaProductos(Result resultados){
        List<Producto> productos= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            int id = (int) resultados.getValue(fila,"id");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String marca = (String) resultados.getValue(fila,"marca");
            String categoria = (String) resultados.getValue(fila,"categoria");
            double precio = (double) resultados.getValue(fila,"precio");
            productos.add(new Producto(id,nombre, Marca.valueOf(marca), Categoria.valueOf(categoria),precio));
            // Marca y Categoria son enums
        }
        return productos;
    }
    public static String[][] exportarDatos(Result resultados){
        String[][] datosResultado=new String[resultados.size()][5]; // 5 por la cantidad de datos
        for(int registro = 0; registro < resultados.size(); registro ++){
            datosResultado[registro][0] = String.valueOf((Integer) resultados.getValue(registro,"id"));
            datosResultado[registro][1] = (String) resultados.getValue(registro,"nombre");
            datosResultado[registro][2] = (String) resultados.getValue(registro,"marca");
            datosResultado[registro][3] = (String) resultados.getValue(registro,"categoria");
            datosResultado[registro][4] = String.valueOf((Double) resultados.getValue(registro,"precio"));
        }
        return datosResultado;
    }

    public static String[][] obtenerTodosLosProductos(DSLContext query){
        Table producto = DSL.table("producto");
        Result resultados = query.select().from(producto).fetch();
        return exportarDatos(resultados);
    }

    public static String[][] obtenerProductosID(DSLContext query, int id){
        Table producto = DSL.table("producto");
        Result resultados = query.select().from(producto)
                .where(DSL.field("id").eq(id)).fetch();
        return exportarDatos(resultados);
    }

    public static String[][] obtenerProductosNombre(DSLContext query, String nombre){
        Table producto = DSL.table("producto");
        Result resultados = query.select().from(producto)
                .where(DSL.field("nombre").eq(nombre)).fetch();
        return exportarDatos(resultados);
    }

    // obtenerProductosMarca si marca es un enum
    public static String[][] obtenerProductosMarca(DSLContext query, Marca marca) {
        Table producto = DSL.table("producto");
        Result resultados = query.select().from(producto)
                .where(DSL.field("marca").eq(marca.getMarca())).fetch();
        return exportarDatos(resultados);
    }

    // obtenerProductosCategoria si categoria es un enum
    public static String[][] obtenerProductosCategoria(DSLContext query, Categoria categoria) {
        Table producto = DSL.table("producto");
        Result resultados = query.select().from(producto)
                .where(DSL.field("categoria").eq(categoria.getCategoria())).fetch();
        return exportarDatos(resultados);
    }

}
