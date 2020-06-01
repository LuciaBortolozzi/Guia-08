package model;

public interface ICalculable {
    //atributos public static final
    double DER_PELICULA = 0.60;
    double DER_SERIE = 0.30;
    double DER_MINISERIE = 0.45;

    //metodos public abstract
    double calculoMontoTotalPeliculas();
    double calculoMontoTotalSeriesMasDoce();
    double calculoMontoTotalSeriesMenosDoce();
}
