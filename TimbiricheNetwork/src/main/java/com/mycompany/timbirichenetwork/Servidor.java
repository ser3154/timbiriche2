package com.mycompany.timbirichenetwork;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Servidor {

    private final List<ObjectOutputStream> clientes = new CopyOnWriteArrayList<>();

    public Servidor(int puerto) {
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);

            while (true) {
                Socket clienteSocket = servidor.accept();
                ObjectOutputStream salida = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(clienteSocket.getInputStream());
                clientes.add(salida);
                new Thread(() -> manejarCliente(entrada)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void manejarCliente(ObjectInputStream entrada) {
        try {
            while (true) {
                Object recibido = entrada.readObject();
                if (recibido instanceof Evento evento) {
                    reenviarEvento(evento);
                }
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado.");
        }
    }

    private void reenviarEvento(Evento evento) {
        for (ObjectOutputStream out : clientes) {
            try {
                out.writeObject(evento);
                out.flush();
            } catch (Exception e) {
                System.out.println("Error al reenviar evento.");
            }
        }
    }
}
