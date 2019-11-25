package tic1.client.seeders;

import tic1.client.models.Cinema;
import tic1.client.models.Funcion;
import tic1.client.models.Sala;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.SalaRestTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class FuncionSeeder {
    public static void main(String[] args) {
        FuncionRestTemplate funcionRestTemplate = new FuncionRestTemplate();
        Funcion funcion = new Funcion();
        funcion.setDate(LocalDateTime.now());
        SalaRestTemplate salaRestTemplate = new SalaRestTemplate();
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        List<Sala> salas = salaRestTemplate.returAll();
        int a= (int) (Math.random()*10);
        funcion.setSalaId(salas.get(a).getId());
        CinemaRestTemplate cinemaRestTemplate = new CinemaRestTemplate();
        List<Cinema> cines = cinemaRestTemplate.get();
        funcion.setCinemaId(cines.get(a).getId());
        funcion.setMovie(movieRestTemplate.showMovie(43));
        funcionRestTemplate.save(funcion);
//        funcion.setSalaId();
    }
}
