package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tic1.server.business.TransaccionMgr;
import tic1.server.entities.Transaccion;

@RestController
public class TransaccionRestController {
@Autowired
    TransaccionMgr transaccionMgr;

}
