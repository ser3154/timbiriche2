package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.KnowledgeSourceBase;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.List;

public class KSEvaluarJugadoresListos extends KnowledgeSourceBase {
    
    private java.util.List<com.mycompany.timbirichenetwork.modelo.Jugador> jugadoresAEvaluar;
    
    public boolean validar(java.util.List<com.mycompany.timbirichenetwork.modelo.Jugador> jugadores) {
        this.jugadoresAEvaluar = jugadores;
        ejecutar();
        return control.getContexto("validacionResultado", Boolean.class) != null ? 
               control.getContexto("validacionResultado", Boolean.class) : false;
    }
    
    @Override
    protected boolean puedeEjecutar() {
        return jugadoresAEvaluar != null && !jugadoresAEvaluar.isEmpty();
    }
    
    @Override
    protected void procesar() {
        long listos = jugadoresAEvaluar.stream()
            .filter(com.mycompany.timbirichenetwork.modelo.Jugador::isListo)
            .count();
        
        boolean resultado = listos >= 2 && listos <= 4;
        
        // Guardar resultado en contexto para que pueda ser recuperado
        control.setContexto("validacionResultado", resultado);
        control.setContexto("jugadoresListos", listos);
        
        System.out.println("Evaluación: " + listos + " jugadores listos. Puede iniciar: " + resultado);
    }
}