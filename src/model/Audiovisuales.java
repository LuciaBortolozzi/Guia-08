package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

public abstract class Audiovisuales implements ICalculable{
    protected int codigo;
    protected String nombre;
    protected String genero;
    protected String sinopsis;
    protected Calendar fechaPubli;
    protected TreeSet<Actores> actores;
    protected ArrayList<Calificaciones> calificaciones = new ArrayList<Calificaciones>();
    protected ArrayList<CronogramaPagos> pagos = new ArrayList<CronogramaPagos>();

    public Audiovisuales() {
    }

    public Audiovisuales(int codigo, String nombre, String genero, String sinopsis, Calendar fechaPubli, TreeSet<Actores> actores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.fechaPubli = fechaPubli;
        this.actores = actores;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

    public ArrayList<CronogramaPagos> getPagos() {
        return pagos;
    }

    public void setPagos(Calendar fechaDePago, double monto) {
        pagos.add(new CronogramaPagos(fechaDePago, monto));
    }

    public void setPagos(int indice, Calendar fechaDePago, double monto) {
        pagos.get(indice).setFechaDePago(fechaDePago);
        pagos.get(indice).setMonto(monto);
    }
}
