package tic1.server.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Movie;
import tic1.server.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
public class MovieMgr {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void addMovie(Movie movie) {

        movieRepository.save(movie);

    }

    public Movie getOne(@PathVariable("id") Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }

    public void updateMovie(@PathVariable("id") Long id, @Valid @RequestBody Movie movie) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        existingMovie.setName(movie.getName());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setActors(movie.getActors());
        existingMovie.setGenre(movie.getGenre());

        movieRepository.save(existingMovie);
    }

    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        movieRepository.delete(movie);

        return ResponseEntity.ok().build();
    }

    public List<Movie> findByNamePaged(String name, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        List<Movie> movies = movieRepository.findAllByName(name, pageable);
        return movies;
    }

    public List<Movie> findAllPaged(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Movie> movies = movieRepository.findAll(pageable);
       return movies.getContent();
    }


    public List<Movie> findByGenrePaged(String genre, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        List<Movie> movies = movieRepository.findAllByGenre(genre, pageable);
        return movies;
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

}
