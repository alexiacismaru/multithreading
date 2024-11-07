package com.example.multithreading.components.searchers;

import com.example.multithreading.config.ApplicationProperties;
import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Qualifier("multiThreadedSearcher")
public class MultithreadedSearcher implements Searcher {
    private final ApplicationProperties properties;
    private final ArtistRepository repository;

    public MultithreadedSearcher(ApplicationProperties properties, ArtistRepository repository) {
        this.properties = properties;
        this.repository = repository;
    }

    @Override
    public List<Artist> searchDatabase() {
        var allFutures = generateFutures(8);

        for (CompletableFuture<List<Artist>> future : allFutures) {
            future.join();
        }
        return null;
    }

    private List<CompletableFuture<List<Artist>>> generateFutures(int amount) {
        List<CompletableFuture<List<Artist>>> futures = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int startId = (properties.getRecordCount() / amount) * i + 1;
            int endId = startId + properties.getRecordCount() / amount;
            CompletableFuture<List<Artist>> completableFuture = CompletableFuture.supplyAsync(new
                    SearcherWorker(repository, startId, endId));

            futures.add(completableFuture);
        }
        return futures;
    }
}
