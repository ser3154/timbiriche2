package mvcRegistro;

public class ModeloRegistro {

    private String nombre;
    private String colorHex;
    private String rutaAvatar;

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

    public boolean datosValidos() {
        return nombre != null && !nombre.trim().isEmpty()
                && colorHex != null && !colorHex.isEmpty()
                && rutaAvatar != null && !rutaAvatar.isEmpty();
    }
}
