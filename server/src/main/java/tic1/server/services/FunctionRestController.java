package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.NewMovieDTO;
import tic1.server.business.FunctionMgr;
import tic1.server.business.MovieMgr;
import tic1.server.business.SalaMgr;
import tic1.server.entities.Funcion;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;

import java.time.LocalDateTime;
import java.util.List;

public class FunctionRestController {

    @Autowired
    FunctionMgr functionMgr;
    @Autowired
    SalaMgr salaMgr;
    @Autowired
    MovieMgr movieMgr;

    @GetMapping("/function/{sala_id}/{date}")
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

    @GetMapping("/function/{movie_id}/{date}")
    public List<FunctionDTO> getFunctionDtos(@PathVariable("movie_id") long movie_id, @PathVariable("date")LocalDateTime localDateTime){
        ;

        Movie tempMovie = movieMgr.getOne(movie_id);
        List<Funcion> funtions = functionMgr.getByMovieAndDate(tempMovie, localDateTime);
        List<FunctionDTO> functionDTOs = null;
        for (Funcion tempFuntion:funtions){
            FunctionDTO functionDTO = new FunctionDTO();
            functionDTO.setSala(tempFuntion.getId().getSala().getId());
            functionDTO.setCinemaName(tempFuntion.getId().getSala().getCinema().getName());
            functionDTO.setLocal(tempFuntion.getId().getSala().getCinema().getLocation());
            functionDTO.setProviderName(tempFuntion.getId().getSala().getCinema().getProvider().getName());
            functionDTO.setStartTime(tempFuntion.getId().getDate());
            functionDTO.setMovie(tempFuntion.getMovie().toDTO());
            functionDTOs.add(functionDTO);
        }
        return functionDTOs;
    }



    @GetMapping("/function/{sala_id}/{date}")
    public void delateFunctionDtos(@PathVariable("movie_id") long movie_id, @PathVariable("date")LocalDateTime localDateTime) {







    }
    //TODO crear funcion;

}
