package controller;

import model.DAO.SuscriptoresTXT;
import model.Suscriptores;

import java.util.Iterator;
import java.util.TreeSet;

public class SuscriptorControlador {

    SuscriptoresTXT suscriptoresTXT = new SuscriptoresTXT();

    public Suscriptores buscarSuscriptor(int codSuscriptor){
        Suscriptores suscriptor = null;
        TreeSet<Suscriptores> suscriptores = suscriptoresTXT.bajarSuscriptores();

        Iterator iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            // codigo deberia estar en suscriptor tambien? o hacemos por nroDoc?
        }

        return suscriptor;
    }
}
