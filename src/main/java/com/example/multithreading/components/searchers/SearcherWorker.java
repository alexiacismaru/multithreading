package com.example.multithreading.components.searchers;

import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SearcherWorker implements Supplier<List<Artist>> {
    private final ArtistRepository artistRepository;
    private final long idFrom;
    private final long idUntil;

    public SearcherWorker(ArtistRepository artistRepository, long idFrom, long idUntil) {
        this.artistRepository = artistRepository;
        this.idFrom = idFrom;
        this.idUntil = idUntil;
    }

    @Override
    public List<Artist> get() {
        List<Artist> matchingRecords = new ArrayList<>();
        for (long id = idFrom; id <= idUntil; id++) {
            Optional<Artist> record = artistRepository.findById(id);
            record.ifPresent(matchingRecords::add);
        }
        return matchingRecords;
    }
}

