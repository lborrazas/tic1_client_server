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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args){
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        Genre genre = genreRestTemplate.showGenre(6);
        Genre genre2 = genreRestTemplate.showGenre(9);
        Actor actor = actorRestTemplate.showActor(1);
        Actor actor2 = actorRestTemplate.showActor(3);
        System.out.println(actor2.getId());
        System.out.println(genre2.getId());
        Movie movie = new Movie();
        Set<Actor> actors = new HashSet<>();
        actors.add(actor);
        actors.add(actor2);
        Set<Genre> genres = new HashSet<>();
        System.out.println(genre.getGenre());
        genres.add(genre);
        genres.add(genre2);
        movie.setName("Salsa");
        movie.setDescription("lo ke");
        movie.setActors(actors);
        movie.setGenre(genres);
        movie.setDuration(120);


       // movieRestTemplate.createMovie(movie);

        List<Movie> moviesFilter =  movieRestTemplate.filterActorPaged(actor2,0);
        System.out.println(moviesFilter.get(0).getName());


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
