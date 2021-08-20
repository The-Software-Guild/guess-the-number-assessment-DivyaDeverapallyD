/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.data;

import com.aj.cowsandbullsassessment.models.Game;
import com.aj.cowsandbullsassessment.models.Round;
import java.util.List;

/**
 *
 * @author DivyaDeverapally
 */
public interface GameDao {
    
     Game add(Game game);
         List<Game> getAll();
         Game getGameById(int id);
         Round saveRound(Round round,int  gameId);

    public void updateGameAsFinished(int gameId);

    public List<Round> getRoundsofGame(int gameId);
    public List<Round> getAllRounds();
    public void deletGameById(int gameId);
    public void deleteRoundById(int roundId);
    public Round getRoundById(int roundId);
    
}
