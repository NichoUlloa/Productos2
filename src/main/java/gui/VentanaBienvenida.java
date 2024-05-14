package gui;

import controller.ProductoController;
import model.data.dao.ProductoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaBienvenida extends Ventana {

    public static void main(String[] args) {
        VentanaBienvenida ventana = new VentanaBienvenida();
    }

    // componentes de la ventana
    private JLabel textoMenu;
    private JButton mostrarProductos;
    private JButton registrarProducto;
    private JButton eliminarProducto;
    private JButton modificarProducto;
    private JButton buscarProducto;
    private JButton salir;

    public VentanaBienvenida() {
        super("TIENDA DE PRODUCTOS", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonMostrarProductos();
        generarBotonRegistrarProducto();
        generarBotonEliminarProducto();
        generarBotonModificarProducto();
        generarBotonBuscarProducto();
        generarBotonSalida();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "TIENDA DE PRODUCTOS";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 150, 10, 300, 50);
    }

    private void generarBotonMostrarProductos() {
        mostrarProductos = super.generarBoton("Mostrar Productos", 150, 70, 200, 40);
        mostrarProductos.addActionListener(this);
        this.add(mostrarProductos);
    }

    private void generarBotonRegistrarProducto() {
        registrarProducto = super.generarBoton("Registrar Producto", 150, 120, 200, 40);
        registrarProducto.addActionListener(this);
        this.add(registrarProducto);
    }

    private void generarBotonEliminarProducto() {
        eliminarProducto = super.generarBoton("Eliminar Producto", 150, 170, 200, 40);
        eliminarProducto.addActionListener(this);
        this.add(eliminarProducto);
    }

    private void generarBotonModificarProducto() {
        modificarProducto = super.generarBoton("Modificar Producto", 150, 220, 200, 40);
        modificarProducto.addActionListener(this);
        this.add(modificarProducto);
    }

    private void generarBotonBuscarProducto() {
        buscarProducto = super.generarBoton("Buscar Producto", 150, 270, 200, 40);
        buscarProducto.addActionListener(this);
        this.add(buscarProducto);
    }

    private void generarBotonSalida() {
        salir = super.generarBoton("Salir", 150, 380, 200, 40);
        salir.addActionListener(this);
        this.add(salir);
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarProductos) {
            try {
                String[][] datosProductos = ProductoController.mostrarTodosLosProductos();
                VentanaTabla ventanaTabla = new VentanaTabla(datosProductos, new String[]{"ID", "Nombre", "Marca", "Categoría", "Precio"});
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == registrarProducto) {
            VentanaRegistrarProducto ventanaRegistrarProducto = new VentanaRegistrarProducto();
        } else if (e.getSource() == eliminarProducto) {
            VentanaEliminarProducto ventanaEliminarProducto = new VentanaEliminarProducto();
        } else if (e.getSource() == modificarProducto) {
            VentanaModificarProducto ventanaModificarProducto = new VentanaModificarProducto();
        } else if (e.getSource() == buscarProducto) {
            VentanaBuscarProducto ventanaBuscarProducto = new VentanaBuscarProducto();
        } else if (e.getSource() == salir) {
            System.exit(0);
        }
    }
}
