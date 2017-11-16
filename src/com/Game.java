package com;

import com.mpedersen15.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Matt on 11/15/2017.
 */
public class Game {
    ArrayList<Player> playerList = new ArrayList<>();

    public Game() {
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many players will be playing? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // clear the System.in buffer

        for (int i = 0 ; i < numPlayers ; i++) {
            System.out.print("What is the name of Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            this.playerList.add(new Player(name));
        }

        printPlayerList();
    }

    private void printPlayerList(){
        System.out.println("The players are:");
        for (Player player : playerList){
            System.out.println(player.getName());
        }
    }
}
