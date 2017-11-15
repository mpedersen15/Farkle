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
                System.out.println("Dice score: " + getDiceScore(dice));
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

    private void printDiceResults(ArrayList<Integer> results){
        System.out.print("Results are: ");
        for (int i = 0 ; i < results.size() ; i++) {
            System.out.print(results.get(i) + " ");
        }
        System.out.println();
    }

    private HashMap<Integer, Integer> getDiceBreakdown(ArrayList<Integer> dice){
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

        return diceBreakdown;
    }

    private boolean areDicePlayable(ArrayList<Integer> dice) {
        HashMap<Integer,Integer> diceBreakdown = getDiceBreakdown(dice);

        return  diceBreakdown.containsKey(1) || diceBreakdown.containsKey(5) || diceContainsSet(diceBreakdown);
    }

    private boolean diceContainsSet(HashMap<Integer, Integer> diceBreakdown){
        boolean containsTriplet = diceBreakdown.containsValue(3);
        boolean containsQuadruplet = diceBreakdown.containsValue(4);
        boolean containsQuintet = diceBreakdown.containsValue(5);
        boolean containsSextuplet = diceBreakdown.containsValue(6);

        return containsTriplet || containsQuadruplet || containsQuintet || containsSextuplet;
    }

    private int getDiceScore(ArrayList<Integer> dice) {
        int tempScore = 0;
        int fiveSingleScore = 50;
        int oneSingleScore = 100;
        int oneTripleScore = 1000;

        HashMap<Integer,Integer> diceBreakdown = getDiceBreakdown(dice);

        for (Map.Entry<Integer, Integer> pair : diceBreakdown.entrySet()) {
            if (pair.getKey() == 1){
                if (pair.getValue() >= 3){
                    tempScore += oneTripleScore * (int) Math.pow(2, pair.getValue() - 3);
                }else {
                    tempScore += oneSingleScore * pair.getValue();
                }
            }else if (pair.getKey() == 5) {
                if (pair.getValue() >= 3){
                    tempScore += pair.getKey()*100 * (int) Math.pow(2, pair.getValue() - 3);
                }else {
                    tempScore += fiveSingleScore * pair.getValue();
                }
            }else {
                if (pair.getValue() >= 3){
                    tempScore += pair.getKey()*100 * (int) Math.pow(2, pair.getValue() - 3);
                }
            }
        }
        return tempScore;
    }

}
