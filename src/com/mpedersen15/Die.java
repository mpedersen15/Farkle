package com.mpedersen15;

/**
 * Created by MindRocket Design on 11/15/2017.
 */
public class Die {

    public int roll(){
        return (int)(Math.random() * 5) + 1;
    }
}
