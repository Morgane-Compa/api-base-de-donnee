package com.simplon.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.api.model.Contest;
import com.simplon.api.repository.ContestRepository;

import lombok.Data;

@Data
@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;
    
    //Récupérer tous les jeux
    public Iterable<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    //Récupérer un jeu avec son id
    public Optional<Contest> getContest(final long id) {
        return contestRepository.findById(id);
    }

    // Ajouter/modifier un jeu 
    public Contest saveContest(Contest c) {
        return this.contestRepository.save(c);
    }

    //supprimer un jeu 
    public void deleteContest(final long id) {
        this.contestRepository.deleteById(id);
    }
 
}