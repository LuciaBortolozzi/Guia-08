package model;

import java.util.Calendar;

public class CronogramaPagos {
    private Calendar fechaDePago;
    private double monto;

    public CronogramaPagos() {
    }

    public CronogramaPagos(Calendar fechaDePago, double monto) {
        this.fechaDePago = fechaDePago;
        this.monto = monto;
    }

    public Calendar getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Calendar fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
