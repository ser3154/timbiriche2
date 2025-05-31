package mvcTamanoTablero;

import com.mycompany.blackboard.Blackboard;

public class ModeloTamanoTablero {
    private int tamañoSeleccionado;

    public int getTamañoSeleccionado() {
        return tamañoSeleccionado;
    }

    public void setTamañoSeleccionado(int tamañoSeleccionado) {
        this.tamañoSeleccionado = tamañoSeleccionado;
    }

    // Método alineado al diseño: modelo gestiona publicación
    public void configurarPartida(int tamaño) {
        this.tamañoSeleccionado = tamaño;
        Blackboard.getInstancia().publicar(this);
    }
}
