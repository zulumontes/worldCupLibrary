package com.alejandro.zuluaga;


import com.alejandro.zuluaga.model.Match;
import com.alejandro.zuluaga.services.ScoreBoardServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class WorldCupAppTest
{

    private ScoreBoardServiceImpl scoreBoardServiceImpl = new ScoreBoardServiceImpl();

    /**
     * Test if start game works correctly
     */
    @Test
    public void testStartGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
    }

    /**
     * Test that doesn´t work start the same game twice
     */
    @Test
    public void testStartWrongGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
    }


    /**
     * Test if finish game works correctly
     */
    @Test
    public void testFinishGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
        scoreBoardServiceImpl.finishGame(matchesList, "Spain");
        Assert.assertEquals(0, matchesList.size());
    }

    /**
     * Test if is possible finish game that doesn´t exist
     */
    @Test
    public void testFinishWrongGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
        scoreBoardServiceImpl.finishGame(matchesList, "Mexico");
        Assert.assertEquals(1, matchesList.size());
    }

    /**
     * Test update game works correctly
     */
    @Test
    public void testUpdateGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getHomeGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getAwayGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getTotalGoals).orElseThrow().longValue());
        scoreBoardServiceImpl.updateGame(matchesList, "Spain", 1, 4);
        Assert.assertEquals(1, matchesList.stream().findFirst().map(Match::getHomeGoals).orElseThrow().longValue());
        Assert.assertEquals(4, matchesList.stream().findFirst().map(Match::getAwayGoals).orElseThrow().longValue());
        Assert.assertEquals(5, matchesList.stream().findFirst().map(Match::getTotalGoals).orElseThrow().longValue());
    }


    /**
     * Test that is not possible update game that doesn´t exist
     */
    @Test
    public void testUpdateWrongGame(){
        List<Match> matchesList = new ArrayList<>();
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        Assert.assertEquals(1, matchesList.size());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getHomeGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getAwayGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getTotalGoals).orElseThrow().longValue());
        scoreBoardServiceImpl.updateGame(matchesList, "Mexico", 1, 4);
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getHomeGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getAwayGoals).orElseThrow().longValue());
        Assert.assertEquals(0, matchesList.stream().findFirst().map(Match::getTotalGoals).orElseThrow().longValue());
    }

    /**
     * Test that matches list is sorted correctly
     * @throws InterruptedException
     */
    @Test
    public void testGetSummary() throws InterruptedException {
        List<Match> matchesList = new ArrayList<>();

        scoreBoardServiceImpl.startGame(matchesList, "Mexico", "Canada");
        TimeUnit.SECONDS.sleep(1);
        scoreBoardServiceImpl.startGame(matchesList, "Spain", "Brazil");
        TimeUnit.SECONDS.sleep(1);
        scoreBoardServiceImpl.startGame(matchesList, "Germany", "France");
        TimeUnit.SECONDS.sleep(1);
        scoreBoardServiceImpl.startGame(matchesList, "Uruguay", "Italy");
        TimeUnit.SECONDS.sleep(1);
        scoreBoardServiceImpl.startGame(matchesList, "Argentina", "Australia");
        scoreBoardServiceImpl.updateGame(matchesList, "Mexico", 0, 5);
        scoreBoardServiceImpl.updateGame(matchesList, "Spain", 10, 2);
        scoreBoardServiceImpl.updateGame(matchesList, "Germany", 2, 2);
        scoreBoardServiceImpl.updateGame(matchesList, "Uruguay", 6, 6);
        scoreBoardServiceImpl.updateGame(matchesList, "Argentina", 3, 1);

        matchesList = scoreBoardServiceImpl.getSummaryScoreBoard(matchesList);

        Assert.assertEquals("Uruguay", matchesList.get(0).getHomeTeam());
        Assert.assertEquals("Spain", matchesList.get(1).getHomeTeam());
        Assert.assertEquals("Mexico", matchesList.get(2).getHomeTeam());
        Assert.assertEquals("Argentina", matchesList.get(3).getHomeTeam());
        Assert.assertEquals("Germany", matchesList.get(4).getHomeTeam());


    }

}
