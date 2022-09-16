package com.alejandro.zuluaga.model;

import java.util.Date;

public class Match {

    /**
     * Home team name
     */
    private String homeTeam;

    /**
     * Away team name
     */
    private String awayTeam;

    /**
     * Home team goals scored
     */
    private Integer homeGoals;

    /**
     * Away team goals score
     */
    private Integer awayGoals;

    /**
     * Total goals in the match
     */
    private Integer totalGoals;

    /**
     * Match creation time
     */
    private Date createdAt;



    public Match(String homeTeam, String awayTeam) {
        super();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.createdAt = new Date();
        this.totalGoals = 0;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Integer getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(Integer homeGoals, Integer awayGoals) {
        this.totalGoals = homeGoals + awayGoals;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    @Override
    public String toString() {
        return homeTeam + " - " + awayTeam + ": " + homeGoals + " - " + awayGoals + "\n";
    }

}
