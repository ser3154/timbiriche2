package mvcLobby;

import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;

public class ControladorLobbyJuego {

    private final ModeloLobbyJuego modelo;
    private final VistaLobby vista;
    private final Cliente cliente;
    private final Jugador jugadorLocal;
    private final int tamañoTablero;

    public ControladorLobbyJuego(ModeloLobbyJuego modelo, VistaLobby vista, Cliente cliente, Jugador jugadorHost, int tamañoTablero) {
        this.modelo = modelo;
        this.vista = vista;
        this.cliente = cliente;
        this.jugadorLocal = jugadorHost;
        this.tamañoTablero = tamañoTablero;

        vista.getBtnIniciar().addActionListener(e -> solicitarInicioJuego());
        vista.getBtnEditarPerfil().addActionListener(e -> editarPerfil());
    }

    private void solicitarInicioJuego() {
        modelo.solicitarInicioJuego(cliente, tamañoTablero);
    }

    private void editarPerfil() {
        new mvcEditarPerfil.ControladorEditarPerfil(jugadorLocal);
        modelo.actualizarJugador(jugadorLocal, cliente);
    }
}
