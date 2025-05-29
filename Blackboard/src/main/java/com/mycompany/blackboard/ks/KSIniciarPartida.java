package com.mycompany.blackboard.ks;

import com.mycompany.timbirichenetwork.eventos.EventoIniciarPartida;

public class KSIniciarPartida {
    public void procesar(EventoIniciarPartida evento) {
        // Lógica según los diagramas (si aplica). Actualmente solo notifica.
        System.out.println("EventoIniciarPartida recibido. aceptadoPorTodos=" + evento.isAceptadoPorTodos());
    }
}
