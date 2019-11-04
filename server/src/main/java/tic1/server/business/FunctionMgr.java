package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Sala;
import tic1.server.persistence.FunctionRepository;

import java.time.LocalDateTime;

@Service
public class FunctionMgr {
    @Autowired
    private FunctionRepository funcionRepository;

    public Funcion getFunctionByPk(Sala sala, LocalDateTime localDateTime){
        FunctionPK functionPK = new FunctionPK(sala, localDateTime);
        return funcionRepository.getOne(functionPK);

    }
}
