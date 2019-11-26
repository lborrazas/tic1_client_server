package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.CinemaDto;
import tic1.commons.transfers.ProviderDTO;
import tic1.server.business.CinemaMgr;
import tic1.server.business.ProviderMgr;
import tic1.server.entities.Cinema;
import tic1.server.entities.Provider;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    CinemaMgr cinemaMgr;
    @Autowired
    ProviderMgr providerMgr;

    private Provider providerFromDto(ProviderDTO providerDTO) {
        Provider provider = new Provider();
        provider.setName(providerDTO.getName());
        provider.setId(providerDTO.getId());

        return provider;
    }

    private Cinema cinemaFromDto(CinemaDto cinemaDto) {
        Cinema cinema = new Cinema();
        cinema.setProvider(providerMgr.getById(cinemaDto.getProviderId()));
        cinema.setLocation(cinemaDto.getLocation());
        cinema.setId(cinemaDto.getId());
        cinema.setName(cinemaDto.getName());
        return cinema;
    }


    @GetMapping("/cinema/")
    public List<CinemaDto> All() {
        List<CinemaDto> cinemasDtos = new ArrayList<>();
        List<Cinema> cinemas = cinemaMgr.findAll();
        for (Cinema cin : cinemas) {
            cinemasDtos.add(cin.toDTO()); // mate hacete el dto
        }
        return cinemasDtos;

    }

    @GetMapping("/cinema/id/{id}")
    public CinemaDto cine(@PathVariable("id") long id) {
        Cinema cinema = cinemaMgr.getOne(id);
        return cinema.toDTO(); // mate hacete el dto
    }

    @GetMapping("/cinema/name/{name}")
    public List<CinemaDto> cineByName(@PathVariable String name) {
        List<CinemaDto> cinemasDtos = new ArrayList<>();
        List<Cinema> cinemas = cinemaMgr.getByName(name);
        for (Cinema cin : cinemas) {
            cinemasDtos.add(cin.toDTO()); // mate hacete el dto
        }
        return cinemasDtos;
    }

    @GetMapping("/cinema/provider/{id}")
    public List<CinemaDto> cineByProviderId(@PathVariable long id) {
        List<CinemaDto> cinemasDtos = new ArrayList<>();
        List<Cinema> cinemas = cinemaMgr.getByProviderId(id);
        for (Cinema cin : cinemas) {
            cinemasDtos.add(cin.toDTO()); // mate hacete el dto
        }
        return cinemasDtos;
    }

    @GetMapping("/cinema/{providerDTo}")
    public List<CinemaDto> cineByByProviderName(@PathVariable ProviderDTO providerDTO) {
        List<CinemaDto> cinemasDtos = new ArrayList<>();
        List<Cinema> cinemas = cinemaMgr.getByProviderName(providerDTO.getName());
        for (Cinema cin : cinemas) {
            cinemasDtos.add(cin.toDTO()); // mate hacete el dto
        }
        return cinemasDtos;
    }


    @PostMapping("/cinema/{providerId}")
    public void save(@RequestBody CinemaDto cinemaDto, @PathVariable("providerId") long providerID) {
        System.out.println(providerID);
        cinemaDto.setProvider(providerID);
        Cinema cinema = cinemaFromDto(cinemaDto);
        cinemaMgr.save(cinema);
    }

    @DeleteMapping("/cinema/{id}")
    public void delete(@PathVariable long id) {
        cinemaMgr.deleteCinema(id);
    }
}
