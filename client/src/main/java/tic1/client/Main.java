package tic1.client;

import org.apache.tomcat.util.net.jsse.JSSEImplementation;
import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.ActorRestTemplate;
import tic1.client.services.GenreRestTemplate;
import tic1.client.services.MovieRestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args){
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        Genre genre = genreRestTemplate.showGenre(3);
        System.out.println(genre.getGenre());
        Actor actor = actorRestTemplate.showActor(9);
        System.out.println(actor.getName());
        Movie movie = new Movie();
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(actor);
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(genre);
        movie.setName("movie1");
        movie.setDescription("descripcion");
        movie.setActors(actors);
        movie.setGenre(genres);
        movie.setDuration(100);

        movieRestTemplate.createMovie(movie);
       // List<Movie> movies = movieRestTemplate.findAllPaged(1);
       // List<Movie> movies = movieRestTemplate.filterGenrePaged("dramatica", 0);
       // List<Movie> movies = movieRestTemplate.filterTitlePaged("NOMBRE", 0);
      //  System.out.println(movies.get(0).toString());

       // Movie movieForEdit = movies.get(0);
       // System.out.println(movieForEdit.getGenre());
       // movieForEdit.setGenre("NO ANDA");
        //movieForEdit.setName("NOMBRE");
        //System.out.println(movieForEdit.getGenre());



     //   Movie movieFromRepo = movieRestTemplate.showMovie(5);
     //   System.out.println(movieFromRepo.toString());

     //   movieRestTemplate.updateMovie(movieForEdit.getId(),movieForEdit);
       // movieRestTemplate.deleteMovie(3);
       // movieRestTemplate.createMovie(movie);
    }
}
