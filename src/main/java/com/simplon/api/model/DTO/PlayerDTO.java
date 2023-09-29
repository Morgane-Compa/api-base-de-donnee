package com.simplon.api.model.DTO;

import java.util.ArrayList;
import java.util.List;

import com.simplon.api.model.Contest;
import com.simplon.api.model.Player;

import lombok.Data;

@Data
public class PlayerDTO {
    private long id;
    private String email;
    private String nickname;
    private List<String> wins;
    private Integer contests;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.email = player.getEmail();
        this.nickname = player.getNickname();
        this.wins = new ArrayList<String>();

        if(player.getWins() != null) {
            for (Contest contest : player.getWins()) {
                this.wins.add(
                    "Partie n°" + contest.getId() + "de" + contest.getGame().getTitle() + "du" + contest.getStartDate()
                );
            }
        }

        if( player.getContests() !=null) {
            this.contests = player.getContests().size();
        } else {
            this.contests = 0;
        }
        
    }

}