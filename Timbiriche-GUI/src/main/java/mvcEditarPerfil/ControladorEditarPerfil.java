package mvcEditarPerfil;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ModeloLobbyJuego;

public class ControladorEditarPerfil {

    private final VistaEditarPerfil vista;

    public ControladorEditarPerfil(Jugador jugadorOriginal) {
        this.vista = new VistaEditarPerfil(jugadorOriginal);

        vista.setPerfilEditadoListener(jugadorActualizado -> {
            System.out.println("Perfil actualizado: " + jugadorActualizado.getNombre());
            
            // Actualizar en el modelo de lobby si está disponible
            Blackboard.getInstancia().obtenerEstado(ModeloLobbyJuego.class).ifPresent(modeloLobby -> {
                modeloLobby.actualizarJugador(jugadorActualizado);
                Blackboard.getInstancia().publicar(modeloLobby);
            });
        });
    }
}