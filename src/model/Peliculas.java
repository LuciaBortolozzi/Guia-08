package model;

import java.util.Calendar;
import java.util.TreeSet;

public class Peliculas extends Audiovisuales {
    private int duracion;
    private Calendar fechaFilm;

    public Peliculas(int codigo, String nombre, String genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
    }

    public Peliculas(int codigo, String nombre, String genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores, int duracion, Calendar fechaFilm) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
        this.duracion = duracion;
        this.fechaFilm = fechaFilm;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Calendar getFechaFilm() {
        return fechaFilm;
    }

    public void setFechaFilm(Calendar fechaFilm) {
        this.fechaFilm = fechaFilm;
    }
}
