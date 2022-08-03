package com.galaxiaCul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.galaxiaCul.model.Pronostico;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("pronosticoRepository")
public interface PronosticoRepository extends PagingAndSortingRepository<Pronostico, Long> {

    // @param dia
    // @return DEVUELVE EL PRONOSTICO DE UN DIA
    public Pronostico findByDia(int dia);

    // @param clima
    // @return DEVUELVE UNA LISTA CON TODOS LOS PRONOSTICOS DEL CLIMA INGRESADO

    public List<Pronostico> findByClima(String clima);

    // @return DEVUELVE EL PRONOSTICO QUE TENGA MAYOR PERIMETRO
    public Pronostico findFirstByOrderByPerimetroDesc();

    // @param dia
    // @param clima
    // return DEVUELVE UNA LISTA CON LOS PRONOSTICOS DE ESE CLIMA EN LA QUE EL DIA SEA MENOR AL INGRESADO
    public List<Pronostico> findByDiaLessThanAndClima(int dia, String clima);

    // @param perimetro
    // @return DEVUELVE UNA LISTA CON LAS PREDICCIONES QUE TIENE EXACTAMENTE EL MISMO PERIMETRO
    public List<Pronostico> findByPerimetro(double perimetro);

    // @return DEVUELVE LA LISTA DE PRONOSTICOS COMPLETA
    public List<Pronostico> findAll();

}
