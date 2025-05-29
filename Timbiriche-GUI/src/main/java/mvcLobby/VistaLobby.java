package mvcLobby;

import blackboard.IV;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaLobby extends JFrame implements IV<ModeloLobbyJuego> {

    private final JPanel panelJugadores;
    private final JButton btnIniciar;
    private final JButton btnEditarPerfil;

    public VistaLobby() {
        setTitle("Lobby");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
        add(new JScrollPane(panelJugadores), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnIniciar = new JButton("Iniciar");
        btnEditarPerfil = new JButton("Editar Perfil");
        panelBotones.add(btnEditarPerfil);
        panelBotones.add(btnIniciar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnEditarPerfil() {
        return btnEditarPerfil;
    }

    @Override
    public void actualizar(ModeloLobbyJuego modelo) {
        panelJugadores.removeAll();
        for (Jugador j : modelo.getJugadores()) {
            JLabel lbl = new JLabel(j.getNombre() + (j.isListo() ? " ✓" : " ..."));
            lbl.setForeground(Color.decode(j.getColorHex()));
            panelJugadores.add(lbl);
        }
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }
}
