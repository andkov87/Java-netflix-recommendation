package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.data.enums.Role;
import com.codecool.netflix.data.enums.Type;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class SimilarityScoreCalculator {
    private final Integer POINT_FOR_SAME_TYPE = 10;
    private final Integer POINT_FOR_EACH_SIMILAR_GENRE = 20;
    private final Integer POINT_FOR_EACH_SIMILAR_ACTOR = 15;
    private final Integer POINT_FOR_EACH_SIMILAR_DIRECTOR = 30;

    public Integer calculateSimilarityScore(Title titleOfInterest, List<Credit> titleOfInterestCredits, Title comparedTitle, List<Credit> allCredits) {
        return 0;
        //TODO: Your code here
    }

    private List<Credit> getCastForTitle(Title title, List<Credit> credits) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    private Integer getSimilarityScoreBasedOnType(Type type1, Type type2) {
        return 0;
        //TODO: Your code here
    }

    private Integer getSimilarityScoreBasedOnGenre(List<String> genre1, List<String> genre2) {
        return 0;
        //TODO: Your code here
    }

    private List<String> getCreditsByRole(List<Credit> credits, Role role) {
        return new ArrayList<>();
        //TODO: Your code here
    }

    private Integer getSimilarityScoreBasedOnActors(List<String> actors1, List<String> actors2) {
        return 0;
        //TODO: Your code here
    }

    private Integer getSimilarityScoreBasedOnDirectors(List<String> directors1, List<String> directors2) {
        return 0;
        //TODO: Your code here
    }

    private int getPointsForImdbScore(Title comparedTitle) {
        return 0;
        //TODO: Your code here
    }

}
