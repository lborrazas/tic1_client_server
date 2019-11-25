package tic1.client.seeders;

import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.ActorRestTemplate;
import tic1.client.services.GenreRestTemplate;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.SalaRestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MovieSeeder {
    public static void main(String[] args) {
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        List<Actor> a = actorRestTemplate.findAll();
        List<Genre> b = genreRestTemplate.findAll();
        Movie peli = new Movie();
        Set<Actor> a1= new HashSet<Actor>();
        Set<Genre> b1= new HashSet<Genre>();
        for (Actor actor:a){
            a1.add(actor);
        }
        for (Genre genre:b){
            b1.add(genre);
        }
        peli.setActors(a1);
        peli.setGenre(b1);
        peli.setDescription("peurba de juan");
        peli.setDuration(420);
        peli.setImagePath("Shutter Island.png");
        peli.setName("Shutter Island");
        movieRestTemplate.createMovie(peli);
    }
}
