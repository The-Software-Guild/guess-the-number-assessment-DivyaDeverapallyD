/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.controllers;

import com.aj.cowsandbullsassessment.data.GameDao;
import com.aj.cowsandbullsassessment.models.Game;
import com.aj.cowsandbullsassessment.models.Round;
import com.aj.cowsandbullsassessment.util.ServiceMethods;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DivyaDeverapally
 */
@RestController
//@RequestMapping("/game")
public class GameController {

    private final GameDao dao;
ServiceMethods service= new ServiceMethods();
    public GameController(GameDao dao) {
        this.dao = dao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int start() {
        Game game= new Game();
        game.setAnswer(service.generateNumber());
        return dao.add(game).getId();
    }
    
    @GetMapping
    @RequestMapping("/game")
    public List<Game> allGames() {
        List<Game> allGames= dao.getAll();
        List<Game> filterGames= service.filterGames(allGames);
        return filterGames;
    }
    //Each Round will have a guess, the time of the guess, and the result of the guess in the format "e:0:p:0"
    /*"guess" – POST – Makes a guess by passing the guess and gameId in as JSON.
    The program must calculate the results of the guess and mark the game finished if the guess is correct.
    It returns the Round object with the results filled in.*/
        @PostMapping("/guess/{gameId}")
        public Round guess(@RequestBody Round round, @PathVariable int gameId) {
          //  int gameId= game.getId();
          //  Round round= new Round();
         //   round.setGuess(game.getAnswer());
            round.setGuessTime(new Date());
            
            String answer= dao.getGameById(gameId).getAnswer();
            
         Map<Character,Integer> guessResult= new  HashMap<>();
        guessResult=  service.getResult(round.getGuess(), gameId, answer);
        String guessRes= "e:"+guessResult.get('e')+":"+"p:"+guessResult.get('p');
        round.setResult(guessRes);
          //  round.setResult(res);
            //dao SaveRoundResult
            
           if( guessResult.get('e')==4){
               dao.updateGameAsFinished(gameId);
           }
            //call service with guess, game id, asnwer(fecth this from db)
          
        //    dao.updateGame(gameId);
        
        return dao.saveRound(round, gameId);
    }
          @PostMapping("/guess2/{gameId}")
        public String guess2(@RequestBody Round round, @PathVariable int gameId) {
          //  int gameId= game.getId();
           // Round round= new Round();
           // round.setGuess(game.getAnswer());
           // round.setGuessTime(new Date());
            String res="";
            res= "GameId"+ gameId+"Round"+ round.getGuess();
        //    int answer= dao.getGameById(gameId).getAnswer();
            
           // String res= service.getResult(round.getGuess(), gameId, answer);
            round.setResult(res);
            //dao SaveRoundResult
            return res;
            
            //call service with guess, game id, asnwer(fecth this from db)
            
       // return dao.saveRound(round, gameId);
    }
        //    @RequestMapping("/game")
//"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress 
        //games do not display their answer.
        @GetMapping("/game/{gameId}")
        public Game getGame(@PathVariable int gameId){
            Game game= new Game();
            game= dao.getGameById(gameId);
            if(game.getStatus().equals("in progress"))
                 game.setAnswer("XXXX");
            
            return game;
            
        }
        
        //"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
        @GetMapping("rounds/{gameId}")
        public List<Round> getRoundsOfGame(@PathVariable int gameId){
            return dao.getRoundsofGame(gameId);
        }
        

}
