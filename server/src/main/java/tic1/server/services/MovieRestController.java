package tic1.server.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.server.business.MovieMgr;
import tic1.server.entities.Movie;
import tic1.commons.transfers.MovieDTO;
import tic1.server.persistence.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieRestController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieMgr movieMgr;

    @Autowired
    private ModelMapper modelMapper;

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
    public List<MovieDTO> moviesByTitle(@PathVariable("title") String title){
       List<Movie> movies = movieRepository.findByName(title); //todo List must be pages not full
       return movies.stream()
               .map(Movie::toDTO)
               .collect(Collectors.toList());
    }

    @GetMapping("/movie")
    public List<MovieDTO> movies(){
        List<Movie> movies = movieRepository.findAll(); //todo List must be pages not full
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }





}
