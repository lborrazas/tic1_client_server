package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Genre;
import tic1.server.persistence.GenreRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class GenreMgr {
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public List<Genre> findById(long id) {
        return   genreRepository.findAllById(id);
    }
    public void updateGenre(@PathVariable("id") Long id, @Valid @RequestBody Genre genre){
        Genre existingGenre= genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingGenre.setGenre(genre.getGenre());
        genreRepository.save(existingGenre);
    }

    public Genre getOne(long id) {
        return   genreRepository.getOne(id);
    }



    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    public List<Genre> findByGenre(String genreName) {
      return  genreRepository.findByGenre(genreName);
    }

    public  List<Genre> getAllByGenre(String genre){
        return genreRepository.findAllByGenre(genre);
    }

    public  List<Genre> findAllById(Long id){
        return genreRepository.findAllById(id);
    }

    public  List<Genre> getByGenre(String genre){
        return  genreRepository.findByGenre(genre);
    }

    public   List<Genre> findByGenreContaining(String genre){
        return genreRepository.findByGenreContaining(genre);
    }

}
