package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.commons.transfers.FunctionDTO;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;
import tic1.server.persistence.FunctionRepository;
import tic1.server.persistence.MovieRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FunctionMgr {
    @Autowired
    private FunctionRepository funcionRepository;
   // @Autowired
   // private MovieRepository movieRepository;

    public void save(Funcion funcion){
        funcionRepository.save(funcion);
    }


    public Funcion getFunctionByPk(Sala sala, LocalDateTime localDateTime){
        FunctionPK functionPK = new FunctionPK(sala, localDateTime);
        return funcionRepository.findById(functionPK).get();
    }
    public List<Funcion> findAll() {
        return funcionRepository.findAll();
    }

    public void addFuncion(Funcion funcion) {

        funcionRepository.save(funcion);

    }

    public ResponseEntity<?> deleteFunction(@PathVariable("id") FunctionPK id) {

        Funcion funcion= funcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        funcionRepository.delete(funcion);

        return ResponseEntity.ok().build();
    }

    public void updateActor(@PathVariable("id") FunctionPK id, @Valid @RequestBody Funcion tempFuncion){
        Funcion existingFuncion=funcionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingFuncion.setMovie(tempFuncion.getMovie());
        existingFuncion.setSecondId(tempFuncion.getSecondId());
        funcionRepository.save(existingFuncion);
    }
   public List<Funcion> getByIdSala(Sala sala){
        return funcionRepository.findAllByIdSala(sala);
    };


    public List<Funcion> fetchByIdSalaId(long salaid){
        return funcionRepository.findAllByIdSalaId(salaid);
    }

    public List<Funcion> fetchByMovieAndIdDateAfter(Movie movie, LocalDateTime today){
        return funcionRepository.findAllByMovieAndIdDateAfter(movie,today);
    };//cuando se llame la funcion usar now()

    public List<Funcion> getByDateAfter(LocalDateTime today){
        return funcionRepository.findAllByIdDateAfter(today);
    };//cuando se llame la funcion usar now()


    public List<Funcion> getByMovieAndDate(Movie movie, LocalDateTime today){
        return  funcionRepository.findAllByMovieAndIdDateAfter(movie, today);//cuando se llame la funcion usar now()
    };//cuando se llame la funcion usar now()

}
