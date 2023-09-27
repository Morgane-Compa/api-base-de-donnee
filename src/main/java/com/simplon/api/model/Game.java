package com.simplon.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

// Correspond à une table de la base de donnée

@Data /*Permet de ne pas ajouter les getter et le setters dans la classe */
@Entity /*cette classe à le rôle d'une entité */
public class Game {
    @Id /*clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Auto incrémentation */
    private long id;
    private String title;
    private Integer min;
    private Integer max;

    @OneToMany(mappedBy = "game")
    private List<Contest> contests;
}




