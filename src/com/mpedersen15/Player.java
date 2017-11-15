package com.mpedersen15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by MindRocket Design on 11/15/2017.
 */
public class Player {
    private String name;
    private int score = 0;


    public Player(String name) {
        this.name = name;
    }

    public void takeTurn() {
        int diceAvailable = 6;
        int tempScore = 0;
        do {
            ArrayList<Integer> dice = rollDice(diceAvailable);
            printDiceResults(dice);

            if(areDicePlayable(dice)){
                System.out.println("Dice are playable!");
            }else{
                System.out.println("You lose!");
            }


        }while(!isTurnOver());

    }

    public ArrayList<Integer> rollDice(int numDice) {
        System.out.print("The dice rolled to: ");
        Die die = new Die();
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < numDice; i++){
            int roll = die.roll();
            dice.add(roll);
        }

        return dice;
    }

    public boolean isTurnOver(){
        return true;
    }

    public void printDiceResults(ArrayList<Integer> results){
        System.out.print("Results are: ");
        for (int i = 0 ; i < results.size() ; i++) {
            System.out.print(results.get(i) + " ");
        }
        System.out.println();
    }

    public boolean areDicePlayable(ArrayList<Integer> dice) {
        HashMap<Integer, Integer> diceBreakdown = new HashMap<>();

        for(Integer die : dice){
            if (!diceBreakdown.containsKey(die)){
                diceBreakdown.put(die,1);
            }else{
                int newVal = diceBreakdown.get(die);
                newVal++;
                diceBreakdown.replace(die,newVal);
            }
        }

        /*for (Map.Entry<Integer, Integer> pair : diceBreakdown.entrySet()) {
            System.out.println("Number of " + pair.getKey() + "s: " + pair.getValue());
        }*/

        boolean containsOne = diceBreakdown.containsKey(1);
        boolean containsFive = diceBreakdown.containsKey(5);
        boolean containsTriplet = diceBreakdown.containsValue(3);
        boolean containsQuadruplet = diceBreakdown.containsValue(4);
        boolean containsQuintet = diceBreakdown.containsValue(5);
        boolean containsSextuplet = diceBreakdown.containsValue(6);

        return  containsOne || containsFive || containsTriplet || containsQuadruplet || containsQuintet || containsSextuplet;
    }

}
