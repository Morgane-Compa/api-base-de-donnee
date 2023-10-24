package com.simplon.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.EntityManager;

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

    @Autowired
    private EntityManager entityManager;

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

    // SELECT FROM game g WHERE title LIKE "%a%"
    public List<Game> searchTitle(String word) {
        String sql = "SELECT new" + Game.class.getName() + "(g.id, g.title, g.min, g.max)" 
                    + " FROM " + Game.class.getName() + "g"
                    + "WHERE g.title LIKE '%" + word + "%'";
                    Query query = entityManager.createQuery(sql, Game.class);
                    List<Game> liste = new ArrayList<Game>();
                    liste = query.getResultList();
                    return liste;
    }
 
}

