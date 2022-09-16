package com.alejandro.zuluaga;

import com.alejandro.zuluaga.model.Match;
import com.alejandro.zuluaga.services.ScoreBoardServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorldCupApp {

    private static final Logger logger = LogManager.getLogger();

    private static ScoreBoardServiceImpl scoreBoardServiceImpl = new ScoreBoardServiceImpl();

    public static void main(String[] args) {

        List<Match> matchesList = new ArrayList<>();
        try {
            scoreBoardServiceImpl.startGame(matchesList, "Mexico", "Canada");
            TimeUnit.SECONDS.sleep(1);
            scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
            TimeUnit.SECONDS.sleep(1);
            scoreBoardServiceImpl.startGame(matchesList, "Germany", "France");
            TimeUnit.SECONDS.sleep(1);
            scoreBoardServiceImpl.startGame(matchesList, "Uruguay", "Italy");
            TimeUnit.SECONDS.sleep(1);
            scoreBoardServiceImpl.startGame(matchesList, "Argentina", "Australia");
            scoreBoardServiceImpl.startGame(matchesList, "Portugal", "Denmark");

        } catch (InterruptedException e) {

            logger.error("[Main] Error starting game due interruption thread ", e);

        }

        scoreBoardServiceImpl.updateGame(matchesList, "Mexico", 0, 5);
        scoreBoardServiceImpl.updateGame(matchesList, "Spain", 10, 2);
        scoreBoardServiceImpl.updateGame(matchesList, "Germany", 2, 2);
        scoreBoardServiceImpl.updateGame(matchesList, "Uruguay", 6, 6);
        scoreBoardServiceImpl.updateGame(matchesList, "Argentina", 3, 1);

        scoreBoardServiceImpl.finishGame(matchesList, "Portugal");

        System.out.println(matchesList);

        System.out.println(scoreBoardServiceImpl.getSummaryScoreBoard(matchesList));

    }
}
