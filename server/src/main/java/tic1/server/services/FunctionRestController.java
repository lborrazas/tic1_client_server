package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.NewMovieDTO;
import tic1.server.business.FunctionMgr;
import tic1.server.business.SalaMgr;
import tic1.server.entities.Function;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;

import java.time.LocalDateTime;

public class FunctionRestController {

    @Autowired
    FunctionMgr functionMgr;
    @Autowired
    SalaMgr salaMgr;

    @GetMapping("/function/{sala_id}/{date}")
    public FunctionDTO getFunctionDto(@PathVariable("sala_id") long sala_id, @PathVariable("date")LocalDateTime localDateTime){
        FunctionDTO functionDTO = new FunctionDTO();
        Sala sala = salaMgr.getSalaById(sala_id);
        Function function = functionMgr.getFunctionByPk(sala, localDateTime);

        functionDTO.setSala(sala.getId());
        functionDTO.setCinemaName(sala.getCinema().getName());
        functionDTO.setLocal(sala.getCinema().getLocation());
        functionDTO.setProviderName(sala.getCinema().getProvider().getName());
        functionDTO.setStartTime(function.getId().getDate());
        functionDTO.setMovie(function.getMovie().toDTO());
        return functionDTO;
    }

    //TODO crear funcion;

}
