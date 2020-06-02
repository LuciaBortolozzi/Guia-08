package view;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validaciones {

    static Scanner scan;
    static{
        scan = new Scanner(System.in);
    }

    public Validaciones(){ }

    public static int validarInt(String ingreso){
        Mostrar.mostrar("Ingresar " + ingreso);
        int x = -1;
        boolean incorrecto;
        do {
            try {
                incorrecto = false;
                x = scan.nextInt();
            } catch (InputMismatchException e){
                incorrecto = true;
                scan.nextLine();
            }
            if (x <= 0){
                incorrecto = true;
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
            }
        } while ( incorrecto );
        return x;
    }

    public static long validarLong(String ingreso){
        Mostrar.mostrar("Ingresar " + ingreso);
        long x = -1;
        boolean incorrecto;
        do {
            try {
                incorrecto = false;
                x = scan.nextLong();
            } catch (InputMismatchException e){
                incorrecto = true;
                scan.nextLine();
            }
            if ( x <= 0 ){
                incorrecto = true;
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
            }
        } while ( incorrecto );
        return x;
    }

    public static double validarDouble(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        double x = -1;
        boolean incorrecto;
        do {
            try {
                incorrecto = false;
                x = scan.nextDouble();
            } catch (InputMismatchException e){
                incorrecto = true;
                scan.nextLine();
            }
            if ( x <= 0 ){
                incorrecto = true;
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
            }
        } while ( incorrecto );
        return x;
    }

    public static boolean validarBoolean(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        int x;
        do {
            while (!scan.hasNextInt()) {
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while (x != 0 && x != 1);
        return x == 1;
    }

    public static int validarAnio(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        int x;
        do {
            while (!scan.hasNextInt()){
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while ( x < 1900 );
        return x;
    }

    public static int validarMes(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        int x;
        do {
            while (!scan.hasNextInt()){
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while ( x < 1 || x > 12 );
        return x;
    }

    public static int validarDia(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        int x;
        do {
            while (!scan.hasNextInt()){
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while ( x < 1 || x > 31 );
        return x;
    }

    public static int tipo(String ingreso){
        Mostrar.mostrar("Ingresar " + ingreso);
        int x;
        do {
            while (!scan.hasNextInt()) {
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while (x != 1 && x != 2);
        return x;
    }

    public static char validarSexo(String ingreso){
        Mostrar.mostrar("Ingresar " + ingreso);
        char x;
        do {
            while (!scan.hasNext()) {
                System.out.println("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.next().charAt(0);
        } while (x != 'M' && x != 'F' && x != 'O');
        return x;
    }

    public static int limite(int a, int b){
        int x;
        do {
            while (!scan.hasNextInt()) {
                Mostrar.mostrar("Incorrecto, ingresar nuevamente: ");
                scan.nextLine();
            }
            x = scan.nextInt();
        } while (x < a || x > b);
        return x;
    }

    public static String ingresar(String ingreso) {
        Mostrar.mostrar("Ingresar " + ingreso);
        String texto = "";
        while (texto.equals("")) {
            texto = scan.nextLine();
        }
        return texto;
    }

    public static String ingresar(String ingreso, boolean toUpper) {
        Mostrar.mostrar("Ingresar " + ingreso);
        String texto = "";
        while (texto.equals("")) {
            texto = scan.nextLine();
        }
        if(toUpper) {
            return texto.toUpperCase();
        } else {
            return texto;
        }
    }

    public static Calendar ultimoMes(Calendar fechaActual) {
        fechaActual.add(Calendar.MONTH, -1);
        return fechaActual;
    }

    public static Calendar dosMesesAntes(Calendar fechaActual) {
        fechaActual.add(Calendar.MONTH, -2);
        return fechaActual;
    }

    public static Calendar tresMesesAntes(Calendar fechaActual) {
        fechaActual.add(Calendar.MONTH, -3);
        return fechaActual;
    }
    
    public static Calendar OnceMesesAntes(Calendar fechaActual) {
        fechaActual.add(Calendar.MONTH, -10);
        return fechaActual;
    }
    
    public static Calendar UnMesDespues(Calendar fechaActual) {
        fechaActual.add(Calendar.MONTH, +1);
        return fechaActual;
    }
    
    public static Calendar UnaSemanaDespues(Calendar fechaActual) {
        fechaActual.add(Calendar.DAY_OF_MONTH, +7);
        return fechaActual;
    }

    public static Calendar unAnioAntes(Calendar fechaActual) {
        fechaActual.add(Calendar.YEAR, -1);
        return fechaActual;
    }

    public static Calendar dosAniosAntes(Calendar fechaActual) {
        fechaActual.add(Calendar.YEAR, -2);
        return fechaActual;
    }

    public static Calendar convertirAFechaCalendar(String f) {
        Calendar fecha = Calendar.getInstance();

        //dd/mm/aaaa
        String[] aux = f.split("/");
        int day = Integer.parseInt(aux[0]);
        int month = Integer.parseInt(aux[1]);
        int year = Integer.parseInt(aux[2]);

        fecha.set(Calendar.DAY_OF_MONTH, day);
        fecha.set(Calendar.MONTH, (month - 1));
        fecha.set(Calendar.YEAR, year);

        return fecha;
    }

    public static int validarEdad(Calendar fechaDeNac, Calendar fechaActual) {

        int edad = fechaActual.get(Calendar.YEAR) - fechaDeNac.get(Calendar.YEAR);

        if (fechaActual.get(Calendar.MONTH) < fechaActual.get(Calendar.MONTH)){
            edad--;
        } else if (fechaActual.get(Calendar.MONTH) == fechaActual.get(Calendar.MONTH)){
            if (fechaActual.get(Calendar.DAY_OF_MONTH) < fechaActual.get(Calendar.DAY_OF_MONTH)){
                edad--;
            }
        }
        return edad;
    }

    public static boolean menor(Calendar fechaDeNac, Calendar fechaActual, int limite) {
        boolean menor = false;
        int edad = validarEdad(fechaDeNac, fechaActual);
        if (edad < limite)
            menor = true;
        return menor;
    }

    public static boolean mayor(Calendar fechaDeNac, Calendar fechaActual, int limite) {
        boolean mayor = false;
        int edad = validarEdad(fechaDeNac, fechaActual);
        if (edad > limite)
            mayor = true;
        return mayor;
    }
   
}
