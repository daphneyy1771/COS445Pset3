// Prisoner_kxiao.java: sample implementation for Prisoner
// COS 445, Spring 2017
import java.util.*;

public class Prisoner_kxiao implements Prisoner {
    private int nRounds = 0; // how many rounds have elapsed?
    private boolean lastPlay = true; // what did my partner play last?
    //private boolean lastLastPlay = true; // what did my partner play 2 rounds ago?
   // private boolean lastLastLastPlay = true; // what did my partner play 3 rounds ago?
    private int defects = 0; // how many times has my partner defected
    private boolean lastMyPlay = true;

    private boolean isCoop = true;

    private int grimtrig = 0;
    private boolean startedTrig = false;

    // implementation of defecting every other turn
    private boolean ouralgo() {
        // Play tit-for-tat for first 20 rounds in case other player wants to defect first
        // The goal is to observe the other player's behavior
        if (nRounds < 20) {
            lastMyPlay = lastPlay;
            return lastPlay;
        }

        // Give our opponents 5 times to fix their algorithm in case alternate together
        if (grimtrig > 4) return false;

         // already alternating
        if (lastPlay != lastMyPlay) {
            if (startedTrig == false) {
                startedTrig = true;
            }
            lastMyPlay = lastPlay;
            return lastMyPlay;
        }

        // For some reason opponent decided not to play with our strategy, but we are forgiving,
        // so we play tit-for-tat to let them fix their algorithm
        if (startedTrig) {
            // Supposed to play cooperate but played defect instead
            if (!lastPlay && !lastMyPlay) {
                grimtrig++;
            }
            lastMyPlay = lastPlay;
            return lastMyPlay;
        }

        // Randomly decide to defect if playing against tit-for-tat
        if (Math.random() < 0.75) {
            if (isCoop == true) isCoop = false;
            else isCoop = true;
        }

        lastMyPlay = isCoop;
        return isCoop;
    }

    // sample implementation of tit-for-tat
    public boolean cooperate() {
        return ouralgo();
    }

    // sample implementation of callback
    public void receive(boolean action) {
        // increment number of rounds observed
        nRounds++;

        // cache last play
       // lastLastLastPlay = lastLastPlay;
       // lastLastPlay = lastPlay;
        lastPlay = action;
        
        // if defect, increment counter
        if (!action) { defects++; }


    }

    // test against itself (optional, for your convenience)
    public static void main(String[] args) {
        Prisoner_kxiao p1 = new Prisoner_kxiao();
        Prisoner_cxzhang p2 = new Prisoner_cxzhang();

        // test run using provided utility
        int[] payoffs = Prisoner.test(p1, p2);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
