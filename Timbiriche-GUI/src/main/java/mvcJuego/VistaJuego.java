package mvcJuego;

import blackboard.IV;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaJuego extends JFrame implements IV<ModeloJuego> {

    private final JPanel panelTablero;
    private final JPanel panelJugadores;
    private ModeloJuego modelo;

    public VistaJuego() {
        setTitle("Timbiriche");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelJugadores = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Jugadores"));
        add(panelJugadores, BorderLayout.NORTH);

        panelTablero = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panelTablero);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actualizar(ModeloJuego modelo) {
        if (modelo == null) return;
        this.modelo = modelo;

        actualizarJugadores(modelo.getJugadores());
        construirTablero(modelo.getTamañoTablero());
    }

    private void actualizarJugadores(List<Jugador> jugadores) {
        panelJugadores.removeAll();
        for (Jugador j : jugadores) {
            JLabel lbl = new JLabel(j.getNombre());
            lbl.setForeground(Color.decode(j.getColorHex()));
            panelJugadores.add(lbl);
        }
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }

    private void construirTablero(int tamaño) {
        panelTablero.removeAll();
        panelTablero.setLayout(new GridLayout(tamaño, tamaño));

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                JPanel celda = new JPanel();
                celda.setBackground(Color.WHITE);
                celda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                panelTablero.add(celda);
            }
        }

        panelTablero.revalidate();
        panelTablero.repaint();
    }
}
