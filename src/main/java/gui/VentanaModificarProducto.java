package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaModificarProducto extends Ventana{
    public static void main(String[] args) {
        VentanaModificarProducto ventana = new VentanaModificarProducto();
    }

    // componentes de la ventana
    private JLabel textoMenu, textoID, textoNombre, textoPrecio;
    private JTextField campoID, campoNombre, campoPrecio;
    private JButton botonModificar, botonCancelar;

    public VentanaModificarProducto() {
        super("Modificar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonModificar();
        generarBotonCancelar();
        generarCampoID();
        generarCampoNombre();
        generarCampoPrecio();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Modificar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 190, 10, 200, 50);
    }

    private void generarBotonModificar() {
        String textoBoton = "Modificar Producto";
        botonModificar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonModificar.addActionListener(this);
        this.add(botonModificar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 170, 20);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    private void generarCampoID() {
        String textoID = "ID:";
        super.generarJLabel(this.textoID, textoID, 20, 50, 150, 20);
        campoID = super.generarJTextField(200, 50, 250, 20);
        this.add(campoID);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre:";
        super.generarJLabel(this.textoNombre, textoNombre, 20, 100, 150, 20);
        campoNombre = super.generarJTextField(200, 100, 250, 20);
        this.add(campoNombre);
    }

    private void generarCampoPrecio() {
        String textoPrecio = "Precio:";
        super.generarJLabel(this.textoPrecio, textoPrecio, 20, 150, 150, 20);
        campoPrecio = super.generarJTextField(200, 150, 250, 20);
        this.add(campoPrecio);
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonModificar) {
            try {
                ProductoController.modificarProducto(Integer.parseInt(campoID.getText()), campoNombre.getText(), Double.parseDouble(campoPrecio.getText()));
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        } else if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }
}
