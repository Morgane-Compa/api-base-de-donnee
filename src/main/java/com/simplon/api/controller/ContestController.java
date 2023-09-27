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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.api.model.Contest;
import com.simplon.api.model.DTO.ContestDTO;
import com.simplon.api.service.ContestService;
import com.simplon.api.service.GameService;
import com.simplon.api.service.PlayerService;

@RestController
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private GameService GameService;

    @Autowired
    private PlayerService playerService;


    //Retourne tout les jeux sur le chemin http://localhost:9000/contests
    @GetMapping("/contests")
    public Iterable<ContestDTO> allContests() {
        Iterable<Contest> contests =  contestService.getAllContests();
        List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
        for(Contest contest : contests) {
            contestsDTO.add(new ContestDTO(contest));
        }
        return contestsDTO;
    }


    //Retourne un jeux (si il y en a un) sur le chemin http://localhost:9000/contest/(id)
    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable("id") int id ) {
        // Je récupère le jeu
       Optional<Contest> g = contestService.getContest(id); 
       // Contest est otionnel donc on doit faire un if pour les différents cas de figure
       if(g.isPresent()) {
        return new ContestDTO(g.get());
       } else {
        return null;
       }
    } 

    
    // Ajoute un jeu
    @PostMapping("/contest")
    public ContestDTO insertContest(@RequestParam String start_date, @RequestParam int game_id, @RequestParam Integer winner_id) {
        Contest contest = new Contest();
        // Value of est une fonction statique de la classe Date
        contest.setStartDate(java.sql.Date.valueOf(start_date));
        contest.setGame(GameService.getGame(game_id).get());
        if(winner_id != null) {
            contest.setWinner(playerService.getPlayer(winner_id).get());
        }

        return new ContestDTO(contestService.saveContest(contest));
    }


    // Supprime un jeu
    @DeleteMapping("/contest/{id}")
    public boolean deleteContest(@PathVariable("id") long id) {
        // Je récupère le jeu pour vérifier si il existe
        Optional<Contest> g = contestService.getContest(id);
        if(g.isPresent()) {
            contestService.deleteContest(id);
            return true;
        } else {
            return false;
        }
    }


    // Modifie un jeu 
    @PutMapping("/contest/{id}")
    public Contest updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
        Optional<Contest> g = contestService.getContest(id);
        if(g.isPresent()) {
            contest.setId(id);
            
            return contestService.saveContest(contest);
        }
        return null;
    }
}