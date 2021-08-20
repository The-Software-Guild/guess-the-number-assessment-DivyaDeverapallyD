/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author DivyaDeverapally
 */
public class Round {
    
    private int roundId;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.roundId;
        hash = 37 * hash + Objects.hashCode(this.guess);
        hash = 37 * hash + Objects.hashCode(this.guessTime);
        hash = 37 * hash + Objects.hashCode(this.result);
        hash = 37 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        return true;
    }
    private String guess;
    private Date guessTime;
    private String result;
    private int gameId;
    

    /**
     * @return the guess
     */
    public String getGuess() {
        return guess;
    }

    /**
     * @param guess the guess to set
     */
    public void setGuess(String guess) {
        this.guess = guess;
    }

    /**
     * @return the guessTime
     */
    public Date getGuessTime() {
        return guessTime;
    }

    /**
     * @param guessTime the guessTime to set
     */
    public void setGuessTime(Date guessTime) {
        this.guessTime = guessTime;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    
}
