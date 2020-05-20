package model;

import java.util.Calendar;

public class Calificaciones {
    private int estrellas;
    private String motivo;
    private Calendar fechaRealizada;
    private Suscriptores suscriptor;

    public Calificaciones() {
    }

    public Calificaciones(int estrellas, String motivo, Calendar fechaRealizada, Suscriptores suscriptor) {
        this.estrellas = estrellas;
        this.motivo = motivo;
        this.fechaRealizada = fechaRealizada;
        this.suscriptor = suscriptor;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Calendar getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(Calendar fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public Suscriptores getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(Suscriptores suscriptor) {
        this.suscriptor = suscriptor;
    }
}
