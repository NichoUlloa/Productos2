package gui;

import model.Producto;
import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaBuscarProducto extends Ventana{
    public static void main(String[] args) {
        VentanaBuscarProducto ventana = new VentanaBuscarProducto();
    }

    // componentes de la ventana
    private JLabel textoMenu,textoID,textoNombre;
    private JTextField campoID,campoNombre;
    private JButton botonBuscar,botonCancelar;

    public VentanaBuscarProducto() {
        super("Buscar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonBuscar();
        generarBotonCancelar();
        generarCampoID();
        generarCampoNombre();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Buscar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 190, 10, 200, 50);
    }

    private void generarBotonBuscar() {
        String textoBoton = "Buscar Producto";
        botonBuscar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonBuscar.addActionListener(this);
        this.add(botonBuscar);
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

    // metodo exportarDatosProducto
    private String[][] exportarDatosProducto() throws ClassNotFoundException {
        if(this.campoID.getText().length()==0 && this.campoNombre.getText().length()==0){
            JOptionPane.showMessageDialog(this,"Ingrese datos validos");
            return new String[0][0];
        }
        else if(this.campoID.getText().length()==0){
            return ProductoController.mostrarProductosNombre(this.campoNombre.getText());
        }
        else{
            return ProductoController.mostrarProductosID(Integer.parseInt(this.campoID.getText()));
        }
    }


    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.botonBuscar){
            String[] nombreColumnas={"ID","Nombre","Marca","Categoria","Precio"};
            try {
                VentanaTabla ventanaTabla= new VentanaTabla(exportarDatosProducto(),nombreColumnas);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == this.botonCancelar){
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }

    }



}

// ejemplo metodo exportar datos
//private String[][] exportarDatos() throws ClassNotFoundException {
//        if(this.campoNombre.getText().length()==0 && this.campoCarrera.getSelectedItem().equals("Error no existen carreras") ){
//            JOptionPane.showMessageDialog(this,"Ingrese datos validos");
//            return new String[0][0];
//        }
//        else if(this.campoNombre.getText().length()==0){
//            return EstudianteController.mostrarEstudiantesPorCarrera(this.campoCarrera.getSelectedItem().toString());
//        }
//        else{
//            return EstudianteController.mostrarEstudiantesPorNombre(this.campoCarrera.getSelectedItem().toString(),this.campoNombre.getText());
//        }
//
//
//    }

// ejemplo override actionPerformed
// public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == this.botonBuscar){
//            String[] nombreColumnas={"Nombre estudiante","N° de matricula","Carrera","CodigoCarrera"};
//            try {
//                VentanaTabla ventanaTabla= new VentanaTabla(exportarDatos(),nombreColumnas);
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//        if (e.getSource() == this.botonRegresar){
//            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
//            this.dispose();
//        }
//
//    }
