package mvcEditarPerfil;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ModeloLobbyJuego;

public class ControladorEditarPerfil {

    private final VistaEditarPerfil vista;
    private final Blackboard blackboard;

    public ControladorEditarPerfil(Jugador jugadorOriginal) {
        this.vista = new VistaEditarPerfil(jugadorOriginal);
        this.blackboard = Blackboard.getInstancia();

        vista.setPerfilEditadoListener(jugadorActualizado -> {
            blackboard.obtenerEstado(ModeloLobbyJuego.class).ifPresent(modeloLobby -> {
                modeloLobby.editarPerfilJugador(jugadorActualizado, blackboard);
            });
        });
    }
}