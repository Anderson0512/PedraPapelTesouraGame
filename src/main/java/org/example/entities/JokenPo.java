package org.example.entities;

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
            }
            Integer choiceComputer = choiceComputer();
            System.out.println();
            System.out.println("\n" + user.getName() + ": " + choiceUser + " X " + computer.getName()+ ": " + choiceComputer);

        }
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
}
