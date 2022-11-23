package org.example;

import org.example.entities.JokenPo;

import static org.example.service.JokenPoService.startGame;

public class Main {
    public static void main(String[] args) {

        JokenPo jokenPo = startGame();
        jokenPo.toPlay();
    }
}