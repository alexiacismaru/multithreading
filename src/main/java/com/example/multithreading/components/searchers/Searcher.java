package com.example.multithreading.components.searchers;

import com.example.multithreading.domain.Artist;

import java.util.List;

public interface Searcher {
    List<Artist> searchDatabase();
}
