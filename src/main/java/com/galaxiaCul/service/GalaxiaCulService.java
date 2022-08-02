package com.galaxiaCul.service;

import java.util.List;
import com.galaxiaCul.model.Pronostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galaxiaCul.galaxiaCul.model.*;
import com.galaxiaCul.repository.PronosticoRepository;

@Service("galaxiaCulService")
public class GalaxiaCulService {

    @Autowired
    PronosticoRepository pronosticoRepository;

    private final int duracionAnio = 360; // 360 DIAS LA POSICION DE LOS PLANETAS VUELVE A SU ESTADO INICIAL
    private Planeta planeta1;
    private Planeta planeta2;
    private Planeta planeta3;

    /*
    * @param Planeta
    * @return ALMACENA EL PRONOSTICO DEL PRIMER AÑO EN LA BASE DE DATOS
    * TENIENDO EN CUENTA QUE ESTE ES LINEAL
    */
    public void init(Planeta planeta1, Planeta planeta2, Planeta planeta3, PronosticoRepository pronosticoRepository) {
        this.pronosticoRepository=pronosticoRepository;
        this.planeta1=planeta1;
        this.planeta2=planeta2;
        this.planeta3=planeta3;

        pronosticoRepository.deleteAll();

        for(int i = 0; i < duracionAnio; i++) {
            pronosticoRepository.save(getPronostico(i));
        }
        calcularTormentas();
    }

    /*
    * @return CALCULA LA CANTIDAD DE DIAS QUE SE REPITE EL MAXIMO PERIMETRO
    * DE LOS PRONOSTICOS ALMACENADOS
    */
    public void calcularTormentas() {
        Pronostico pronosticoMaximoPerimetro = pronosticoRepository.findFirstByOrderByPerimetroDesc();
        double perimetroMaximo = pronosticoMaximoPerimetro.getPerimetro();
        List<Pronostico> pronosticosDeTormenta = pronosticoRepository.findByPerimetro(perimetroMaximo);

        for(int i = 0; i < pronosticosDeTormenta.size(); i++) {
            Pronostico pronostico = pronosticosDeTormenta.get(i);
            pronostico.setClima("tormenta");
            pronosticoRepository.save(pronostico);
        }
    }

    /*
    * @param cantidad de años
    * @return PRONOSTICO DEL DIA INGRESADO, CALCULA LA CONDICION CLIMATICA DE UN DIA EN PARTICULAR
    */
    public Pronostico getPronostico(int dia) {
        Triangulo triangulo = new Triangulo(planeta1.getPosicion(dia), planeta2.getPosicion(dia),
                planeta3.getPosicion(dia));
        Punto posicionDelSol = new Punto(0 , 0);

        if(triangulo.contieneUnPunto(planeta3.getPosicion(dia)) && triangulo.area() == 0) {
            if(triangulo.contieneUnPunto(posicionDelSol)) {
                return new Pronostico(dia, "sequia", triangulo.perimetro());
            }
            return new Pronostico(dia, "optimo", triangulo.perimetro());
        } else if(triangulo.contieneUnPunto(posicionDelSol)) {
            return new Pronostico(dia, "lluvia", triangulo.perimetro());
        } else {
            return new Pronostico(dia, "soleado", triangulo.perimetro());
        }
    }
}
