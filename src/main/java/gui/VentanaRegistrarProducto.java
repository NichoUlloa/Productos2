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

    private boolean registrarProducto() throws ClassNotFoundException, IllegalArgumentException {
        if (campoID.getText().isEmpty() || campoNombre.getText().isEmpty() || campoPrecio.getText().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos.");
        }
        int id = Integer.parseInt(campoID.getText());
        String nombre = campoNombre.getText();
        Marca marca = (Marca) campoMarca.getSelectedItem();
        Categoria categoria = (Categoria) campoCategoria.getSelectedItem();
        double precio = Double.parseDouble(campoPrecio.getText());

        return ProductoController.registrarProducto(id, nombre, marca, categoria, precio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            try {
                if (registrarProducto()) {
                    JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Producto ya ingresado o datos incorrectos");
                    limpiarCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato de número incorrecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            } catch (ClassNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                ex.printStackTrace();
            }
        } else if (e.getSource() == botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }

    private void limpiarCampos() {
        campoID.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
        campoMarca.setSelectedIndex(0); // Suponiendo que el primer elemento es un marcador vacío o una opción válida
        campoCategoria.setSelectedIndex(0); // Suponiendo que el primer elemento es un marcador vacío o una opción válida
    }




}
