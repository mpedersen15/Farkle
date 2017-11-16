package com.mpedersen15;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by MindRocket Design on 11/15/2017.
 */
public class Player {
    private String name;
    private int score = 0;
    private boolean hasPostedScore = false;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void takeTurn() {
        int diceAvailable = 6;
        int tempScore = 0;

        boolean isTurnOver = false;
        do {
            System.out.println("=======================================");
            System.out.println("Rolling " + diceAvailable + " die/dice");
            ArrayList<Integer> dice = rollDice(diceAvailable);
            for (int i = 0 ; i < dice.size() ; i++) {
                System.out.println("DIE #" + (i+1));
                printDie(dice.get(i));
            }

            if(areDicePlayable(dice)){
                System.out.println("Dice are playable!");
                System.out.println("Dice score: " + getDiceScore(dice));
                ArrayList<Integer> diceNumbers = chooseDiceToKeep();
                if (diceNumbers.contains(-1)){
                    tempScore += getDiceScore(dice);
                    System.out.println("Staying with a score of: " + tempScore);
                    isTurnOver = true;
                }else{
                    ArrayList<Integer> chosenDice = new ArrayList<>();
                    for(int index : diceNumbers) {
                        chosenDice.add(dice.get(index - 1));
                    }
                    tempScore += getDiceScore(chosenDice);

                    System.out.println("Your current round score is: " + tempScore);

                    diceAvailable = diceAvailable - chosenDice.size();

                    if (diceAvailable == 0) {
                        System.out.println("All dice play! Roll all 6 dice again!");
                        diceAvailable = 6;
                    }
                }

            }else{
                System.out.println("Nothing playable!");
                isTurnOver = true;
            }


        }while(!isTurnOver);

        this.score += tempScore;

        System.out.println("Your total score is: " + this.score);
    }

    private ArrayList<Integer> chooseDiceToKeep(){
        // TODO: validate choiceList before returning (i.e. don't allow out of bounds, don't allow illegal characters, don't allow dice that have zero points)
        // TODO: modify choice statement and don't allow "staying" if Player hasn't yet posted a score
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the numbers of the dice you'd like to keep, or enter -1 to stay: ");
        String keepString = scanner.nextLine();
        System.out.println("You've chosen to keep: " + keepString);

        ArrayList<String> list = new ArrayList<>(Arrays.asList(keepString.split(",")));
        ArrayList<Integer> choiceList = new ArrayList<>();
        for (String choice : list) {
            System.out.println(choice);
            choiceList.add(Integer.parseInt(choice.trim()));
        }
        return choiceList;
    }

    public ArrayList<Integer> rollDice(int numDice) {
        System.out.println("The dice rolled to: ");
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

    public void printDie(int number){

        switch(number){
            case 1:
                System.out.println("-----------");
                System.out.println("|         |");
                System.out.println("|    *    |");
                System.out.println("|         |");
                System.out.println("-----------");
                break;
            case 2:
                System.out.println("-----------");
                System.out.println("|      *  |");
                System.out.println("|         |");
                System.out.println("|  *      |");
                System.out.println("-----------");
                break;
            case 3:
                System.out.println("-----------");
                System.out.println("|      *  |");
                System.out.println("|    *    |");
                System.out.println("|  *      |");
                System.out.println("-----------");
                break;
            case 4:
                System.out.println("-----------");
                System.out.println("|  *   *  |");
                System.out.println("|         |");
                System.out.println("|  *   *  |");
                System.out.println("-----------");
                break;
            case 5:
                System.out.println("-----------");
                System.out.println("|  *   *  |");
                System.out.println("|    *    |");
                System.out.println("|  *   *  |");
                System.out.println("-----------");
                break;
            default:
                System.out.println("-----------");
                System.out.println("|  *   *  |");
                System.out.println("|  *   *  |");
                System.out.println("|  *   *  |");
                System.out.println("-----------");
        }
    }

}
