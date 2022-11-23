package org.game;


import org.game.entities.JokenPo;
import org.game.model.gameException.GameException;

import static org.game.service.JokenPoService.startGame;

public class Main {
    public static void main(String[] args) {
        try {

            JokenPo jokenPo = startGame();
            jokenPo.toPlay();
        } catch (GameException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Generic Error! Tente Novamente!!");
        }
    }
}