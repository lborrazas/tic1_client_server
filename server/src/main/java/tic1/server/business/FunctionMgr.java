package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.persistence.FunctionRepository;

@Service
public class FunctionMgr{
    @Autowired
private FunctionRepository funcionRepository;
}
