package com.alejandro.zuluaga.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.alejandro.zuluaga.model.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreBoardServiceImpl implements ScoreBoardService{

    private static final Logger logger = LogManager.getLogger();

    /**
     * Start game and add to ScoreBoard
     * @param matchesList
     * @param homeTeam
     * @param awayTeam
     */
    @Override
    public void startGame(List<Match> matchesList, String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        try {

            if(matchesList.stream().filter(m -> m.getHomeTeam().equals(homeTeam)).collect(Collectors.toSet()).isEmpty()) {
                matchesList.add(match);
                logger.info("[StartGame] Game {} vs {} started correctly at {}", match.getHomeTeam(), match.getAwayTeam(), match.getCreatedAt());
            }else {
                logger.info("[StartGame] Already exists a game for {} like home team", match.getHomeTeam());
            }
        }catch(Exception e) {
            logger.error("[StartGame] Error: ", e);
        }
    }


    /**
     * Finish game and remove it from score board
     * @param matchesList
     * @param homeTeam
     */
    @Override
    public void finishGame(List<Match> matchesList, String homeTeam) {
        try {
            if(!matchesList.stream().filter(m -> m.getHomeTeam().equals(homeTeam)).collect(Collectors.toList()).isEmpty()) {
                matchesList.remove(matchesList.stream().filter(m -> m.getHomeTeam().equals(homeTeam)).findFirst().orElseThrow());
                logger.info("[FinishGame] Game  {} finished", homeTeam);
            }else {
                logger.info("[FinishGame] Dooesn´t exists any match with Home team {}", homeTeam);
            }
        }catch(Exception e) {
            logger.error("[FinishGame] Error: ", e);
        }

    }

    /**
     * Update goals for a game in score board
     * @param matchesList
     * @param homeTeam
     * @param homeGoals
     * @param awayGoals
     */
    @Override
    public void updateGame(List<Match> matchesList, String homeTeam, Integer homeGoals, Integer awayGoals) {
        try {
            if(!matchesList.stream().filter(m -> m.getHomeTeam().equals(homeTeam)).collect(Collectors.toList()).isEmpty()) {
                matchesList.stream().filter(m -> m.getHomeTeam().equals(homeTeam)).forEach(m -> {
                    m.setHomeGoals(homeGoals);
                    m.setAwayGoals(awayGoals);
                    m.setTotalGoals(homeGoals, awayGoals);
                });
                logger.info("[UpdateGame]");
            }else {
                logger.info("[UpdateGame] Doesn´t exists game for {}", homeTeam);
            }
        }catch(Exception e) {
            logger.error("[UpdateGame] Error: ", e);
        }
    }

    /**
     * Get sumary sorted of score board
     * @param matchesList
     * @return
     */
    @Override
    public List<Match> getSummaryScoreBoard(List<Match> matchesList) {
        Comparator<Match> matchComparator = Comparator.comparing(Match::getTotalGoals).reversed().thenComparing(Comparator.comparing(Match::getCreatedAt).reversed());
        logger.info("[GetSumary] Returning {} matches", matchesList.size());
        return matchesList.stream().sorted(matchComparator).collect(Collectors.toList());

    }



}
