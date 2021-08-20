/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.util;

import com.aj.cowsandbullsassessment.models.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author DivyaDeverapally
 */
public class ServiceMethods {
    
    public String generateNumber(){
        int res=0;
         List<Integer> numbers = new ArrayList<>();
    for(int i = 1; i < 10; i++){
        numbers.add(i);
    }

    Collections.shuffle(numbers);

    String result = "";
    for(int i = 0; i < 4; i++){
        result += numbers.get(i).toString();
    }
 //   System.out.println(result);
    res= Integer.parseInt(result);
    return result;
    }
       /*"guess" – POST – Makes a guess by passing the guess and gameId in as JSON.
    The program must calculate the results of the guess and mark the game finished if the guess is correct.
    It returns the Round object with the results filled in.*/
    public Map<Character, Integer> getResult(String g, int gameId, String a){
        Map<Character, Integer> resultMap= new HashMap<>();
          String res="";
        //e:0:p:0"
        int e=0;
        int p=0;
     //   String g= String.valueOf(guess);
      //  String a= String.valueOf(answer);
        Map<Character, Integer> guessMap= new HashMap<>();
        Map<Character, Integer> answerMap= new HashMap<>();
        
        
        for(int i =0; i <g.length(); i++ ){
            guessMap.put(g.charAt(i),i);
            answerMap.put(a.charAt(i), i);
            
        }
        
      for(Map.Entry<Character,Integer> entry: guessMap.entrySet()){
          
          if(answerMap.containsKey(entry.getKey())){
              
              if((entry.getValue()== answerMap.get(entry.getKey()))){
                  e=e+1;
              }
              else{
                  p=p+1;
              }
          }
          
      }
        
      resultMap.put('e',e);
      resultMap.put('p',p);
      res="e:"+e+":"+"p:"+p;
        
        return resultMap;
    }

    public List<Game> filterGames(List<Game> allGames) {
        
        for(Game game: allGames){
            if(game.getStatus().equals("in progress"))
            {
                game.setAnswer("XXXX");
            }
        }
        return allGames;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
