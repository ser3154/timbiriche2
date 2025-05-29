package mvcLobby;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego;
import com.mycompany.timbirichenetwork.eventos.EventoJugadorListo;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.List;

public class ControladorLobbyJuego {

    private final ModeloLobbyJuego modelo;
    private final VistaLobby vista;
    private final Cliente cliente;
    private final Jugador jugadorLocal;
    private final int tamañoTablero;

    public ControladorLobbyJuego(Jugador jugadorHost, int tamañoTablero, Cliente cliente) {
        this.tamañoTablero = tamañoTablero;
        this.cliente = cliente;
        this.jugadorLocal = jugadorHost;
        this.vista = new VistaLobby();

        Blackboard bb = Blackboard.getInstancia();
        this.modelo = bb.obtenerEstado(ModeloLobbyJuego.class).orElseGet(() -> {
            ModeloLobbyJuego nuevo = new ModeloLobbyJuego();
            bb.publicar(nuevo);
            return nuevo;
        });

        bb.registrar(ModeloLobbyJuego.class, vista);

        modelo.agregarJugador(jugadorHost);
        bb.publicar(modelo);

        EventoJugadorListo evento = new EventoJugadorListo(jugadorHost);
        bb.publicarEvento(evento);
        cliente.enviarEvento(evento);

        vista.getBtnIniciar().addActionListener(e -> iniciarPartidaSiEsPosible());
        vista.getBtnEditarPerfil().addActionListener(e -> editarPerfil());
    }

    private void iniciarPartidaSiEsPosible() {
        List<Jugador> jugadores = modelo.getJugadores();

        if (modelo.puedeIniciar()) {
            vista.dispose();
            EventoIniciarJuego evento = new EventoIniciarJuego(jugadores, tamañoTablero);
            Blackboard.getInstancia().publicarEvento(evento);
            cliente.enviarEvento(evento);
        }
    }

    private void editarPerfil() {
        new mvcEditarPerfil.ControladorEditarPerfil(jugadorLocal);
        jugadorLocal.setListo(true);
        Blackboard.getInstancia().publicar(modelo);

        EventoJugadorListo evento = new EventoJugadorListo(jugadorLocal);
        Blackboard.getInstancia().publicarEvento(evento);
        cliente.enviarEvento(evento);
    }
}
