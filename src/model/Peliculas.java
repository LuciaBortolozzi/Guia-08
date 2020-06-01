package model;

import java.util.Calendar;
import java.util.TreeSet;

public class Peliculas extends Audiovisuales {
    private int duracion;
    private int anioFilm;

    public Peliculas() {
    }

    public Peliculas(int duracion, int anioFilm) {
        this.duracion = duracion;
        this.anioFilm = anioFilm;
    }

    public Peliculas(int codigo, String nombre, Generos genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
    }

    public Peliculas(int codigo, String nombre, Generos genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores, int duracion, int anioFilm) {
        super(codigo, nombre, genero, sinopsis, fechaPubli, actores);
        this.duracion = duracion;
        this.anioFilm = anioFilm;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAnioFilm() {
        return anioFilm;
    }

    public void setAnioFilm(int anioFilm) {
        this.anioFilm = anioFilm;
    }

    @Override
    public double calculoMontoTotalPeliculas() {
    	
    	double monto = cronogramaPagos.getMonto() * DER_PELICULA;
    	
        return monto;
    }

	@Override
	public double calculoMontoTotalSeriesMasDoce() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculoMontoTotalSeriesMenosDoce() {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
