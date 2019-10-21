package tic1.client;

import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;

import java.util.List;


public class Main {
    public static void main(String[] args){
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
       // List<Movie> movies = movieRestTemplate.findAllPaged(1);
       // List<Movie> movies = movieRestTemplate.filterGenrePaged("dramatica", 0);
        List<Movie> movies = movieRestTemplate.filterTitlePaged("tempest", 0);
        System.out.println(movies.get(0).toString());


        Movie movie = new Movie();
        movie.setActors("Mate");
        movie.setDescription("descr");
        movie.setDuration("1:30");
        movie.setName("Name from java");
        movie.setGenre("Aventura");

     //   Movie movieFromRepo = movieRestTemplate.showMovie(5);
     //   System.out.println(movieFromRepo.toString());

       // movieRestTemplate.updateMovie(2,movie);
       // movieRestTemplate.deleteMovie(3);
       // movieRestTemplate.createMovie(movie);
    }
}
