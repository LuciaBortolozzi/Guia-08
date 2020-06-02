package model.DAO;

import java.io.*;
import java.util.*;

import model.*;
import model.DAO.*;

public class AudiovisualesTXT {

    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
//    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    //Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + duracion + anio)
    //Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + temporada + episodio)
    public static ArrayList<Audiovisuales> bajarAudiovisualesTXT(ArrayList<Generos> generos) throws Exception {

        ArrayList<Audiovisuales> audiovisuales = new ArrayList<Audiovisuales>();

        try {

            File archaudiovisual = new File(directorio + "Audiovisuales.txt");

            if (archaudiovisual.exists()) {

                Scanner leerArchivo = new Scanner(archaudiovisual);
                ArrayList<String> audiovisualST = new ArrayList<String>();

                while (leerArchivo.hasNext()) {
                    String lineaActual = leerArchivo.nextLine();
                    audiovisualST.add(lineaActual);
                }

                int i = 0;
                for (String audi : audiovisualST) {
                    String tipo = audi.substring(0, 2);
                    if (tipo.equals("06")) {
                        audiovisuales.add(new Peliculas());
                        ((Peliculas) audiovisuales.get(i)).setDuracion(Integer.parseInt(audi.substring(119, 122)));
                        ((Peliculas) audiovisuales.get(i)).setAnioFilm(Integer.parseInt(audi.substring(122, 126)));
                    } else if (tipo.equals("07")) {
                        audiovisuales.add(new Series());
                        ((Series) audiovisuales.get(i)).setTemporada(Integer.parseInt(audi.substring(119, 123).trim()));
                        ((Series) audiovisuales.get(i)).setEpisodio(Integer.parseInt(audi.substring(123, 126).trim()));
                    } else {
                        break;
                    }
                    audiovisuales.get(i).setCodigo(Integer.parseInt(audi.substring(2, 6).trim()));
                    audiovisuales.get(i).setNombre(audi.substring(6, 56).trim());

                    Generos genero;
                    Iterator<Generos> gen = generos.iterator();
                    while (gen.hasNext()) {
                        genero = gen.next();
                        if (genero.getCodigo() == Integer.parseInt(audi.substring(56, 59).trim())) {
                            audiovisuales.get(i).setGenero(genero);
                            break;
                        }
                    }
                    
                    audiovisuales.get(i).setSinopsis(audi.substring(59, 109).trim());
                    Calendar fechaPublicacion = Calendar.getInstance();
                    fechaPublicacion.set(Calendar.DAY_OF_MONTH, Integer.parseInt(audi.substring(109, 111)));
                    fechaPublicacion.set(Calendar.MONTH, (Integer.parseInt(audi.substring(112, 114).replace(" ", ""))) - 1);
                    fechaPublicacion.set(Calendar.YEAR, Integer.parseInt(audi.substring(115, 119)));
                    audiovisuales.get(i).setFechaPubli(fechaPublicacion);

                    i++;
                }

                leerArchivo.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo Audiovisuales.txt ");
        }

        return audiovisuales;
    }

    public static void grabarAudiovisualesTXT(ArrayList<Audiovisuales> audiovisuales) {

        try {
            File fichero = new File(directorio + "Audiovisuales.txt");

            if (fichero.exists()) {
                PrintWriter archivoSalida = new PrintWriter(fichero);

                for (Audiovisuales audi : audiovisuales) {

                    if (audi instanceof Peliculas) {


                        //Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + duracion + anio)
                        archivoSalida.println(String.format("%2s", "06")
                                + String.format("%4s", audi.getCodigo())
                                + String.format("%50s", audi.getNombre())
                                + String.format("%3s", audi.getGenero().getCodigo())
                                + String.format("%50s", audi.getSinopsis())
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.DAY_OF_MONTH))
                                + "/"
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.MONTH))
                                + "/"
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.YEAR))
                                + String.format("%4s", ((Peliculas) audi).getDuracion())
                                + String.format("%4s", ((Peliculas) audi).getAnioFilm())
                        );

                    } else if (audi instanceof Series) {


                        //Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + temporada + episodio)
                        archivoSalida.println(String.format("%2s", "07")
                                + String.format("%4s", audi.getCodigo()
                                + String.format("%50s", audi.getNombre())
                                + String.format("%3s", audi.getGenero().getCodigo())
                                + String.format("%50s", audi.getSinopsis())
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.DAY_OF_MONTH))
                                + "/"
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.MONTH))
                                + "/"
                                + String.format("%2s", audi.getFechaPubli().get(Calendar.YEAR))
                                + String.format("%3s", ((Series) audi).getTemporada())
                                + String.format("%3s", ((Series) audi).getEpisodio()))
                        );

                    }
                }

                archivoSalida.close();
            }

        } catch (Exception e3) {
            System.out.println("No se puede grabar el archivo de Audiovisuales.txt");
        }
    }
}
