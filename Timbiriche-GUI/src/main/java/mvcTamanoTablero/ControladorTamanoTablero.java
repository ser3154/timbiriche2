package mvcTamanoTablero;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ControladorLobbyJuego;
import javax.swing.*;

public class ControladorTamanoTablero {
    private final ModeloTamanoTablero modelo;
    private final VistaTamanoTablero vista;
    private final Jugador jugador;
    private final Cliente cliente;
    private final Blackboard blackboard;

    public ControladorTamanoTablero(Jugador jugador, Cliente cliente) {
        this.jugador = jugador;
        this.cliente = cliente;
        this.blackboard = Blackboard.getInstancia();
        this.modelo = new ModeloTamanoTablero();
        this.vista = new VistaTamanoTablero();
        vista.getBtnContinuar().addActionListener(e -> configurarPartida());
    }

    private void configurarPartida() {
        // Solicitar tamaño del tablero a la vista
        int tamanoSeleccionado = vista.getTamañoSeleccionado();

        if (tamanoSeleccionado <= 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un tamaño válido.");
            return;
        }

        // Interactuar con modelo para configurar partida
        modelo.configurarPartida(tamanoSeleccionado, blackboard, cliente);

        vista.dispose();
        // Continuar al lobby
        new ControladorLobbyJuego(jugador, modelo.getTamañoSeleccionado(), cliente);
    }
}