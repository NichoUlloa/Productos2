package model;

public class Producto {
    // atributos
    private int id;
    private String nombre;
    private Marca marca;
    private Categoria categoria;
    private double precio;

    // constructor
    public Producto(int id, String nombre, Marca marca, Categoria categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
}
