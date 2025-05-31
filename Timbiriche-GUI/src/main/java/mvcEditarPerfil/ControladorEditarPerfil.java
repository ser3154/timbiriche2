package mvcEditarPerfil;

import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ModeloLobbyJuego;

public class ControladorEditarPerfil {

    private final VistaEditarPerfil vista;

    public ControladorEditarPerfil(Jugador jugadorOriginal) {
        this.vista = new VistaEditarPerfil(jugadorOriginal);

        vista.setPerfilEditadoListener(jugadorActualizado -> {
            ModeloLobbyJuego.obtenerOInicializar().editarPerfilJugador(jugadorActualizado);
        });
    }
}