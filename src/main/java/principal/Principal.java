package principal;

import gui.VentanaBienvenida;
import model.data.DBGenerator;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {

        String nombreDataBase = "ProductosDB2";
        DBGenerator.iniciarBD(nombreDataBase);
        VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
    }
}