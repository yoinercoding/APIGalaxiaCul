package com.galaxiaCul.model;

public class Triangulo {

    private Punto punto1;
    private Punto punto2;
    private Punto punto3;

    public Triangulo(Punto punto1, Punto punto2, Punto punto3) {
        super();
        this.punto1 = punto1;
        this.punto2 = punto2;
        this.punto3 = punto3;
    }

    // CALCULA EL AREA DEL TRIANGULO
    public float area() {
        return Math.abs((float)( punto1.getX() * (punto2.getY() - punto3.getY()) + punto2.getX()
                * (punto3.getY() - punto1.getY()) + punto3.getX() * (punto1.getY() - punto2.getY()))/2);
    }

    // DEVUELVE TRUE SI EL PUNTO ESTA CONTENIDO EN EL TRIANGULO
    public boolean contieneUnPunto(Punto punto) {
        Triangulo tri1 = new Triangulo(punto, punto2, punto3);
        Triangulo tri2 = new Triangulo(punto1, punto, punto3);
        Triangulo tri3 = new Triangulo(punto1, punto2, punto);

        return tri1.area() + tri2.area() + tri3.area() == this.area();
    }

    // CALCULA EL PERIMETRO DEL TRIANGULO
    public double perimetro (){
        double xy = this.punto1.distancia(punto2);
        double yz = this.punto2.distancia(punto3);
        double xz = this.punto3.distancia(punto1);

        return xy + yz + xz;
    }

}
