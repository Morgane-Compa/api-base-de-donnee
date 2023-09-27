package com.simplon.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.api.model.Game;
import com.simplon.api.repository.GameRepository;

import lombok.Data;

@Data
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // public GameService(GameRepository gameRepository) {
    //     this.gameRepository = gameRepository;
    // }
    
    //Récupérer tous les jeux
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    //Récupérer un jeu avec son id
    public Optional<Game> getGame(final long id) {
        return gameRepository.findById(id);
    }

    // Ajouter/modifier un jeu 
    public Game saveGame(Game g) {
        return this.gameRepository.save(g);
    }

    //supprimer un jeu 
    public void deleteGame(final long id) {
        this.gameRepository.deleteById(id);
    }
 
}

