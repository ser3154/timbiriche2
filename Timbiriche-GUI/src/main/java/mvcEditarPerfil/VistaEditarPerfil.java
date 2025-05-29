package mvcEditarPerfil;

import com.mycompany.timbirichenetwork.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VistaEditarPerfil extends JFrame {

    private final JTextField txtNombre;
    private final JButton btnSeleccionarAvatar;
    private final JButton btnAceptar;
    private final JButton btnCancelar;
    private final JLabel lblAvatar;
    private String rutaAvatarSeleccionado;
    private PerfilEditadoListener listener;
    private Jugador jugador;

    public VistaEditarPerfil(Jugador jugadorOriginal) {
        this.jugador = jugadorOriginal;

        setTitle("Editar Perfil");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        txtNombre = new JTextField(jugadorOriginal.getNombre());
        lblAvatar = new JLabel("Avatar actual", SwingConstants.CENTER);
        lblAvatar.setIcon(jugadorOriginal.getAvatar());

        btnSeleccionarAvatar = new JButton("Cambiar Avatar");
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Nombre:", SwingConstants.CENTER));
        add(txtNombre);
        add(lblAvatar);
        add(btnSeleccionarAvatar);
        add(btnAceptar);
        add(btnCancelar);

        rutaAvatarSeleccionado = jugadorOriginal.getRutaAvatar();

        btnSeleccionarAvatar.addActionListener(e -> seleccionarAvatar());
        btnCancelar.addActionListener(e -> dispose());
        btnAceptar.addActionListener(e -> aceptarCambios());

        setVisible(true);
    }

    private void seleccionarAvatar() {
        JFileChooser chooser = new JFileChooser("Avatares/");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            rutaAvatarSeleccionado = chooser.getSelectedFile().getAbsolutePath();
            lblAvatar.setIcon(new ImageIcon(rutaAvatarSeleccionado));
        }
    }

    private void aceptarCambios() {
        String nuevoNombre = txtNombre.getText().trim();
        if (nuevoNombre.isEmpty() || rutaAvatarSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa nombre y avatar.");
            return;
        }

        jugador.setNombre(nuevoNombre);
        jugador.setRutaAvatar(rutaAvatarSeleccionado);

        if (listener != null) {
            listener.perfilEditado(jugador);
        }

        dispose();
    }

    public void setPerfilEditadoListener(PerfilEditadoListener listener) {
        this.listener = listener;
    }
}
