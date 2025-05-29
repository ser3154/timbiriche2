/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard;

/**
 *
 * @author Serva
 */
public abstract class KnowledgeSourceBase implements KnowledgeSource {
    
    protected final Blackboard blackboard;
    protected final ControlBlackboard control;
    
    public KnowledgeSourceBase() {
        this.blackboard = Blackboard.getInstancia();
        this.control = ControlBlackboard.getInstancia();
    }
    
    /**
     * Método template que define el flujo de ejecución de un KS
     */
    @Override
    public final void ejecutar() {
        try {
            if (puedeEjecutar()) {
                procesar();
                postProcesar();
            }
        } catch (Exception e) {
            manejarError(e);
        }
    }
    
    /**
     * Verifica si el KS puede ejecutarse en el contexto actual
     */
    protected boolean puedeEjecutar() {
        return true; // Por defecto siempre puede ejecutar
    }
    
    /**
     * Lógica principal del Knowledge Source - debe ser implementada por subclases
     */
    protected abstract void procesar();
    
    /**
     * Acciones posteriores al procesamiento principal
     */
    protected void postProcesar() {
        // Implementación por defecto vacía
    }
    
    /**
     * Manejo de errores durante la ejecución
     */
    protected void manejarError(Exception e) {
        System.err.println("Error en " + this.getClass().getSimpleName() + ": " + e.getMessage());
        e.printStackTrace();
    }
    
    /**
     * Método utilitario para obtener estado del blackboard de forma segura
     */
    protected <T> T obtenerEstadoSeguro(Class<T> tipo) {
        return blackboard.obtenerEstado(tipo).orElse(null);
    }
    
    /**
     * Método utilitario para publicar en el blackboard
     */
    protected <T> void publicarEstado(T estado) {
        if (estado != null) {
            blackboard.publicar(estado);
        }
    }
}
