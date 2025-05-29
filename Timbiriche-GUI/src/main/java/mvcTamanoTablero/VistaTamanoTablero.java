package mvcTamanoTablero;

import javax.swing.*;
import java.awt.*;

public class VistaTamanoTablero extends JFrame {

    private final JComboBox<String> cmbTamanos;
    private final JButton btnContinuar;

    public VistaTamanoTablero() {
        setTitle("Seleccionar Tamaño de Tablero");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        add(new JLabel("Tamaño del tablero:", SwingConstants.CENTER));

        cmbTamanos = new JComboBox<>(new String[]{"3", "4", "5", "6", "7"});
        add(cmbTamanos);

        btnContinuar = new JButton("Continuar");
        add(btnContinuar);

        setVisible(true);
    }

    public JComboBox<String> getCmbTamanos() {
        return cmbTamanos;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }

    public int getTamañoSeleccionado() {
        return Integer.parseInt((String) cmbTamanos.getSelectedItem());
    }
}
