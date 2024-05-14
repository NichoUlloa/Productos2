package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaEliminarProducto extends Ventana{
    public static void main(String[] args) {
        VentanaEliminarProducto ventana = new VentanaEliminarProducto();
    }

    // componentes de la ventana
    private JLabel textoMenu, textoID;
    private JTextField campoID;
    private JButton botonEliminar, botonCancelar;

    public VentanaEliminarProducto() {
        super("Eliminar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonEliminar();
        generarBotonCancelar();
        generarCampoID();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Eliminar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 190, 10, 200, 50);
    }

    private void generarBotonEliminar() {
        String textoBoton = "Eliminar Producto";
        botonEliminar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonEliminar.addActionListener(this);
        this.add(botonEliminar);
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

    // metodo eliminarProducto
    private void eliminarProducto() throws ClassNotFoundException {
        ProductoController productoController = new ProductoController();
        int id = Integer.parseInt(campoID.getText());
        productoController.eliminarProducto(id);
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEliminar) {
            try {
                eliminarProducto();
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
                this.dispose();
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
            }
        } else if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }
}
