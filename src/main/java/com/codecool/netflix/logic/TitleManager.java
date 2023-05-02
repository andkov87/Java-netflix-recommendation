package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.data.TitleWithSimilarityScore;
import java.util.NoSuchElementException;
import com.codecool.netflix.logic.reader.TitleReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitleManager implements CsvItemCollection {
    private final TitleReader reader;
    private final SimilarityScoreCalculator comparator;
    private List<Title> titles;

    public TitleManager(TitleReader reader, SimilarityScoreCalculator comparator) {
        this.reader = reader;
        this.comparator = comparator;
    }

    @Override
    public void init() throws IOException {
        titles = reader.readAll("/titles.csv");
    }

    public List<Title> getTopNImdbScoreFromTitles(int n) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    public List<Credit> getAllCreditsForTitle(String userTitle, List<Credit> credits) throws NoSuchElementException {
        return new ArrayList<>();
        //TODO: Your code here
    }

    public List<Title> getTopNImdbScoreFromGivenGenre(String genre) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    // Extra task - offset
    public List<Title> getTopNImdbScoreFromGivenGenre(String genre, int offset) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    public List<TitleWithSimilarityScore> getSimilarMoviesByTitle(String titleName, List<Credit> allCredits) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    public List<Title> getTitles() {
        return this.titles;
    }
}
