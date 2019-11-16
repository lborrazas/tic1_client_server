package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.NewMovieDTO;
import tic1.server.business.FunctionMgr;
import tic1.server.business.MovieMgr;
import tic1.server.business.SalaMgr;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
public class FuncionRestController {

    @Autowired
    FunctionMgr functionMgr;
    @Autowired
    SalaMgr salaMgr;
    @Autowired
    MovieMgr movieMgr;



    @PostMapping("/function/{function}")
    public void save(@RequestBody FunctionDTO function){
        FunctionPK functionPK= new FunctionPK();
        functionPK.setSala(salaMgr.getSalaById(function.getSala()));
        functionPK.setDate(function.getStartTime());
        Funcion funcion= new Funcion();
        funcion.setId(functionPK);
        funcion.setMovie(movieMgr.getOne(function.getMovie().getId()));
        functionMgr.save(funcion);

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
    public List<FunctionDTO> getFunctionDtos(@PathVariable("movie_id") long movie_id, @PathVariable("date")LocalDateTime localDateTime){
        ;

        Movie tempMovie = movieMgr.getOne(movie_id);
        List<Funcion> funtions = functionMgr.getByMovieAndDate(tempMovie, localDateTime);
        List<FunctionDTO> functionDTOs = new ArrayList<>();

        for (Funcion tempFuntion:funtions){
            FunctionDTO functionDTO = new FunctionDTO();

            functionDTOs.add(tempFuntion.toDTO());
        }
        return functionDTOs;
    }
    @GetMapping("/function/{movie_id}")
    public List<FunctionDTO> getFunctionDtos(@PathVariable("movie_id") long movie_id){
        LocalDateTime localDateTime= null;
        localDateTime = LocalDateTime.now();
        Movie tempMovie = movieMgr.getOne(movie_id);
        List<Funcion> funtions = functionMgr.getByMovieAndDate(tempMovie, localDateTime);
        List<FunctionDTO> functionDTOs = new ArrayList<>();

        for (Funcion tempFuntion:funtions){


            functionDTOs.add(tempFuntion.toDTO());
        }
        return functionDTOs;
    }




    @DeleteMapping("/function/{function}")
    public void delateFunctionDtos(@PathVariable("function") FunctionDTO functionDTO){

        FunctionPK functionPK= new FunctionPK();

        functionPK.setSala(salaMgr.getSalaById(functionDTO.getSala()));
        functionPK.setDate(functionDTO.getStartTime());
        functionMgr.deleteFunction(functionPK);

    }
    //TODO crear funcion;

}
