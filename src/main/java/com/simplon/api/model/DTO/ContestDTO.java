package com.simplon.api.model.DTO;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.simplon.api.model.Contest;

import lombok.Data;

@Data
public class ContestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startDate;
    private String game;
    private String winner;

    public ContestDTO(Contest contest) {
        this.id = contest.getId();
        this.startDate = contest.getStartDate();
        this.game = contest.getGame().getId() + "-" + contest.getGame().getTitle();
        // if(contest.getWinner() != null) {
        //     this.winner = contest.getWinner().getNickname();
        // } else {
        //     this.winner = "pas de vainqueur";
        // }
        this.winner = contest.getWinner() != null ? contest.getWinner().getNickname() : "pas de vainqueurs";
    }
}