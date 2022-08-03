package com.galaxiaCul.model;

import javax.persistence.DiscriminatorValue;

public class Tormenta {
    private int dia;
    private double perimetro;

    public Tormenta() {}

    public Tormenta(int dia, double perimetro) {
        this.dia = dia;
        this.perimetro = perimetro;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    @Override
    public String toString() {
        return "Tormenta [dia=" + dia + ", perimetro=" + perimetro + "]";
    }

}
