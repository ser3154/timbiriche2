package mvcJuego;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

public class ModeloJuego {

    private final List<Jugador> jugadores;
    private final int tamañoTablero;
    private final int[][] tablero;
    private int jugadorActual = 0;

    public ModeloJuego(List<Jugador> jugadores, int tamañoTablero) {
        this.jugadores = new ArrayList<>(jugadores);
        this.tamañoTablero = tamañoTablero;
        this.tablero = new int[tamañoTablero][tamañoTablero];
    }

    // Método según diagrama - el modelo inicializa y notifica
    public void inicializarJuego(Blackboard blackboard, Cliente cliente) {
        if (jugadores.size() < 2) {
            System.out.println("[ModeloJuego] No hay suficientes jugadores para iniciar");
            return;
        }

        System.out.println("[ModeloJuego] Inicializando juego con " + jugadores.size() + " jugador(es)");
        blackboard.publicar(this);
    }

    // Getters y métodos existentes...
    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }

    public int[][] getTablero() {
        int[][] copia = new int[tamañoTablero][tamañoTablero];
        for (int i = 0; i < tamañoTablero; i++) {
            System.arraycopy(tablero[i], 0, copia[i], 0, tamañoTablero);
        }
        return copia;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(jugadorActual);
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual + 1) % jugadores.size();
    }

    public void marcarCasilla(int fila, int columna, int idJugador) {
        if (fila >= 0 && fila < tamañoTablero && columna >= 0 && columna < tamañoTablero) {
            tablero[fila][columna] = idJugador;
        }
    }
}
