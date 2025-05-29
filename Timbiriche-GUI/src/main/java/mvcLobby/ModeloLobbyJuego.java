package mvcLobby;

import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModeloLobbyJuego {

    private final List<Jugador> jugadores = new ArrayList<>();

    public void agregarJugador(Jugador jugador) {
        boolean existe = jugadores.stream()
            .anyMatch(j -> j.getNombre().trim().equalsIgnoreCase(jugador.getNombre().trim()));

        if (!existe) {
            jugadores.add(jugador);
        } else {
            actualizarJugador(jugador);
        }
    }

    public void actualizarJugador(Jugador jugador) {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            if (j.getNombre().trim().equalsIgnoreCase(jugador.getNombre().trim())) {
                jugadores.set(i, jugador);
                break;
            }
        }
    }

    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    public boolean puedeIniciar() {
        long listos = jugadores.stream().filter(Jugador::isListo).count();
        return listos >= 2 && listos <= 4;
    }
}
