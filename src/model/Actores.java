package model;

public class Actores implements Comparable<Actores>{

    private String nombre;
    private String apellido;
    private char sexo;

    public Actores() {
    }

    public Actores(String nombre, String apellido, char sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public int compareTo(Actores a) {
        int lastCmp = apellido.compareTo(a.apellido);                   // Primer parametro
        return (lastCmp != 0 ? lastCmp : nombre.compareTo(a.nombre));   // Segundo parametro
    }
}
