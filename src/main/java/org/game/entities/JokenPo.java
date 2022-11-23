package org.game.entities;

import org.game.model.gameException.GameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static org.game.service.JokenPoService.startGame;

public record JokenPo(
        Player user,
        Player computer,
        Integer rounds
) {
    static Scanner sc = new Scanner(System.in);
    static String go = "n";

    public void toPlay() {

        System.out.println("\n**************  SEJA BEM VINDO(A), " + user.getName() + " ****************\n");

        try {

            for (int i = 0; i < rounds; i++) {

                Integer choiceUser = choiceUser();
                System.out.println();
                if (validateInputUser(choiceUser)) continue;
                Integer choiceComputer = choiceComputer();
                System.out.println();
                System.out.println(user.getName() + ": Escolheu " + choiceUser + " ((" + choiceType(choiceUser) + ")) X " + computer.getName() + ": Escolheu " + choiceComputer + " ((" + choiceType(choiceComputer) + "))\n");
                descriptionReason(choiceUser, choiceComputer);
                Integer result = choiceUser - choiceComputer;
                winnerRound(result, (i + 1));

            }

            showFinalResult();
            sc.nextLine();
            System.out.print("Deseja continuar? (s/n) ");
            go = sc.next();

            validateContinue();

            while ("s".equalsIgnoreCase(go)) {
                System.out.println("\nDeseja continuar com a mesma pontuação ou reiniciar o Game?");
                System.out.print("'c' - Continuar ou 'r' - Reiniciar: ");
                String confirm = sc.next();
                validateConfirmContinue(confirm);
                break;
            }
        } catch (GameException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Generic Error of input!");
        }
    }

    private void validateConfirmContinue(String confirm) {
        if ("c".equalsIgnoreCase(confirm)) {
            System.out.print("\n" + user.getName() + ", informe quantas partidas você deseja jogar desta vez: ");
            Integer rounds = sc.nextInt();
            new JokenPo(user, computer, rounds).toPlay();
        } else if ("r".equalsIgnoreCase(confirm)) {
            startGame().toPlay();
        } else {
            System.out.println("\n\t\t*** Finalizando a sessão ***");
            throw new GameException("\n\t\tOpção de escolha inválida, tente novamente!\n\n");
        }
    }

    private boolean validateInputUser(Integer choiceUser) {
        if (choiceUser < 1 || choiceUser > 3) {
            System.out.println("\nJOGADA INVÁLIDA! (1, 2 ou 3): ");
            System.out.println("\tPONTO PARA " + computer.getName() + "\n");
            computer.incrementScore();
            return true;
        }
        return false;
    }

    private static void validateContinue() {
        if (!"s".equalsIgnoreCase(go) && !"n".equalsIgnoreCase(go)) {
            System.out.println("\n\t\t*** Finalizando a sessão ***");
            throw new GameException("\n\t\tOpção de escolha inválida, tente novamente!\n\n");
        }
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
            System.out.println("\n\t\tVENCEDOR = *** " + finalWinner + " ***");
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

        String choiceUser = sc.next();
        validateInputUser(choiceUser);
        return Integer.valueOf(choiceUser);
    }

    private static void validateInputUser(String choiceUser) {
        if (!choiceUser.matches("[0-9]+")) {
            throw new GameException("Escolha inválida: Use apenas números!");
        }
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
