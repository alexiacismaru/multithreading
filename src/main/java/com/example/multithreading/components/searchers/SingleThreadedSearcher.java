package com.example.multithreading.components.searchers;

import com.example.multithreading.config.ApplicationProperties;
import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("singleThreadedSearcher")
public class SingleThreadedSearcher implements Searcher {
    private final ArtistRepository artistRepository;
    private final ApplicationProperties applicationProperties;

    public SingleThreadedSearcher(ArtistRepository artistRepository, ApplicationProperties applicationProperties) {
        this.artistRepository = artistRepository;
        this.applicationProperties = applicationProperties;
    }

    public List<Artist> searchDatabase() {
        List<Artist> matchingRecords = new ArrayList<>();
        for (long id = 1; id <= applicationProperties.getRecordCount(); id++) {
            Artist record = artistRepository.findById(id).orElseThrow();
            if (record.getName().startsWith("M") && record.getDebutYear() < 2000 && record.getDebutYear() > 1940) {
                matchingRecords.add(record);
            }

            for (int j = 0; j < 5 && j < matchingRecords.size(); j++) {
                Artist artist = matchingRecords.get(j);
                System.out.println("First five artists:");
                System.out.println(artist.getName() + " " + artist.getGender() + " " + artist.getDebutYear());
            }
        }
        return matchingRecords;
    }
}
