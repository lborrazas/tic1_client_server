package tic1.client.seeders;

import tic1.client.models.Cinema;
import tic1.client.models.Sala;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.SalaRestTemplate;
import tic1.commons.transfers.SalaDTO;

import java.util.List;
import java.util.Random;

public class SalaSeeder {
    public static void main(String[] args) {
        SalaRestTemplate salaRestTemplate = new SalaRestTemplate();
        CinemaRestTemplate cinemaRestTemplate = new CinemaRestTemplate();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setName("juanito El wuerfanito");
        salaDTO.setMaxcolum(3);
        salaDTO.setMaxfila(6);
        int a= (int) (Math.random()*10);
        Cinema delete = cinemaRestTemplate.get().get(a);
        salaDTO.setCinemaid(cinemaRestTemplate.get().get(a).getId());
        Sala sala = new Sala(salaDTO);
        salaRestTemplate.createSala(sala);
    }

}
