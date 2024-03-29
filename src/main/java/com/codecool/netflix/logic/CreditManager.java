package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.logic.reader.CreditReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditManager implements CsvItemCollection {
    private final CreditReader reader;
    private List<Credit> credits;

    public CreditManager(CreditReader reader) {
        this.reader = reader;
    }

    @Override
    public void init() throws IOException {
        credits = reader.readAll("/credits.csv");
    }

    public List<Title> getAllTitlesByCreditName(String name, List<Title> titles) {
        List<String> getActorId = credits.stream()
                .filter(credit -> credit.getName().equalsIgnoreCase(name))
                .map(Credit::getId)
                .toList();

        List<Title> getSameTitleId = titles.stream()
                .filter(title -> getActorId.contains(title.getId()))
                .toList();

        return getSameTitleId;
    }

    public List<Credit> getCredits() {
        return this.credits;
    }
}
