package com.example.multithreading;

import com.example.multithreading.components.removers.CreateAndStartWorkers;
import com.example.multithreading.components.searchers.Searcher;
import com.example.multithreading.components.searchers.SingleThreadedSearcher;
import com.example.multithreading.components.seeders.Seeder;
import com.example.multithreading.domain.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@ConfigurationPropertiesScan
@EntityScan("com.example.multithreading")
public class MultithreadingApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MultithreadingApplication.class);
            private final Seeder seeder;
//    private final Searcher searcher;
//    private final CreateAndStartWorkers remover;
//    private final SingleThreadedSearcher searcher;

    public MultithreadingApplication(
            @Qualifier("singleThreadedSeeder") Seeder seeder) {
        StopWatch watch = new StopWatch();
        watch.start();
        this.seeder = seeder;
        watch.stop();
        System.out.println("Total execution time to seed the database: "
                + watch.getTotalTimeNanos());
    }

//    public MultithreadingApplication(
//            @Qualifier("multiThreadedSeeder") Seeder seeder) {
//        StopWatch watch = new StopWatch();
//        watch.start();
//        this.seeder = seeder;
//        watch.stop();
//        System.out.println("Total execution time to seed the database: "
//                + watch.getTotalTimeNanos());
//    }

//    public MultithreadingApplication(
//            @Qualifier("multiThreadedSearcher") Searcher searcher) {
//        StopWatch watch = new StopWatch();
//        watch.start();
//        this.searcher = searcher;
//        watch.stop();
//        System.out.println("Total execution time to search the database: "
//                + watch.getTotalTimeNanos());
//        System.out.println("First five artists:");
//        for (int i = 0; i < 5 && i < searcher.searchDatabase().size(); i++) {
//            Artist artist = searcher.searchDatabase().get(i);
//            System.out.println(artist.getName() + " " + artist.getGender() + " " + artist.getDebutYear());
//        }
//    }

//    public MultithreadingApplication(
//            @Qualifier("singleThreadedSearcher") SingleThreadedSearcher searcher) {
//        StopWatch watch = new StopWatch();
//        watch.start();
//        this.searcher = searcher;
//        watch.stop();
//
//        System.out.println("Total execution time to search the database: "
//                + watch.getTotalTimeNanos());
//        System.out.println("First five artists:");
//        for (int i = 0; i < 5 && i < searcher.searchDatabase().size(); i++) {
//            Artist artist = searcher.searchDatabase().get(i);
//            System.out.println(artist.getName() + " " + artist.getGender()+ " " + artist.getDebutYear());
//        }
//    }

//    public MultithreadingApplication(CreateAndStartWorkers remover) {
//        this.remover = remover;
//    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Seeding database ...");
        this.seeder.seedDatabase();
//        logger.info("Searching database");
//        this.searcher.searchDatabase();
//        logger.info("Removing from the database");
//        this.remover.createAndStartRemoverWorkers();
    }

    public static void main(String[] args) {
        SpringApplication.run(MultithreadingApplication.class);
    }
}
