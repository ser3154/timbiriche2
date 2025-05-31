package mvcLobby;

import blackboard.IV;
import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego;
import com.mycompany.timbirichenetwork.eventos.EventoJugadorListo;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ModeloLobbyJuego {

    private final List<Jugador> jugadores = new ArrayList<>();

    // Obtener o inicializar el modelo desde el Blackboard
    public static ModeloLobbyJuego obtenerOInicializar() {
        Blackboard bb = Blackboard.getInstancia();
        return bb.obtenerEstado(ModeloLobbyJuego.class).orElseGet(() -> {
            ModeloLobbyJuego nuevo = new ModeloLobbyJuego();
            bb.publicar(nuevo);
            return nuevo;
        });
    }

    // Alternativa para KS: inicializar y publicar directamente
    public static ModeloLobbyJuego inicializarYPublicar(Blackboard bb) {
        ModeloLobbyJuego nuevo = new ModeloLobbyJuego();
        bb.publicar(nuevo);
        return nuevo;
    }

    // Registrar una vista como observadora
    public void registrarVista(IV<ModeloLobbyJuego> vista) {
        Blackboard.getInstancia().registrar(ModeloLobbyJuego.class, vista);
    }

    // Agregar un jugador local (desde flujo normal)
    public void agregarJugador(Jugador jugador, Cliente cliente) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        Blackboard.getInstancia().publicar(this);
        cliente.enviarEvento(new EventoJugadorListo(jugador));
    }

    // Agregar un jugador desde la red (usado en KS)
    public void agregarJugadorDesdeRed(Jugador jugador, Blackboard bb) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        bb.publicar(this);
    }

    // Actualizar datos de un jugador local (por ejemplo, después de editar perfil)
    public void actualizarJugador(Jugador jugador, Cliente cliente) {
        jugadores.removeIf(j -> j.getNombre().equals(jugador.getNombre()));
        jugadores.add(jugador);
        Blackboard.getInstancia().publicar(this);
        cliente.enviarEvento(new EventoJugadorListo(jugador));
    }

    // Aplicar cambios de perfil sin reenviar evento (usado en KS)
    public void editarPerfilJugador(Jugador jugadorActualizado) {
        jugadores.removeIf(j -> j.getNombre().equals(jugadorActualizado.getNombre()));
        jugadores.add(jugadorActualizado);
        Blackboard.getInstancia().publicar(this);
    }

    // Iniciar el juego si todos los jugadores están listos
    public void solicitarInicioJuego(Cliente cliente, int tamañoTablero) {
        if (puedeIniciar()) {
            EventoIniciarJuego evento = new EventoIniciarJuego(jugadores, tamañoTablero);
            Blackboard.getInstancia().publicarEvento(evento);
            cliente.enviarEvento(evento);
        }
    }

    // Verificar si todos los jugadores están listos y son al menos dos
    public boolean puedeIniciar() {
        return jugadores.size() >= 2 && jugadores.stream().allMatch(Jugador::isListo);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
