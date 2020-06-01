package model.DAO;

import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.Audiovisuales;
import model.Calificaciones;
import model.CronogramaPagos;
import view.Validaciones;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CronogramaPagosTXT {

        //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
        private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

        //Calificaciones.txt -> (identificador + ";" + fechaDePago + ";" + nombrePublicacion + ";" + fechaPublicacion + ";" + monto )
        public static ArrayList<Audiovisuales> bajarCronogramas() throws Exception {

            ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();
            Calendar fechaMesAnterior = Calendar.getInstance();
            fechaMesAnterior.add(Calendar.MONTH, -2);
            
            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaAnterior = Validaciones.ultimoMes(fechaActual);
            try {

            	for(int i = 0; i<11 ; i++) {
            		
            		File archivo = new File( directorio + "CronogramaPagos" + fechaAnterior + fechaMesAnterior.get(Calendar.YEAR) +".txt");
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
                            if(identificador == 1) {

	                            Calendar fechaPago = Validaciones.convertirAFechaCalendar(cronogramaST[1]);
	                            String nombrePublicacion = cronogramaST[2];
	                            Calendar fechaPublicacion = Validaciones.convertirAFechaCalendar(cronogramaST[3]);
	                            double monto = Double.parseDouble(cronogramaST[4]);
	
	                            Audiovisuales audiovisual = AudiovisualesControlador.buscarAudiovisualPorNombreyFecha(nombrePublicacion, audiovisuales);
	                        
	                            if ( audiovisual != null){
	                                audiovisual.setPagos(fechaPago, monto);
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
        }

        //cambiar por grabarpagos
        public static void grabarCalificacionesTXT(ArrayList<Audiovisuales> audiovisuales) {

            try{
                File fichero = new File(directorio + "Calificaciones.txt");

                if (fichero.exists()) {
                    PrintWriter archivoSalida = new PrintWriter(fichero);

                    for(Audiovisuales audiovisual: audiovisuales) {

                        for (Calificaciones calificacion: audiovisual.getCalificaciones()){

                            //Calificaciones.txt -> ("\n" + codAudiovisual + ";"+ estrellas + ";" + motivo + ";" + dd/mm/aaaa + ";" + codSuscriptor)
                            archivoSalida.println( audiovisual.getCodigo() + ";"
                                    + calificacion.getEstrellas() + ";"
                                    + calificacion.getMotivo() + ";"
                                    + calificacion.getFechaRealizada().get(Calendar.DAY_OF_MONTH)
                                    + "/"
                                    + (calificacion.getFechaRealizada().get(Calendar.MONTH) + 1)
                                    + "/"
                                    + calificacion.getFechaRealizada().get(Calendar.YEAR) + ";"
                                    + calificacion.getSuscriptor().getCodigo()

                            );
                        }
                    }

                    archivoSalida.close();
                }

            } catch (Exception e) {
                System.out.println("No se puede grabar el archivo de Calificaciones.txt");
            }
        }

}


