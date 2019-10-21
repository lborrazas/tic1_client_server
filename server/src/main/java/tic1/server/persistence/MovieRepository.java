package tic1.server.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import tic1.server.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String title);
   // Pageable pageList = PageRequest.of(0, 3, Sort.by("name"));
    List<Movie> findAllByName(String name, Pageable pageable);

}
