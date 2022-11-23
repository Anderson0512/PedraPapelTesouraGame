package org.example.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public record JokenPo(
        Player user,
        Player computer,
        Integer rounds
) {
    static Scanner sc = new Scanner(System.in);

    public void toPlay() {

        System.out.println("\n**************  SEJA BEM VINDO(A), " + user.getName() + " ****************\n");

        for (int i = 0; i < rounds; i++) {

            Integer choiceUser = choiceUser();
            System.out.println();
            if (choiceUser < 1 || choiceUser > 3) {
                System.out.println("\nJOGADA INVÁLIDA! (1, 2 ou 3): ");
                System.out.println("\tPONTO PARA " + computer.getName() + "\n");
                computer.incrementScore();
                continue;
            }
            Integer choiceComputer = choiceComputer();
            System.out.println();
            System.out.println(user.getName() + ": Escolheu " + choiceUser + " ((" + choiceType(choiceUser) + ")) X " + computer.getName() + ": Escolheu " + choiceComputer + " ((" + choiceType(choiceComputer) + "))\n");
            descriptionReason(choiceUser, choiceComputer);
            Integer result = choiceUser - choiceComputer;
            winnerRound(result, (i + 1));

        }
        showFinalResult();
    }

    public void showFinalResult() {
        System.out.println("\n*************************************************\n");

        Integer finalScoreUser = user.getScore();
        Integer finalScoreComputer = computer.getScore();

        System.out.println("\n\tPLACAR FINAL: " + user.getName() + " " + finalScoreUser + " X " + finalScoreComputer + " " + computer.getName());

        if (finalScoreUser == finalScoreComputer) {
            System.out.println("\t\t\tEMPATE!");
        } else {
            String finalWinner = (finalScoreUser > finalScoreComputer) ? user.getName() : computer.getName();
            System.out.println("\t\tVENCEDOR = *** " + finalWinner + " ***");
        }
        System.out.println("\n*************************************************\n");
    }

    private void winnerRound(Integer result, Integer round) {
        String winnerRound;
        if (result == 0) {
            winnerRound = "EMPATE!";
        } else {
            if (result == -1 || result == 2) {
                computer().incrementScore();
                winnerRound = computer.getName();
            } else {
                user.incrementScore();
                winnerRound = user.getName();
            }
        }
        System.out.println("\nVencedor da Rodada (" + round + "): " + winnerRound + "\n");
    }

    private Integer choiceComputer() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    private Integer choiceUser() {
        System.out.println("1 - PEDRA ");
        System.out.println("2 - PAPEL ");
        System.out.println("3 - TESOURA ");

        System.out.print("Informe sua jogada: ");

        return sc.nextInt();
    }

    private String choiceType(Integer choice) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "PEDRA");
        map.put(2, "PAPEL");
        map.put(3, "TESOURA");
        return map.get(choice);
    }

    private void descriptionReason(Integer one, Integer two) {
        if (1 == one && 2 == two || 2 == one && 1 == two) {
            System.out.println("*** Papel envolve Pedra ***");
        } else if (1 == one && 3 == two || 3 == one && 1 == two) {
            System.out.println("*** Pedra amassa Tesoura ***");
        } else if (one == two) {
            System.out.println("\t\t*Rodada Neutra*");
        } else {
            System.out.println("*** Tesoura corta Papel ***");
        }
    }
}
