/*
 * 
 * You can use the following import statemets
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */
 package com.example.player.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList; 
import com.example.player.service.PlayerH2Service;
import com.example.player.model.Player;
@RestController 
public class PlayerController{
    @Autowired
	public PlayerH2Service playerService;
       @GetMapping("/players") 
      // get all team players
       public ArrayList<Player> getPlayers()
       {
        return playerService.getPlayers();
       }
        
        //get player by id
       @GetMapping("/players/{playerId}")
            public Player getPlayerById(@PathVariable("playerId") int playerId) {
                    return playerService.getPlayerById(playerId);
        }
      
      //add player to team
       @PostMapping("/players")
       public Player addPlayer(@RequestBody Player player){
              return playerService.addPlayer(player);
       }
       
       //update the player 
        @PutMapping("/players/{playerId}")
        public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player) {
            return playerService.updatePlayer(playerId, player);
        }
       //delete the player
        @DeleteMapping("/players/{playerId}")
            public void deleteBook(@PathVariable("playerId") int playerId){
                playerService.deletePlayer(playerId);
            }  
            
}
