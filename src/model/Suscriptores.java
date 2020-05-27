package model;

import java.util.Calendar;

public class Suscriptores implements Comparable<Suscriptores>{
    private long nroDoc;
    private String nombre;
    private String apellido;
    private Calendar fechaDeNac;
    private char sexo;

    public Suscriptores() {
    }

    public Suscriptores(long nroDoc, String nombre, String apellido, Calendar fechaDeNac, char sexo) {
        this.nroDoc = nroDoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNac = fechaDeNac;
        this.sexo = sexo;
    }

    public long getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(long nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Calendar getFechaDeNac() {
        return fechaDeNac;
    }

    public void setFechaDeNac(Calendar fechaDeNac) {
        this.fechaDeNac = fechaDeNac;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public int compareTo(Suscriptores p) {
        return apellido.compareTo(p.apellido);      // orden natural: apellido
    }
}
