package com.galaxiaCul.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.galaxiaCul.repository.PronosticoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import com.galaxiaCul.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GalaxiaController {

    @Autowired
    private PronosticoRepository pronosticoRepository;
    private final int diasPorAnio = 360;

    // @param dia
    // @return EL CLIMA DEL DIA CONSULTADO
    @RequestMapping("/clima")
    public Pronostico clima(@RequestParam(value="dia") String dia) {
        try {
            int d = Integer.valueOf(dia).intValue();
            Pronostico pronostico = pronosticoRepository.findByDia(d % diasPorAnio);
            pronostico.setDia(d);
            return pronostico;
        } catch (Exception e) {
            throw new RuntimeException("Los parametros ingresados son incorrectos.");
        }
    }


    // @param tipo, años
    // @return EL PERIODO Y LA CANTIDAD DE DIAS QUE SE REPETIRA EN LA CANTIDAD DE AÑOS INGRESADOS
    @RequestMapping("/periodo")
    public Periodo periodo(@RequestParam(value="tipo") String tipo, @RequestParam(value="años") String anios) {
        try {
            List <Pronostico> List = PronosticoRepository.findByCLima(tipo);
            int cantidadPorAnio = list.size();
            int cantidadTotal = cantidadPorAnio * Integer.valueOf(anios).intValue();
            Periodo periodo = new Periodo(cantidadTotal, tipo);

            return periodo;
        } catch (Exception e) {
            throw new RuntimeException("Los parametros ingresados son incorrectos.");
        }
    }

    // @param años
    // @return CUANTAS VECES SE REPETIRA CADA TIPO DE CLIMA EN LA CANTIDAD DE AÑOS INGRESADA
    @RequestMapping("/periodos")
    public List<Periodo> periodos(@RequestParam(value="años") String anios) {
        try{
            int cant = 0;
            int cantidadDeAnios = Integer.valueOf(anios).intValue();
            List<Periodo> periodos =  new ArrayList<>();
            List<Pronostico> list = calcularPronosticoPorAnios(pronosticoRepository.findAll(), cantidadDeAnios);

            cant = (int)list.stream().filter(pronostico -> "soleado".equals(pronostico.getClima())).count();
            periodos.add(new Periodo(cant, "soleado"));

            cant = (int)list.stream().filter(pronostico -> "lluvia".equals(pronostico.getClima())).count();
            periodos.add(new Periodo(cant, "lluvia"));

            cant = (int)list.stream().filter(pronostico -> "tormenta".equals(pronostico.getClima())).count();
            periodos.add(new Periodo(cant, "tormenta"));

            cant = (int)list.stream().filter(pronostico -> "temperatura y presion optima".equals(pronostico.getClima())).count();
            periodos.add(new Periodo(cant, "temperatura y presion optima"));

            cant = (int)list.stream().filter(pronostico -> "sequia".equals(pronostico.getClima())).count();
            periodos.add(new Periodo(cant, "sequia"));

            return periodos;

        } catch (Exception e){
            throw new RuntimeException("Los parametros ingresados son incorrectos.");
        }
    }

    // @param años
    // @return DIAS DE LLUVIA MAX QUE HABRA EN LA CANTIDAD DE AÑOS INGRESADA Y SU PERIMETRO
    @RequestMapping("/picoMaxDeLluvia")
    public List<Tormenta> picoDeLluvia(@RequestParam(value="años") String anios) {
        try{
            int cantidadDeAnios = Integer.valueOf(anios).intValue();
            List<Pronostico> tormentasPorAnio = pronosticoRepository.findByDiaLessThanAndClima(diasPorAnio, "tormenta");
            List<Pronostico> tormentasTotales = calcularPronosticoPorAnios(tormentasPorAnio, cantidadDeAnios);
            List<Tormenta>	tormentas = new ArrayList<>();

            for(Pronostico pronostico: tormentasTotales) {
                tormentas.add(new Tormenta(pronostico.getDia(), pronostico.getPerimetro()));
            }

            return tormentas;

        }catch (Exception e){
            throw new RuntimeException("Los parametros ingresados son incorrectos.");
        }
    }


    /*@param pronostico y cantidad de años
    * @return List<Pronostico> ordenada por dia con su respectiva prediccion climatica
    * procesa las condiciones climaticas de un año completo y calcula el clima equivalente
    * en la proyeccion de años ingresada
    */
    public List<Pronostico> calcularPronosticoPorAnios(List<Pronostico> pronosticoPorUnAnio, int cantidadDeAnios){
        List<Pronostico> pronostico = new ArrayList<>();

        for (int i = 0; i < cantidadDeAnios; i++) {
            for(Pronostico p : pronosticoPorUnAnio) {
                pronostico.add(new Pronostico(p.getDia() + (diasPorAnio * i), p.getClima(), p.getPerimetro()));
            }
        }

        return pronostico;
    }

}
