/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcEditarPerfil;



/**
 *
 * @author joseq
 */
public class ModeloEditarPerfil {

    private String nuevoNombre;
    private String nuevaRutaAvatar;

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getNuevaRutaAvatar() {
        return nuevaRutaAvatar;
    }

    public void setNuevaRutaAvatar(String nuevaRutaAvatar) {
        this.nuevaRutaAvatar = nuevaRutaAvatar;
    }

    public boolean datosValidos() {
        return nuevoNombre != null && !nuevoNombre.trim().isEmpty()
                && nuevaRutaAvatar != null && !nuevaRutaAvatar.trim().isEmpty();
    }
}
