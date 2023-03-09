
package com.example.player.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.player.repository.PlayerRepository;
import com.example.player.model.PlayerRowMapper;
import com.example.player.model.Player;

// Write your code here
@Service
public class PlayerH2Service implements PlayerRepository{
    @Autowired 
    private  JdbcTemplate db;
    //get books from database
    public ArrayList<Player> getPlayers(){
       List<Player> list= db.query("SELECT * FROM TEAM",new PlayerRowMapper());
       ArrayList<Player> players=new ArrayList<>(list);
       return players;
    }
    //get by player using player id 
    public Player getPlayerById(int playerId)
    {
        try{
        Player player=db.queryForObject("SELECT * FROM TEAM WHERE playerId=?",new PlayerRowMapper(),playerId);
        return player;
        }
        catch(Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    // add a boot to Team 
    public Player addPlayer(Player player)
    {
        db.update("INSERT INTO TEAM(playerName,jerseyNumber,role) values (?,?,?)", player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        Player p= db.queryForObject("select * from team where playerName=? and jerseyNumber=? and role=?",new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        return p;
    }
    //update the player 
   public  Player updatePlayer(int playerId,Player player){
        if(player.getPlayerName()!=null)
        {
            db.update("Update team set playerName=? where playerId=?",player.getPlayerName(),playerId);
        }
        if(player.getJerseyNumber()!=0)
        {
            db.update("Update team set jerseyNumber=? where playerId=?",player.getJerseyNumber(),playerId);
        }
        if(player.getRole()!=null)
        {
            db.update("Update team set role=? where playerId=?",player.getRole(),playerId);
        }
        return getPlayerById(playerId);

    }
    //delete the player
    public void deletePlayer(int playerId)
    {
        db.update("delete from team where playerId=?",playerId);
    }
       

}