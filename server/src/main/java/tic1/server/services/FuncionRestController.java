package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.FunctionDTO;
import tic1.server.business.FunctionMgr;
import tic1.server.business.MovieMgr;
import tic1.server.business.SalaMgr;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Movie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FuncionRestController {

    @Autowired
    FunctionMgr functionMgr;
    @Autowired
    SalaMgr salaMgr;
    @Autowired
    MovieMgr movieMgr;


    @PostMapping("/funcion/")
    public void save(@RequestBody FunctionDTO function) {
        FunctionPK functionPK = new FunctionPK();
        functionPK.setSala(salaMgr.getSalaById(function.getSala()));
        functionPK.setDate(function.getStartTime());
        Funcion funcion = new Funcion();
        funcion.setId(functionPK);
        funcion.setMovie(movieMgr.getOne(function.getMovie().getId()));
        functionMgr.save(funcion);
    }

    @GetMapping("/funcion")
    public List<FunctionDTO> all() {
        List<Funcion> funcions = functionMgr.findAll();
        List<FunctionDTO> functionDTOS = new ArrayList<>();
        for (Funcion funcion : funcions) {
            functionDTOS.add(funcion.toDTO());
        }
        return functionDTOS;
    }

    @GetMapping("/funcion/provider/{provider_id}")
    public List<FunctionDTO> allByProvider(@PathVariable("provider_id") long provId) {
        List<Funcion> funcions = functionMgr.findByProvider(provId);
        List<FunctionDTO> functionDTOS = new ArrayList<>();
        for (Funcion funcion : funcions) {
            functionDTOS.add(funcion.toDTO());
        }
        return functionDTOS;
    }

    @GetMapping("/funcion/provider/{provider_id}/paged/{page}")
    public List<FunctionDTO> moviesPaged(@PathVariable("page") int page, @PathVariable("provider_id") long provId) {
        List<Funcion> movies = functionMgr.findByProviderPaged(provId, page);
        return movies.stream()
                .map(Funcion::toDTO)
                .collect(Collectors.toList());
    }

    /*@GetMapping("/function/{sala_id}/{date}")
    public FunctionDTO getFunctionDto(@PathVariable("sala_id") long sala_id, @PathVariable("date")LocalDateTime localDateTime){
        FunctionDTO functionDTO = new FunctionDTO();
        Sala sala = salaMgr.getSalaById(sala_id);
        Funcion function = functionMgr.getFunctionByPk(sala, localDateTime);

        functionDTO.setSala(sala.getId());
        functionDTO.setCinemaName(sala.getCinema().getName());
        functionDTO.setLocal(sala.getCinema().getLocation());
        functionDTO.setProviderName(sala.getCinema().getProvider().getName());
        functionDTO.setStartTime(function.getId().getDate());
        functionDTO.setMovie(function.getMovie().toDTO());
        return functionDTO;
    }
*/
    @GetMapping("/function/{movie_id}/{date}")
    public List<FunctionDTO> getFunctionDtos(@PathVariable("movie_id") long movie_id, @PathVariable("date") LocalDateTime localDateTime) {
        ;

        Movie tempMovie = movieMgr.getOne(movie_id);
        List<Funcion> funtions = functionMgr.getByMovieAndDate(tempMovie, localDateTime);
        List<FunctionDTO> functionDTOs = new ArrayList<>();

        for (Funcion tempFuntion : funtions) {
            FunctionDTO functionDTO = new FunctionDTO();

            functionDTOs.add(tempFuntion.toDTO());
        }
        return functionDTOs;
    }

    @GetMapping("/function/movie/{movie_id}")
    public List<FunctionDTO> getFunctionDtos(@PathVariable("movie_id") String movie_id) {
        long id = Long.parseLong(movie_id);
        Movie tempMovie = movieMgr.getOne(id);
        List<Funcion> funtions = functionMgr.getByMovie(tempMovie);
        List<FunctionDTO> functionDTOs = new ArrayList<>();

        for (Funcion tempFuntion : funtions) {


            functionDTOs.add(tempFuntion.toDTO());
        }
        return functionDTOs;
    }


    @DeleteMapping("/function/{id}/{date}")
    public void delateFunctionDtos(@PathVariable("id") long id, @PathVariable("date") String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        FunctionPK functionPK = new FunctionPK();

        functionPK.setSala(salaMgr.getSalaById(id));
        functionPK.setDate(dateTime);
        functionMgr.deleteFunction(functionPK);

    }
    //TODO crear funcion;

}
