package com.galaxiaCul.model;

public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // CALCULA LA DISTANCIA A OTRO PUNTO
    public double distancia (Punto punto) {
        return Math.sqrt(Math.pow(punto.getX()-this.x, 2) + Math.pow(punto.getY()-this.y, 2));
    }

    @Override
    public String toString() {
        return "Punto [x=" + x + ", y=" + y + "]";
    }
}
