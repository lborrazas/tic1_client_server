package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.entities.Cinema;
import tic1.server.persistence.CinemaRepository;

@Service
public class CinemaMgr {
    @Autowired
    CinemaRepository cinemaRepository;


}
