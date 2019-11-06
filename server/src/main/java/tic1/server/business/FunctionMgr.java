package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Sala;
import tic1.server.persistence.FunctionRepository;
import tic1.server.persistence.MovieRepository;

import java.time.LocalDateTime;

@Service
public class FunctionMgr {
    @Autowired
    private FunctionRepository funcionRepository;
    @Autowired
    private MovieRepository movieRepository;


    public Funcion getFunctionByPk(Sala sala, LocalDateTime localDateTime){
        FunctionPK functionPK = new FunctionPK(sala, localDateTime);
        return funcionRepository.getOne(functionPK);

    }
}
