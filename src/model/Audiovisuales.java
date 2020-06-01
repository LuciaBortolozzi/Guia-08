package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

public abstract class Audiovisuales implements ICalculable{
    protected int codigo;
    protected String nombre;
    protected Generos genero;
    protected String sinopsis;
    protected Calendar fechaPubli;
    protected TreeSet<Actores> actores;
    protected ArrayList<Calificaciones> calificaciones = new ArrayList<Calificaciones>();
    protected CronogramaPagos cronogramaPagos;

    public Audiovisuales() {
        this.cronogramaPagos = new CronogramaPagos();
    }

    public Audiovisuales(int codigo, String nombre, Generos genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.fechaPubli = fechaPubli;
        this.actores = actores;
        this.cronogramaPagos = new CronogramaPagos();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Calendar getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(Calendar fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public TreeSet<Actores> getActores() {
        return actores;
    }

    public void setActores(TreeSet<Actores> actores) {
        this.actores = actores;
    }

    public ArrayList<Calificaciones> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(int estrellas, String motivo, Calendar fechaRealizada, Suscriptores suscriptor) {
        calificaciones.add(new Calificaciones(estrellas, motivo, fechaRealizada, suscriptor));
    }

    public void setCalificaciones(int indice, int estrellas, String motivo, Calendar fechaRealizada, Suscriptores suscriptor) {
        calificaciones.get(indice).setEstrellas(estrellas);
        calificaciones.get(indice).setMotivo(motivo);
        calificaciones.get(indice).setFechaRealizada(fechaRealizada);
        calificaciones.get(indice).setSuscriptor(suscriptor);
    }

    public CronogramaPagos getCronogramaPagos() {
        return cronogramaPagos;
    }

    public void setCronogramaPagos(int indice, Calendar fechaDePago, double monto) {
        cronogramaPagos.setFechaDePago(fechaDePago);
        cronogramaPagos.setMonto(monto);
    }
}
