package mvcTamanoTablero;

import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ControladorLobbyJuego;
import mvcLobby.ModeloLobbyJuego;
import mvcLobby.VistaLobby;

import javax.swing.*;

public class ControladorTamanoTablero {
    private final ModeloTamanoTablero modelo;
    private final VistaTamanoTablero vista;
    private final Jugador jugador;
    private final Cliente cliente;

    public ControladorTamanoTablero(Jugador jugador, Cliente cliente) {
        this.jugador = jugador;
        this.cliente = cliente;
        this.modelo = new ModeloTamanoTablero();
        this.vista = new VistaTamanoTablero();

        vista.getBtnContinuar().addActionListener(e -> configurarPartida());
    }

    private void configurarPartida() {
        int tamanoSeleccionado = vista.getTamañoSeleccionado();

        if (tamanoSeleccionado <= 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un tamaño válido.");
            return;
        }

        modelo.configurarPartida(tamanoSeleccionado);
        vista.dispose();

        VistaLobby vistaLobby = new VistaLobby();
        ModeloLobbyJuego modeloLobby = ModeloLobbyJuego.obtenerOInicializar();
        modeloLobby.agregarJugador(jugador, cliente);
        modeloLobby.registrarVista(vistaLobby);

        new ControladorLobbyJuego(modeloLobby, vistaLobby, cliente, jugador, tamanoSeleccionado);
    }
}
