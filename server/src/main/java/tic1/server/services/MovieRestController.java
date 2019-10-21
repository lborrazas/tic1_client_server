package tic1.server.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tic1.server.business.MovieMgr;
import tic1.server.entities.Movie;
import tic1.commons.transfers.MovieDTO;
import tic1.server.persistence.MovieRepository;

import javax.servlet.http.HttpServletResponse;
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
    public void save(@RequestBody MovieDTO movie) {
        movieMgr.addMovie(new Movie(movie));
    }

    @PutMapping("/movie/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody MovieDTO movie) {
        movieMgr.updateMovie(id, new Movie(movie));
    }

    @GetMapping("/movie/title/{title}")
    public List<MovieDTO> moviesByTitle(@PathVariable("title") String title) {
        List<Movie> movies = movieRepository.findByName(title); //todo List must be pages not full
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/{id}")
    public MovieDTO moviesByTitle(@PathVariable("id") long id) {
        Movie movie = movieMgr.getOne(id);
        return movie.toDTO();
    }

    /*@GetMapping("/movie/genre/{genre}/{page}")
    public List<MovieDTO> moviesByGenresWithPages(@PathVariable("genre") String genre, @PathVariable("page") int page){
        List<Movie> movies = movieMgr.
    }*/

    @GetMapping("/movie")
    public List<MovieDTO> movies() {
        List<Movie> movies = movieRepository.findAll(); //todo List must be pages not full
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/paged/{page}")
    public List<MovieDTO> moviesPaged(@PathVariable("page") int page) {
       List<Movie> movies = movieMgr.findAllPaged(page);
        return movies.stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/movie/{id}")
    public void delete(@PathVariable("id") Long id) {
        movieMgr.deleteMovie(id);
    }

  /*  @GetMapping(params = { "page", "size" })
    public List<MovieDTO> findPaginated(@RequestParam("page") int page,
                                   @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
                                   HttpServletResponse response) {
        Page<MovieDTO> resultPage = service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<MovieDTO>(
                MovieDTO.class, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return resultPage.getContent();
    }*/


}
