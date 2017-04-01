/*****************************************************************************/
/*  Names: Daphne Yang, Kyle Xiao                                            */
/*  Net ID: daphney, kxiao                                                   */
/*  Assignment 3 Problem 1: Iterated Prisoner's Dilemma                      */
/*****************************************************************************/
import java.util.*;

public class Prisoner_kxiao implements Prisoner {
    private int nRounds = 0; // how many rounds have elapsed?
    private boolean lastPlay = true; // what did my partner play last?
    private boolean lastMyPlay = true; // what did I play last?
    private boolean startedTrig = false; // Have we started alternating?

    // implementation of defecting every other turn
    private boolean ouralgo() {
        // Play tit-for-tat for first 20 rounds in case other player wants to 
        // defect first. The goal is to observe the other player's behavior
        if (nRounds < 20) {
            if (lastPlay != lastMyPlay) startedTrig = true;
            lastMyPlay = lastPlay;
            return lastPlay;
        }
        // This is the last round, so must defect
        if (nRounds == 999) return false;

        // We play tit-for-tat if we have already started alternating
        if (startedTrig) {
            lastMyPlay = lastPlay;
            return lastMyPlay;
        }

         // already alternating
        if (lastPlay != lastMyPlay) {
            if (startedTrig == false) {
                startedTrig = true;
            }
            lastMyPlay = lastPlay;
            return lastMyPlay;
        }

        // Randomly decide to defect
        if (Math.random() < 0.5) lastMyPlay = !lastMyPlay;   

        return lastMyPlay;
    }

    // Returns true if cooperate, false if defect
    public boolean cooperate() {
        return ouralgo();
    }

    // Callback
    public void receive(boolean action) {
        // increment number of rounds elapsed
        nRounds++;
        lastPlay = action;
    
    }

    // test client
    public static void main(String[] args) {
        Prisoner_kxiao p1 = new Prisoner_kxiao();
        Prisoner_kxiao p2 = new Prisoner_kxiao();

        // test run using provided utility
        int[] payoffs = Prisoner.test(p1, p2);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
