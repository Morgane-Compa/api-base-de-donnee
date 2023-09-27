package com.simplon.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.api.model.Player;
import com.simplon.api.service.PlayerService;

@RestController
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    //Retourne tout les jeux sur le chemin http://localhost:9000/players
    @GetMapping("/players")
    public Iterable<Player> allPlayers() {
        return playerService.getAllPlayers();
    }

     //Retourne un jeux (si il y en a un) sur le chemin http://localhost:9000/player/(id)
    @GetMapping("/player/{id}")
    public Player player(@PathVariable("id") int id) {
        // Je récupère mon joueur
        Optional<Player> p = playerService.getPlayer(id);
        if(p.isPresent()) {
            return p.get();
        } else {
            return null;
        }
     }

    //  Ajoute un joueur
    @PostMapping("/player")
    public Player insertPlayer(@RequestBody Player p) {
        return playerService.savePlayer(p);
    }

    // Supprime un joueur 
    @DeleteMapping("/player/{id}")
    public boolean deletePlayer(@PathVariable("id") long id) {
        // Je récupère le joueur pour vérifier si il existe
        Optional<Player> p = playerService.getPlayer(id);
        if(p.isPresent()) {
            playerService.deletePlayer(id);
            return true;
        } else {
            return false;
        }
    }

    // Modifie un joueur 
    @PutMapping("/player/{id}")
    public Player updatPlayer(@PathVariable("id") long id, @RequestBody Player player) {
        Optional<Player> p = playerService.getPlayer(id); 
        if(p.isPresent()) {
            Player playerToUpdate = p.get();
            // Modifie l'email
            if(player.getEmail() !=null) {
                playerToUpdate.setEmail(player.getEmail());
            }
            // Modifie le nickname
            if(player.getNickname() !=null) {
                playerToUpdate.setNickname(player.getNickname());
            }
            return playerService.savePlayer(playerToUpdate);
        }
        return null;
    }

}