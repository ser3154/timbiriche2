package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.KnowledgeSourceBase;
import com.mycompany.timbirichenetwork.eventos.EventoJugadorListo;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ModeloLobbyJuego;

public class KSEscucharJugadorListo extends KnowledgeSourceBase {
    
    private EventoJugadorListo eventoActual;
    
    public void procesar(EventoJugadorListo evento) {
        this.eventoActual = evento;
        ejecutar(); // Usar el método template de la clase base
    }
    
    @Override
    protected boolean puedeEjecutar() {
        return eventoActual != null && eventoActual.getJugador() != null;
    }
    
    @Override
    protected void procesar() {
        Jugador jugador = eventoActual.getJugador();
        jugador.setListo(true);

        // Obtener o crear modelo de lobby
        ModeloLobbyJuego modelo = obtenerEstadoSeguro(ModeloLobbyJuego.class);
        if (modelo == null) {
            modelo = new ModeloLobbyJuego();
        }

        // Actualizar modelo
        modelo.agregarJugador(jugador);
        
        // Publicar cambios
        publicarEstado(modelo);
        
        System.out.println("Jugador " + jugador.getNombre() + " marcado como listo");
    }
    
    @Override
    protected void postProcesar() {
        // Limpiar referencia al evento procesado
        this.eventoActual = null;
    }
}