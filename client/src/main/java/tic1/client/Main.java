package tic1.client;

import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args){
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        List<Movie> movies = movieRestTemplate.findAll();
        System.out.println(movies.get(0).toString());

        Movie movie = new Movie();
        movie.setActors("Mary Brown");
        movie.setDescription("Romantic Movie");
        movie.setDuration("2 hours");
        movie.setName("La Ultima Cena");

        movieRestTemplate.createMovie(movie);
    }
}
