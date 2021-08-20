/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.data;


import com.aj.cowsandbullsassessment.models.Game;
import com.aj.cowsandbullsassessment.models.Round;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DivyaDeverapally
 */
//@Repository
public class GameDaoInMemoryImpl implements GameDao {
    private final List<Game> games= new ArrayList<Game>();

    @Override
    public Game add(Game game) {
            int nextId = games.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        game.setId(nextId);
        games.add(game);
        return game;
    }

    @Override
    public List<Game> getAll() {
       return new ArrayList<>(games);
    }

    @Override
    public Game getGameById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round saveRound(Round round,int  gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGameAsFinished(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRoundsofGame(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getAllRounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletGameById(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRoundById(int roundId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round getRoundById(int roundId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
