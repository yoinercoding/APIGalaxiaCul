package com.galaxiaCul.service;

import com.galaxiaCul.model.*;
import com.galaxiaCul.repository.PronosticoRepository;

public interface GalaxiaCulServiceI {

    Pronostico getPronostico(int dia);

    void calcularTormentas();

    void init(Planeta planeta1, Planeta planeta2, Planeta planeta3, PronosticoRepository pronosticoRepository);
}
