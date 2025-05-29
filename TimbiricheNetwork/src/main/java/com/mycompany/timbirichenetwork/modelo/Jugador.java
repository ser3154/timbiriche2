/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork.modelo;

import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 *
 * @author joseq
 */
public class Jugador implements Serializable {

    private String nombre;
    private String colorHex;
    private String rutaAvatar;
    private transient ImageIcon avatar;
    private boolean listo;

    public Jugador(String nombre, String colorHex, String rutaAvatar, boolean listo) {
        this.nombre = nombre;
        this.colorHex = colorHex;
        this.rutaAvatar = rutaAvatar;
        this.avatar = new ImageIcon(rutaAvatar);
        this.listo = listo;
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
        this.avatar = new ImageIcon(rutaAvatar);
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Jugador)) {
            return false;
        }
        Jugador otro = (Jugador) obj;
        return this.nombre.trim().equalsIgnoreCase(otro.nombre.trim());
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }
}
