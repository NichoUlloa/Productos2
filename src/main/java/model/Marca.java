package model;

public enum Marca {
    // sony, samsung, lg, philips, panasonic
    SONY("Sony"), SAMSUNG("Samsung"), LG("LG"), PHILIPS("Philips"), PANASONIC("Panasonic");

    private String marca;

    private Marca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return marca;
    }
}
