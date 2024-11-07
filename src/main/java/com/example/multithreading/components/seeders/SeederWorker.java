package com.example.multithreading.components.seeders;

import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;
import net.datafaker.Faker;

public class SeederWorker implements Runnable {
    private final ArtistRepository artistRepository;
    private final int idFrom;
    private final int idUntil;

    public SeederWorker(ArtistRepository artistRepository, int idFrom, int idUntil) {
        this.artistRepository = artistRepository;
        this.idFrom = idFrom;
        this.idUntil = idUntil;
    }

    @Override
    public void run() {
        for (int i = idFrom; i <= idUntil; i++) {
            var faker = new Faker();
            artistRepository.save(new Artist(
                    (long) i,
                    faker.artist().name(),
                    faker.gender().binaryTypes(),
                    faker.number().numberBetween(1900, 2023)));
        }
    }
}

