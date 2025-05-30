package mvcLobby;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego;
import com.mycompany.timbirichenetwork.eventos.EventoJugadorListo;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

public class ModeloLobbyJuego {

    private final List<Jugador> jugadores = new ArrayList<>();

    public static ModeloLobbyJuego obtenerOInicializar(Blackboard blackboard) {
        return blackboard.obtenerEstado(ModeloLobbyJuego.class).orElseGet(() -> {
            ModeloLobbyJuego nuevo = new ModeloLobbyJuego();
            blackboard.publicar(nuevo);
            return nuevo;
        });
    }

    public static ModeloLobbyJuego inicializarYPublicar(Blackboard blackboard) {
        ModeloLobbyJuego nuevo = new ModeloLobbyJuego();
        blackboard.publicar(nuevo);
        return nuevo;
    }

    public void agregarJugador(Jugador jugador, Blackboard blackboard, Cliente cliente) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        blackboard.publicar(this);
        cliente.enviarEvento(new EventoJugadorListo(jugador));
    }

    public void agregarJugadorDesdeRed(Jugador jugador, Blackboard blackboard) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        blackboard.publicar(this);
    }

    public void solicitarInicioJuego(Blackboard blackboard, Cliente cliente, int tamañoTablero) {
        if (puedeIniciar()) {
            EventoIniciarJuego evento = new EventoIniciarJuego(jugadores, tamañoTablero);
            blackboard.publicarEvento(evento);
            cliente.enviarEvento(evento);
        }
    }

    public boolean puedeIniciar() {
        return jugadores.size() >= 2 && jugadores.stream().allMatch(Jugador::isListo);
    }

    public void actualizarJugador(Jugador jugador, Blackboard blackboard, Cliente cliente) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        blackboard.publicar(this);
        cliente.enviarEvento(new EventoJugadorListo(jugador));
    }

    public void editarPerfilJugador(Jugador jugador, Blackboard blackboard) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        blackboard.publicar(this);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
