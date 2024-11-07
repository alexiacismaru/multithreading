package com.example.multithreading.components.seeders;

import com.example.multithreading.config.ApplicationProperties;
import com.example.multithreading.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Qualifier("multiThreadedSeeder")
public class MultithreadedSeeder implements Seeder {
    private final ArtistRepository artistRepository;
    private final ApplicationProperties properties;

    @Autowired
    public MultithreadedSeeder(ArtistRepository artistRepository, ApplicationProperties properties) {
        this.artistRepository = artistRepository;
        this.properties = properties;
    }

    @Override
    public void seedDatabase() {
        var allFutures = generateFutures(2);

        for (CompletableFuture<Void> future : allFutures) {
            future.join();
        }
    }

    private List<CompletableFuture<Void>> generateFutures(int amount) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int startId = (properties.getRecordCount() / amount) * i + 1;
            int endId = startId + properties.getRecordCount() / amount;
            CompletableFuture<Void> future
                    = CompletableFuture.runAsync(
                    new SeederWorker(artistRepository, startId, endId)
            );
            futures.add(future);
        }
        return futures;
    }
}
