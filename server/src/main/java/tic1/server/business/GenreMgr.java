package tic1.server.business;

import com.sun.tools.javac.jvm.Gen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.entities.Genre;
import tic1.server.persistence.GenreRepository;

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

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    public List<Genre> findByGenre(String genreName) {
      return  genreRepository.findByGenre(genreName);
    }
}
