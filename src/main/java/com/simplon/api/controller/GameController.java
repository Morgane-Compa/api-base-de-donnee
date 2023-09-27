package com.simplon.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.api.model.Game;
import com.simplon.api.model.DTO.GameDTO;
import com.simplon.api.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    //Retourne tout les jeux sur le chemin http://localhost:9000/games
    @GetMapping("/games")
    public Iterable<GameDTO> allGames() {
        Iterable<Game> games =  gameService.getAllGames();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();
        for(Game game : games) {
            gamesDTO.add(new GameDTO(game));
        }
        return gamesDTO;
    }

    //Retourne un jeux (si il y en a un) sur le chemin http://localhost:9000/game/(id)
    @GetMapping("/game/{id}")
    public GameDTO game(@PathVariable("id") int id ) {
        // Je récupère le jeu
       Optional<Game> g = gameService.getGame(id); 
       // Game est otionnel donc on doit faire un if pour les différents cas de figure
       if(g.isPresent()) {
        return new GameDTO(g.get());
       } else {
        return null;
       }
    } 
    
    // Ajoute un jeu
    @PostMapping("/game")
    public GameDTO insertGame(@RequestBody Game g) {
        return new GameDTO(gameService.saveGame(g));
    }

    // Supprime un jeu
    @DeleteMapping("/game/{id}")
    public boolean deleteGame(@PathVariable("id") long id) {
        // Je récupère le jeu pour vérifier si il existe
        Optional<Game> g = gameService.getGame(id);
        if(g.isPresent()) {
            gameService.deleteGame(id);
            return true;
        } else {
            return false;
        }
    }


    // Modifie un jeu 
    @PutMapping("/game/{id}") 
    public GameDTO updateGame(@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> g = gameService.getGame(id);
        if(g.isPresent()) {
            Game gameToUpdate = g.get();
            // Modifie le titre
            if(game.getTitle() != null) {
                gameToUpdate.setTitle(game.getTitle());
            }
            // Modifie la valeur max de la base de donnée
            if(game.getMax() != null) {
                gameToUpdate.setMax(game.getMax());
            }
            // Modifie la valeur min de la base de donnée
            if(game.getMin() != null) {
                gameToUpdate.setMin(game.getMin());
            }
            // Une fois tout modifier on save
            return new GameDTO( gameService.saveGame(gameToUpdate));
        }
        return null;
    }
}

