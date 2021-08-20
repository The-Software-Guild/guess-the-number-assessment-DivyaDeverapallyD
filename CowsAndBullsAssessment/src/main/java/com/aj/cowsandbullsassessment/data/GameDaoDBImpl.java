/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.cowsandbullsassessment.data;

import com.aj.cowsandbullsassessment.models.Game;
import com.aj.cowsandbullsassessment.models.Round;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DivyaDeverapally
 */
@Repository
public class GameDaoDBImpl implements GameDao {

     private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, game.getAnswer());
            //  statement.setString(2, todo.getNote());
            return statement;
            
        }, keyHolder);
        game.setId(keyHolder.getKey().intValue());
        return game;
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
        public List<Game> getAll() {
        final String sql = "SELECT id, answer, status from game;";
        return jdbcTemplate.query(sql, new GameMapper());
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Game getGameById(int id) {
        final String sql = "SELECT id, answer,status FROM game WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        
    }
    // pemdng - if e=4 , then we need to update game table tooo in below code
    
    @Override
    public Round saveRound(Round round, int gameId) {
              final String sql = "INSERT INTO round(guess, result,id, guessTime) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
           jdbcTemplate.update(new PreparedStatementCreator() {
                  @Override
                  public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                      PreparedStatement statement = conn.prepareStatement(
                              sql,
                              Statement.RETURN_GENERATED_KEYS);
                     // java.sql.Date sqlDate = new java.sql.Date(round.getGuessTime());
                     
                      statement.setString(1, round.getGuess());
                      statement.setString(2, round.getResult());
                      statement.setInt(3, gameId);
                      java.sql.Date sqlDate = new java.sql.Date(round.getGuessTime().getTime());
                      statement.setDate(4,sqlDate);
                      //  statement.setString(2, todo.getNote());
                      return statement;
                  }
              }, keyHolder);
        round.setRoundId(keyHolder.getKey().intValue());
        round.setGameId(gameId);
        return round;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGameAsFinished(int gameId) {
         final String sql = "UPDATE game SET  status= ? where id= ?";
            

         jdbcTemplate.update(sql,"Finished", gameId);
              
        
        
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRoundsofGame(int gameId) {
        final String sql = "SELECT roundId, guess,result,guessTime,id from round where id = ? ";
        return jdbcTemplate.query(sql, new RoundMapper(),gameId);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getAllRounds() {
          final String sql = "SELECT roundId, guess, result,guessTime,id from round;";
        return jdbcTemplate.query(sql, new RoundMapper());
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deletGameById(int gameId) {
         final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
          final String DELETE_Round = "DELETE FROM round WHERE id = ?";
            jdbcTemplate.update(DELETE_Round, gameId);
        jdbcTemplate.update(DELETE_GAME, gameId);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteRoundById(int roundId) {
        
          final String DELETE_ROUND = "DELETE FROM round WHERE id = ?";
        jdbcTemplate.update(DELETE_ROUND, roundId);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round getRoundById(int roundId) {
         final String sql = "SELECT roundId, guess,result,guessTime,id FROM round WHERE roundId = ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), roundId);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getString("status"));
            //    td.setFinished(rs.getBoolean("finished"));
            return game;
        }
        
    }

    private static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            Round round = new Round();
            round.setRoundId(rs.getInt("roundid"));
            round.setGuess(rs.getString("guess"));
            //    LocalDate date=rs.getDate("guessTime").toLocalDate();
            
            round.setGuessTime(new Date(rs.getDate("guessTime").getTime()));
            round.setResult(rs.getString("result"));
            round.setGameId(rs.getInt("id"));
            //game.setId(rs.getInt("id"));
            //  game.setAnswer(rs.getInt("answer"));
            //  game.setStatus(rs.getString("status"));
            //    td.setFinished(rs.getBoolean("finished"));
            return round;
        }
    }
}
