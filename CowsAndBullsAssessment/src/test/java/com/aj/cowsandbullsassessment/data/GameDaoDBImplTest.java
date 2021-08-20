/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.data;

import com.aj.cowsandbullsassessment.TestApplicationConfiguration;
import com.aj.cowsandbullsassessment.models.Game;
import com.aj.cowsandbullsassessment.models.Round;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author DivyaDeverapally
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoDBImplTest {
      
    @Autowired
    GameDao gameDao;
    
    public GameDaoDBImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         
        List<Round> rounds= gameDao.getAllRounds();
        for(Round round: rounds){
            gameDao.deleteRoundById(round.getRoundId());
        }
        
        List<Game> games= gameDao.getAll();
        for(Game game: games){
            gameDao.deletGameById(game.getId());
        }
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class GameDaoDBImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus("in progress");
        game=gameDao.add(game);
        Game fromDao= gameDao.getGameById(game.getId());
        assertEquals(game,fromDao);
    
    }

    /**
     * Test of getAll method, of class GameDaoDBImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus("in progress");
        gameDao.add(game);
        Game game1 = new Game();
        game1.setAnswer("5678");
        game1.setStatus("in progress");
        
        gameDao.add(game1);
        
        List<Game> games= gameDao.getAll();
        assertEquals(2,games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game1));
        
        
        
       
    }

    /**
     * Test of getGameById method, of class GameDaoDBImpl.
     */
    @Test
    public void testGetGameById() {
        
       Game game = new Game();
        game.setAnswer("1234");
        game.setStatus("in progress");
        game=gameDao.add(game);
        Game fromDao= gameDao.getGameById(game.getId());
        assertEquals(game,fromDao);
    }

    /**
     * Test of saveRound method, of class GameDaoDBImpl.
     */
    @Test
    public void testSaveRound() {
          Game game = new Game();
         game.setAnswer("1234");
        game.setStatus("in progress");
        game=gameDao.add(game);
        Game fromDao= gameDao.getGameById(game.getId());
        System.out.println("saveRound");
        Round round= new Round();
        round.setGuess("4231");
        round.setResult("e:2:p:2");
      //  Date date= ne
        round.setGuessTime(new Date());
       // round.setGameId(game.getId());
        
         round= gameDao.saveRound(round, game.getId());
        // Round fromDao= gameDao.get
        //Round round = null;
        assertEquals(round, round);
    }

    /**
     * Test of updateGameAsFinished method, of class GameDaoDBImpl.
     */
    @Test
    public void testUpdateGameAsFinished() {
      Game game = new Game();
        game.setAnswer("1234");
        game.setStatus("in progress");
        game=gameDao.add(game);
        Game fromDao= gameDao.getGameById(game.getId());
        assertEquals(game,fromDao);
        game.setStatus("finished");
        gameDao.updateGameAsFinished(game.getId());
        fromDao= gameDao.getGameById(game.getId());
        assertEquals(game,fromDao);
        
    }

    /**
     * Test of getRoundsofGame method, of class GameDaoDBImpl.
     */
    @Test
    public void testGetRoundsofGame() {
        
        Game game = new Game();
        game.setAnswer("8452");
        game.setStatus("in progress");
        game=gameDao.add(game);
        
        Round round= new Round();
         round.setGuess("8452");
        round.setResult("e:4:p:0");
      //  Date date= ne
        round.setGuessTime(new Date());
   round.setGameId(1);
        gameDao.saveRound(round,  game.getId());
        
                Round round1= new Round();
         round1.setGuess("8452");
        round1.setResult("e:4:p:0");
      //  Date date= ne
        round1.setGuessTime(new Date());
     round1.setGameId(game.getId());
        gameDao.saveRound(round1, game.getId());
        
        List<Round> rounds= gameDao.getRoundsofGame(game.getId());
        
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        
        
        
        
      
    }
    
  

  
   

    
}
