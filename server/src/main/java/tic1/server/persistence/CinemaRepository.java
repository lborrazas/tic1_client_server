package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities.Cinema;
import tic1.server.entities.Provider;

import java.awt.print.Pageable;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByName(String name);

    List<Cinema> findAllByProvider(Provider provider);

    List<Cinema> findAllByProviderId(long providerId);

    List<Cinema> findAllByProviderName(String providerName);
}
