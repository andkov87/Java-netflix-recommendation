package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.data.enums.Role;
import com.codecool.netflix.data.enums.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("FieldCanBeLocal")
public class SimilarityScoreCalculator {
    private final Integer POINT_FOR_SAME_TYPE = 10;
    private final Integer POINT_FOR_EACH_SIMILAR_GENRE = 20;
    private final Integer POINT_FOR_EACH_SIMILAR_ACTOR = 15;
    private final Integer POINT_FOR_EACH_SIMILAR_DIRECTOR = 30;

    public Integer calculateSimilarityScore(Title titleOfInterest, List<Credit> titleOfInterestCredits, Title comparedTitle, List<Credit> allCredits) {

        // score by type-match
        Integer typeScore = getSimilarityScoreBasedOnType(titleOfInterest.getType(), comparedTitle.getType());

        //score by genre match
        Integer genreScore = getSimilarityScoreBasedOnGenre(titleOfInterest.getGenres(),comparedTitle.getGenres());

        //score by actors
        List<String> titleOfInterestActorName = getCreditsByRole(titleOfInterestCredits, Role.ACTOR);
        List<String> comparedTitleActorName = getCreditsByRole(getCastForTitle(comparedTitle,allCredits), Role.ACTOR);
        int actorScore = getSimilarityScoreBasedOnActors(titleOfInterestActorName,comparedTitleActorName);

        //score by directors
        List<String> titleOfInterestDirectorName = getCreditsByRole(titleOfInterestCredits, Role.DIRECTOR);
        List<String> comparedTitleDirectorName = getCreditsByRole(getCastForTitle(comparedTitle,allCredits), Role.DIRECTOR);
        int directorScore = getSimilarityScoreBasedOnDirectors(titleOfInterestDirectorName, comparedTitleDirectorName);

        //score by Imdb score
        int imdbScore = getPointsForImdbScore(comparedTitle);

        return typeScore + genreScore + actorScore + directorScore + imdbScore;
    }
//
    private List<Credit> getCastForTitle(Title title, List<Credit> credits) {
        String creditId = title.getId();

        List<Credit> castForTitle = credits.stream()
                .filter(credit -> creditId.equals(credit.getId()))
                .toList();

        return castForTitle;
    }

    private Integer getSimilarityScoreBasedOnType(Type type1, Type type2) {

        return Stream.of(type1,type2)
                .distinct()
                .count() == 1 ? POINT_FOR_SAME_TYPE : 0;
    }

    private Integer getSimilarityScoreBasedOnGenre(List<String> genre1, List<String> genre2) {
            List<String> commonGenres = genre1.stream()
                    .filter(genre2::contains)
                    .distinct()
                    .toList();

        return commonGenres.size() * POINT_FOR_EACH_SIMILAR_GENRE;
    }

    private List<String> getCreditsByRole(List<Credit> credits, Role role) {

        List<Role> roleToMatch = List.of(role);

        List<String> namesByRole = credits.stream()
                .filter(credit -> roleToMatch.contains(credit.getRole()))
                .map(Credit::getName)
                .toList();

        return namesByRole;
    }

    private Integer getSimilarityScoreBasedOnActors(List<String> actors1, List<String> actors2) {

        List<String> commonActors = actors1.stream()
                .filter(actors2::contains)
                .distinct()
                .toList();

        return commonActors.size() * POINT_FOR_EACH_SIMILAR_ACTOR;
    }

    private Integer getSimilarityScoreBasedOnDirectors(List<String> directors1, List<String> directors2) {
        List<String> commonDirectors = directors1.stream()
                .filter(directors2::contains)
                .distinct()
                .toList();

        return commonDirectors.size() * POINT_FOR_EACH_SIMILAR_DIRECTOR;
    }

    private int getPointsForImdbScore(Title comparedTitle) {

        return comparedTitle.getImdbScore() == null ? 0 : Math.round(comparedTitle.getImdbScore());
    }

}
