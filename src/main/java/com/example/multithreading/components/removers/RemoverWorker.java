package com.example.multithreading.components.removers;

import com.example.multithreading.domain.Artist;

import java.util.List;
import java.util.function.Predicate;

public class RemoverWorker implements Runnable {
    private final List<Artist> artists;
    private final Predicate<Artist> predicate;

    public RemoverWorker(List<Artist> artists, Predicate<Artist> predicate) {
        this.artists = artists;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        artists.removeIf(predicate);

    }
}
