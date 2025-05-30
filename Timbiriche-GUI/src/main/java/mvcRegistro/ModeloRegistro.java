package mvcRegistro;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;

public class ModeloRegistro {

    private String nombre;
    private String colorHex;
    private String rutaAvatar;
    private final Blackboard blackboard;

    public ModeloRegistro() {
        this.blackboard = Blackboard.getInstancia();
    }

    public boolean datosValidos() {
        return nombre != null && !nombre.trim().isEmpty()
                && colorHex != null && !colorHex.isEmpty()
                && rutaAvatar != null && !rutaAvatar.isEmpty();
    }

    public void registrarJugador(Cliente cliente) {
        if (datosValidos()) {
            Jugador jugador = new Jugador(nombre, colorHex, rutaAvatar, true);
            blackboard.publicar(jugador);
            System.out.println("[ModeloRegistro] Jugador publicado en Blackboard: " + jugador.getNombre());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getRutaAvatar() {
        return rutaAvatar;
    }

    public void setRutaAvatar(String rutaAvatar) {
        this.rutaAvatar = rutaAvatar;
    }
}
