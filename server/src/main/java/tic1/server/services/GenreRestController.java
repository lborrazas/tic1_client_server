package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.MovieGenreDTO;
import tic1.server.business.GenreMgr;
import tic1.server.entities.Genre;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GenreRestController {

    @Autowired
    private GenreMgr genreMgr;

    @GetMapping("/genre")
    public List<MovieGenreDTO> genres() {
        List<Genre> genres = genreMgr.findAll(); //todo List must be pages not full
        return genres.stream()
                .map(Genre::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{id}")
    public MovieGenreDTO genre(@PathVariable long id) {
      Genre genre = genreMgr.findById(id).get(0);
      return genre.toDTO();
    }


    @PostMapping("/genre")
    public void save(@RequestBody MovieGenreDTO genreDto){
        Genre genre = new Genre(genreDto);
        genreMgr.save(genre);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable long id){
        genreMgr.deleteById(id);
    }
}
