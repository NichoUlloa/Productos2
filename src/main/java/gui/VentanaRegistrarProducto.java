package gui;

import controller.ProductoController;
import model.Categoria;
import model.Marca;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarProducto extends Ventana{
    public static void main(String[] args) {
        VentanaRegistrarProducto ventana = new VentanaRegistrarProducto();
    }

    // componentes de la ventana
    private JLabel textoMenu, textoID, textoNombre, textoMarca, textoCategoria, textoPrecio;
    private JTextField campoID, campoNombre, campoPrecio;
    private JComboBox campoMarca, campoCategoria;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarProducto() {
        super("Registrar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonRegistrar();
        generarBotonCancelar();
        generarCampoID();
        generarCampoNombre();
        generarListaMarca();
        generarListaCategoria();
        generarCampoPrecio();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 190, 10, 200, 50);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Producto";
        botonRegistrar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
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

    private void generarListaMarca() {
        super.generarJLabel(this.textoMarca, "Marca:", 20, 200, 100, 20);
        campoMarca = super.generarListaDesplegable(Marca.values(), 200, 200, 250, 20);
        this.add(campoMarca);
    }
    // categoria es un enum
    private void generarListaCategoria() {
        super.generarJLabel(this.textoCategoria, "Categoria:", 20, 250, 100, 20);
        campoCategoria = super.generarListaDesplegable(Categoria.values(), 200, 250, 250, 20);
        this.add(campoCategoria);
    }

    private boolean registrarProducto() {
        if (campoID.getText().length() == 0 || campoNombre.getText().length() == 0 || campoPrecio.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            if (registrarProducto()) {
                try {
                    ProductoController.registrarProducto(Integer.parseInt(campoID.getText()) , campoNombre.getText(), (Marca) campoMarca.getSelectedItem(), (Categoria) campoCategoria.getSelectedItem(), Double.parseDouble(campoPrecio.getText()));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "Producto registrado exitosamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos");
            }
        } else if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }



}
