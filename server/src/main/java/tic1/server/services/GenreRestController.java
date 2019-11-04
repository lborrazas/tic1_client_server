package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.MovieGenreDTO;
import tic1.server.entities.Genre;
import tic1.server.persistence.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GenreRestController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genre")
    public List<MovieGenreDTO> genres() {
        List<Genre> genres = genreRepository.findAll(); //todo List must be pages not full
        return genres.stream()
                .map(Genre::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{id}")
    public MovieGenreDTO genre(@PathVariable long id) {
      Genre genre = genreRepository.findById(id).get();
      return genre.toDTO();
    }


    @PostMapping("/genre")
    public void save(@RequestBody MovieGenreDTO genreDto){
        Genre genre = new Genre(genreDto);
        genreRepository.save(genre);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable long id){
        genreRepository.deleteById(id);
    }
}
