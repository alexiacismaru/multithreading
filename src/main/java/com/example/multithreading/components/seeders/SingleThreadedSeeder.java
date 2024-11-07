package com.example.multithreading.components.seeders;

import com.example.multithreading.config.ApplicationProperties;
import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("singleThreadedSeeder")
public class SingleThreadedSeeder implements Seeder {
    private static final Logger logger = LoggerFactory.getLogger(SingleThreadedSeeder.class);
    private final ArtistRepository artistRepository;
    private final ApplicationProperties properties;

    @Autowired
    public SingleThreadedSeeder(ArtistRepository artistRepository, ApplicationProperties properties) {
        this.artistRepository = artistRepository;
        this.properties = properties;
    }

    @Override
    public void seedDatabase() {
        logger.info("AMOUNT: {}", properties.getRecordCount());

        for (int i = 0; i < properties.getRecordCount(); i++) {
            var faker = new Faker();
            artistRepository.save(new Artist(
                    (long) i + 1,
                    faker.artist().name(),
                    faker.gender().binaryTypes(),
                    faker.number().numberBetween(1900, 2023))); //random debut year
        }
    }
}

