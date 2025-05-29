package mvcRegistro;

import blackboard.IV;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame implements IV<ModeloRegistro> {

    private final JTextField txtNombre;
    private final JButton btnColor;
    private final JButton btnAvatar;
    private final JButton btnRegistrar;

    private Color colorSeleccionado = Color.BLUE;
    private String rutaAvatarSeleccionado = "";

    public VistaRegistro() {
        setTitle("Registro");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        txtNombre = new JTextField();
        btnColor = new JButton("Seleccionar Color");
        btnAvatar = new JButton("Seleccionar Avatar");
        btnRegistrar = new JButton("Registrar");

        add(new JLabel("Nombre del jugador:"));
        add(txtNombre);
        add(btnColor);
        add(btnAvatar);
        add(btnRegistrar);

        btnColor.addActionListener(e -> colorSeleccionado = JColorChooser.showDialog(this, "Color", colorSeleccionado));
        btnAvatar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("Avatares/");
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                rutaAvatarSeleccionado = chooser.getSelectedFile().getAbsolutePath();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getColorHex() {
        return "#" + Integer.toHexString(colorSeleccionado.getRGB()).substring(2);
    }

    public String getRutaAvatar() {
        return rutaAvatarSeleccionado;
    }

    @Override
    public void actualizar(ModeloRegistro modelo) {
        txtNombre.setText(modelo.getNombre());
    }
}
