package tic1.server.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.server.business.ActorsMgr;
import tic1.server.business.GenreMgr;
import tic1.server.business.MovieMgr;
import tic1.server.entities.Actor;
import tic1.server.entities.Movie;
import tic1.server.entities.Genre;
import tic1.commons.transfers.NewMovieDTO;
import tic1.server.persistence.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieRestController {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreMgr genreMgr;

    @Autowired
    ActorsMgr actorsMgr;

    @Autowired
    MovieMgr movieMgr;

    @Autowired
    private ModelMapper modelMapper;
    private Genre genres;

    @PostMapping("/movie")
    public void save(@RequestBody NewMovieDTO movie) {
        Movie move = new Movie(movie);
        movieMgr.addMovie(new Movie(movie));
    }

    @PutMapping("/movie/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody NewMovieDTO movie) {
        movieMgr.updateMovie(id, new Movie(movie));
    }

    @GetMapping("/movie/title/{title}/{page}")
    public List<NewMovieDTO> moviesByTitle(@PathVariable("title") String title, @PathVariable("page") int page) {
        List<Movie> movies = movieMgr.findByNamePaged(title, page); //todo List must be pages not full
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    //@GetMapping("/movie/genre/{genre}/{page}")
    //public List<NewMovieDTO> moviesByGenre(@PathVariable("genres") Genre genres, @PathVariable("page") int page) {
    // List<Movie> movies = movieMgr.findByGenrePaged(genres, page); //todo List must be pages not full
    // return movies.stream()
    //       .map(Movie::toDTO)
    //     .collect(Collectors.toList());
    //}

    @GetMapping("/movie/{id}")
    public NewMovieDTO moviesById(@PathVariable("id") long id) {
        Movie movie = movieMgr.getOne(id);
        return movie.toDTO();
    }

    @GetMapping("/movie/genre/{genre}/{page}")
    public List<NewMovieDTO> moviesByGenresWithPages(@PathVariable("genre") String genreName, @PathVariable("page") int page){
        Genre genre =  genreMgr.findByGenre(genreName).get(0);
        List<Movie> movies = movieMgr.findByGenrePaged(genre, page);
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/actor/{actor}/{page}")
    public List<NewMovieDTO> moviesByActorWithPages(@PathVariable("actor") Long actorId, @PathVariable("page") int page){
        Actor actor =  actorsMgr.getOne(actorId);
        List<Movie> movies = movieMgr.findByActorPaged(actor, page);
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie")
    public List<NewMovieDTO> movies() {
        List<Movie> movies = movieRepository.findAll(); //todo List must be pages not full
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/paged/{page}")
    public List<NewMovieDTO> moviesPaged(@PathVariable("page") int page) {
        List<Movie> movies = movieMgr.findAllPaged(page);
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/movie/{id}")
    public void delete(@PathVariable("id") Long id) {
        movieMgr.deleteMovie(id);
    }
}
