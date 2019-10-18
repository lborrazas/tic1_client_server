package tic1.server.business;

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

        movieRepository.save(existingMovie);
    }

    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        movieRepository.delete(movie);

        return ResponseEntity.ok().build();
    }
}
