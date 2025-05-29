/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Serva
 */
public class ControlBlackboard {

    private static ControlBlackboard instancia;
    private final Blackboard blackboard;
    private final ExecutorService executor;
    private final Map<Class<?>, List<KnowledgeSource>> knowledgeSources;
    private final Map<String, Object> contextData;

    private ControlBlackboard() {
        this.blackboard = Blackboard.getInstancia();
        this.executor = Executors.newCachedThreadPool();
        this.knowledgeSources = new ConcurrentHashMap<>();
        this.contextData = new ConcurrentHashMap<>();
    }

    public static ControlBlackboard getInstancia() {
        if (instancia == null) {
            synchronized (ControlBlackboard.class) {
                if (instancia == null) {
                    instancia = new ControlBlackboard();
                }
            }
        }
        return instancia;
    }

    /**
     * Registra un Knowledge Source para ser activado cuando cambie un tipo
     * específico
     */
    public synchronized void registrarKS(Class<?> tipoActivador, KnowledgeSource ks) {
        knowledgeSources.computeIfAbsent(tipoActivador, k -> new ArrayList<>()).add(ks);
    }

    /**
     * Evalúa y activa Knowledge Sources basado en cambios en el blackboard
     */
    public void evaluarActivacion(Object nuevoEstado) {
        if (nuevoEstado == null) {
            return;
        }

        Class<?> tipo = nuevoEstado.getClass();
        List<KnowledgeSource> ksParaEjecutar = knowledgeSources.get(tipo);

        if (ksParaEjecutar != null) {
            for (KnowledgeSource ks : ksParaEjecutar) {
                // Ejecutar de forma asíncrona para no bloquear
                executor.submit(() -> {
                    try {
                        ks.ejecutar();
                    } catch (Exception e) {
                        System.err.println("Error ejecutando KS: " + e.getMessage());
                    }
                });
            }
        }
    }

    /**
     * Almacena datos de contexto para uso de los KS
     */
    public void setContexto(String clave, Object valor) {
        contextData.put(clave, valor);
    }

    /**
     * Obtiene datos de contexto
     */
    @SuppressWarnings("unchecked")
    public <T> T getContexto(String clave, Class<T> tipo) {
        Object valor = contextData.get(clave);
        return valor != null ? tipo.cast(valor) : null;
    }

    /**
     * Limpia recursos
     */
    public void shutdown() {
        executor.shutdown();
        knowledgeSources.clear();
        contextData.clear();
    }
}
