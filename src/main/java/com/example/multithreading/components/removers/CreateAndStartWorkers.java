package com.example.multithreading.components.removers;

import com.example.multithreading.domain.Artist;
import com.example.multithreading.repository.ArtistRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class CreateAndStartWorkers {
    private final ArtistRepository repository;

    public CreateAndStartWorkers(ArtistRepository repository) {
        this.repository = repository;
    }

    public void createAndStartRemoverWorkers() throws InterruptedException {
        List<Artist> artists = repository.findAll();

        Predicate<Artist> predicate1 = record -> record.getDebutYear() == (1990);
        int matchingYear = (int) artists.stream().filter(predicate1).count();
        System.out.println(matchingYear + " artists that debuted in 1990.");
        RemoverWorker worker1 = new RemoverWorker(artists, predicate1);
        Thread thread1 = new Thread(worker1);
        thread1.start();
        thread1.join();
        matchingYear = (int) artists.stream().filter(predicate1).count();

        Predicate<Artist> predicate2 = record -> record.getGender().equals("Male");
        int matchingGender = (int) artists.stream().filter(predicate2).count();
        System.out.println(matchingGender + " artists that are male.");
        RemoverWorker worker2 = new RemoverWorker(artists, predicate2);
        Thread thread2 = new Thread(worker2);
        thread2.start();
        thread2.join();
        matchingGender = (int) artists.stream().filter(predicate2).count();

        Predicate<Artist> predicate3 = record -> record.getName().equals("Picasso");
        int matchingName = (int) artists.stream().filter(predicate3).count();
        System.out.println(matchingName + " artists named Picasso.");
        RemoverWorker worker3 = new RemoverWorker(artists, predicate3);
        Thread thread3 = new Thread(worker3);
        thread3.start();
        thread3.join();
        matchingName = (int) artists.stream().filter(predicate3).count();

        System.out.println(" ");
        System.out.println("Starting to remove records.");
        System.out.println(matchingYear + " artists that debuted in 1990.");
        System.out.println(matchingGender + " artists that are male.");
        System.out.println(matchingName + " artists named Picasso.");
    }
}
