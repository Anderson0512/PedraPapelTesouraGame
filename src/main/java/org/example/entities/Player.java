package org.example.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private Integer score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    public void incrementScore(){
        setScore(getScore() + 1);
    }
}
