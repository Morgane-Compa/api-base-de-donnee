package com.simplon.api.model.DTO;

import java.util.ArrayList;
import java.util.List;

import com.simplon.api.model.Contest;
import com.simplon.api.model.Game;

import lombok.Data;

@Data
public class GameDTO {
    private long id;
    private String title;
    private Integer min;
    private Integer max;
    private List<String> contest;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.min = game.getMin();
        this.max = game.getMax();
        List<String> strings = new ArrayList<String>();
        for (Contest contest : game.getContests()) {
            strings.add("Partie n°" + contest.getId() + "du" + contest.getStartDate());
        }

        this.contest = strings;
    }
}