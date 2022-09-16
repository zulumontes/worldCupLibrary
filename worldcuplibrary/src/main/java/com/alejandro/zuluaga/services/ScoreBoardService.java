package com.alejandro.zuluaga.services;

import com.alejandro.zuluaga.model.Match;

import java.util.List;


public interface ScoreBoardService {

    void startGame(List<Match> matchesList, String homeTeam, String awayTeam);

    void finishGame(List<Match> matchesList, String homeTeam);

    void updateGame(List<Match> matchesList, String homeTeam, Integer homeGoals, Integer awayGoals);

    List<Match> getSummaryScoreBoard(List<Match> matchesList);

}
