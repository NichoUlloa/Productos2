package model;

public enum Categoria {
    // televisores, celulares, computadores, electrodomesticos
    TELEVISORES("Televisores"), CELULARES("Celulares"), COMPUTADORES("Computadores"), ELECTRODOMESTICOS("Electrodomesticos");

    private String categoria;

    private Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
