package tic1.client.seeders;

import tic1.client.models.Cinema;
import tic1.client.models.Provider;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.ProviderRestTemplate;
import tic1.commons.transfers.CinemaDto;

import java.util.List;

public class CinemaSeeder {
    public static void main(String[] args) {
        CinemaRestTemplate cinemaRestTemplate = new CinemaRestTemplate();
        ProviderRestTemplate providerRestTemplate = new ProviderRestTemplate();
        List<Provider> providerList = providerRestTemplate.get();
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setName("juanito el weorfamit");
        for (int i=0;i<10;i++){double a = Math.random() * 10;
        long providerId = providerList.get((int) a).getId();
        cinemaDto.setProvider(providerId);
        cinemaRestTemplate.createCinema(new Cinema(cinemaDto));
    }
    }
}
