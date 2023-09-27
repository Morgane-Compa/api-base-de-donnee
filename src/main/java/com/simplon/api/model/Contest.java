package com.simplon.api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

// Correspond à une table de la base de donnée

@Data /*Permet de ne pas ajouter les getter et le setters dans la classe */
@Entity /*cette classe à le rôle d'une entité */
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date")
    private Date startDate;

    // Ici le Game fait comprendre que la donnée vient du model Game (car c'est une clé étrangère)
    @ManyToOne // many contest to one game
    private Game game;
    
    @ManyToOne
    private Player winner;
}
