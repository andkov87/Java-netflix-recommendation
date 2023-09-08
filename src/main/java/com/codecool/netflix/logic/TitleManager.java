package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.data.TitleWithSimilarityScore;

import java.util.Comparator;
import java.util.NoSuchElementException;
import com.codecool.netflix.logic.reader.TitleReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Title> topTitles = titles.stream()
                .filter(title -> title.getImdbScore() != null)
                .sorted(Comparator.comparing(Title::getImdbScore).reversed())
                .limit(n)
                .toList();

                return topTitles;
    }

    public List<Credit> getAllCreditsForTitle(String userTitle, List<Credit> credits) {

        List<Title> inputTitle = titles.stream()
                .filter(title -> title.getTitle().equalsIgnoreCase(userTitle))
                .toList();


            List<Credit> receiveActors = credits.stream()
                    .filter(credit -> credit.getId().equals(inputTitle.get(0).getId()))
                    .toList();

        return receiveActors;
    }

    public List<Title> getTopNImdbScoreFromGivenGenre(String genre, Integer n) {
        List<Title> matchingGenres = titles.stream()
                .filter(title -> title.getGenres().contains(genre))
                .toList();

        List<Title> topNHighestScore = matchingGenres.stream()
                .filter(title -> title.getImdbScore() != null)
                .sorted(Comparator.comparing(Title::getImdbScore).reversed())
                .limit(n)
                .toList();

        return topNHighestScore;
    }

    // Extra task - offset
//    public List<Title> getTopNImdbScoreFromGivenGenre(String genre,int offset) {
//        return new ArrayList<>();
//        //TODO: Your code here
//    }

    public List<TitleWithSimilarityScore> getSimilarMoviesByTitle(String titleName, List<Credit> allCredits, int n) {

        Title searchedMovie = titles.stream()
                .filter(title -> title.getTitle().equalsIgnoreCase(titleName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("<" + titleName + "> not found!"));


        return titles.stream()
                .filter(title -> !title.getTitle().equalsIgnoreCase(titleName))
                .map(title -> {
                        int similarityScore = comparator.calculateSimilarityScore(searchedMovie,allCredits,title,allCredits);
                        return new TitleWithSimilarityScore(title,similarityScore);
                })

                .sorted(Comparator.comparingInt(TitleWithSimilarityScore::getSimilarityScore).reversed())
                .limit(n)
                .toList();
    }

    public List<Title> getTitles() {
        return this.titles;
    }
}
