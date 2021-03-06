package model.DAO;

import model.Audiovisuales;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class CronogramaPagosTXT {

    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
//    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static void grabarCronogramaPagosTXT(ArrayList<Audiovisuales> audiovisualesAux, int cantidadPublicaciones, double totalAbonar) {
        Calendar fechaActual = Calendar.getInstance();
        try {
            FileWriter fichero = new FileWriter(directorio + "CronogramaPagos" + fechaActual.get(Calendar.MONTH) + fechaActual.get(Calendar.YEAR) + ".txt");

                PrintWriter archivoSalida = new PrintWriter(fichero);
                String identificador = "01";
                String identificadorTotal = "03";
                for (Audiovisuales audiovisual : audiovisualesAux) {

                    //CronogramaPagos.txt -> (identificador + ";" + fechaDePago + ";" + nombrePublicacion + ";" + fechaPublicacion + ";" + monto )
                    archivoSalida.println(identificador + ";"
                            + audiovisual.getCronogramaPagos().getFechaDePago().get(Calendar.DAY_OF_MONTH) + "/"
                            + (audiovisual.getCronogramaPagos().getFechaDePago().get(Calendar.MONTH) +1)  + "/"
                            + audiovisual.getCronogramaPagos().getFechaDePago().get(Calendar.YEAR) + ";"
                            + audiovisual.getNombre() + ";"
                            + audiovisual.getFechaPubli().get(Calendar.DAY_OF_MONTH) + "/"
                            + (audiovisual.getFechaPubli().get(Calendar.MONTH) + 1) + "/"
                            + audiovisual.getFechaPubli().get(Calendar.YEAR) + ";"
                            + audiovisual.getCronogramaPagos().getMonto());


                }
                //CronogramaPagos.txt -> (identificadorTotal + ";" + cantidadPublicaciones + ";" + totalAbonar)
                archivoSalida.println(identificadorTotal + ";" + cantidadPublicaciones + ";" + totalAbonar);

                archivoSalida.close();

        } catch (IOException e) {
            System.out.println("No se puede grabar el archivo de CronogramaPagosmmdd.txt");
        }
    }

        /* AL FINAL ESTA FUNCI�N NO ES NECESARIA, LA DEJO PARA NO TENER QUE LLORAR
    //Calificaciones.txt -> (identificador + ";" + fechaDePago + ";" + nombrePublicacion + ";" + fechaPublicacion + ";" + monto )
    public static ArrayList<Audiovisuales> bajarCronogramas(ArrayList<Audiovisuales> audiovisuales) throws Exception {

        Calendar fechaMesAnterior = Calendar.getInstance();
        fechaMesAnterior = Validaciones.dosMesesAntes(fechaMesAnterior);

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Validaciones.ultimoMes(fechaActual);
        try {

            for (int i = 0; i < 11; i++) {

                File archivo = new File(directorio + "CronogramaPagos" + fechaAnterior.get(Calendar.MONTH) + fechaMesAnterior.get(Calendar.YEAR) + ".txt");
                if (archivo.exists()) {

                    Scanner leerArchivoCronogramaPagos = new Scanner(archivo);
                    ArrayList<String> cronogramaPagosST = new ArrayList<String>();

                    //Guardar contenido en String
                    while (leerArchivoCronogramaPagos.hasNext()) {
                        String lineaActualMES = leerArchivoCronogramaPagos.nextLine();
                        cronogramaPagosST.add(lineaActualMES);
                    }

                    // Guardar objetos
                    for (String s : cronogramaPagosST) {

                        String[] cronogramaST = s.split(";");

                        //01 para datos - 03 para totales

                        int identificador = Integer.parseInt(cronogramaST[0]);
                        if (identificador == 1) {

                            Calendar fechaPago = Validaciones.convertirAFechaCalendar(cronogramaST[1]);
                            String nombrePublicacion = cronogramaST[2];
                            Calendar fechaPublicacion = Validaciones.convertirAFechaCalendar(cronogramaST[3]);
                            double monto = Double.parseDouble(cronogramaST[4]);

                            Audiovisuales audiovisual = AudiovisualesControlador.buscarAudiovisualPorNombreyFecha(nombrePublicacion, audiovisuales);

                            if (audiovisual != null) {
                                audiovisual.setCronogramaPagos(fechaPago, monto);
                            }
                        }
                    }

                    leerArchivoCronogramaPagos.close();
                }
                fechaAnterior = Validaciones.ultimoMes(fechaAnterior);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return audiovisuales;
    }*/

}


