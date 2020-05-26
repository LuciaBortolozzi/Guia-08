package model;

import java.util.Calendar;
import java.util.TreeSet;

public class Series extends Audiovisuales {
    private int temporada;
    private int episodio;

    public Series() {
    }

    public Series(int temporada, int episodio) {
        this.temporada = temporada;
        this.episodio = episodio;
    }

    public Series(int codigo, String nombre, Generos genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
    }

    public Series(int codigo, String nombre, Generos genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores, int temporada, int episodio) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
        this.temporada = temporada;
        this.episodio = episodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getEpisodio() {
        return episodio;
    }

    public void setEpisodio(int episodio) {
        this.episodio = episodio;
    }

    @Override
    public double calculoMontoTotal() {
        return 0;
    }
}
