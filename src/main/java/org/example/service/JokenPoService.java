package org.example.service;

import org.example.entities.JokenPo;
import org.example.entities.Player;

import java.util.Locale;
import java.util.Scanner;

public class JokenPoService {

    public JokenPoService() {
    }

    public static JokenPo startGame() {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("************    Jo+Ken+Po    *************\n");

        System.out.print("Informe seu nome: ");
        String name = sc.nextLine().toUpperCase();
        Player user = new Player(name);
        System.out.print("Qual nome você quer me dar? ");
        String nameComputer = sc.nextLine().toUpperCase();
        Player computer = new Player(nameComputer);

        System.out.print(name + ", informe quantas partidas você deseja jogar: ");
        Integer rounds = sc.nextInt();

        return new JokenPo(user, computer, rounds);
    }
}
