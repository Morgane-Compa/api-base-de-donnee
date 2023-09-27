package com.simplon.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Player {
    @Id /*clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Auto incrémentation */
    private long id;
    private String email;

    // @Column(length = 30)
    private String nickname;

    @OneToMany(mappedBy = "winner")
    private List<Contest> wins;
}