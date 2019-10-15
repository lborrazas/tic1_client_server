package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.server.business.MovieMgr;
import tic1.server.entities.Movie;
import tic1.commons.transfers.MovieDTO;
import tic1.server.persistence.MovieRepository;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieMgr movieMgr;

    @PostMapping("/movie")
    public void save(@RequestBody MovieDTO movie)
    {
        movieMgr.addMovie(new Movie(movie));
    }

    @PutMapping("/movie")
    public void update(@PathVariable("id") Long id, @RequestBody MovieDTO movie){
        movieMgr.updateMovie(id, new Movie(movie));
    }

    @GetMapping("/movie/title/{title}")
    public List<Movie> moviesByTitle(@PathVariable("title") String title){
        return movieRepository.findByName(title);
    }
    


}
