import java.lang.*;
import java.util.*;
import java.io.*;

public class Centipede_kxiao implements Centipede {
	private boolean asAlice = false; // who are we playing as?
	private double alice = -1;
	private double bob = -1;

	public void init(double a, double b) {
		alice = a;
		bob = b;
	}

	// Returns true to push, false to end the game
	public boolean push(int t) {
		if (t == 1) asAlice = true;

		// Don't push in 99th round if playing as Alice
		if (asAlice && t == 99) return false;

		// Don't push in 100th round
		if (t == 100) return false;

		// Goal is to push every round
		return true;

	}

	public static void main(String[] args) {
        Centipede_kxiao p1;
        Centipede_karans p2; 
        int[] payoffs;
        int[] totals = new int[2];
        totals[0] = 0; //kxiao
        totals[1] = 0; //karans

        // test without history
        System.out.println("--- first round, no history");
        p1 = new Centipede_kxiao();
        p2 = new Centipede_karans();
        double[] noHistory = {-1, -1, -1, -1};
        boolean amIAlice = false;

        for (int i = 1; i < 101; i++) {
        	if (amIAlice) {
        		payoffs = Centipede.test(p1, p2, noHistory);
        	}
        	else payoffs = Centipede.test(p2, p1, noHistory);

       	    totals[0] += payoffs[0];
       	    totals[1] += payoffs[1];

       	    if (amIAlice) {
       	    	noHistory[0] += payoffs[0];
       	    	noHistory[3] += payoffs[1];
       	    }
       	    else {
       	    	noHistory[1] += payoffs[0];
       	    	noHistory[2] += payoffs[1];
       	    }

       	    for (int j = 0; j < noHistory.length; j++) {
       	    	if (noHistory[j] != -1) noHistory[j] = (double) noHistory[j]/i;
       	    }

       	    amIAlice = Math.random() < 0.5;
         }
        // payoffs = Centipede.test(p1, p2, noHistory);
         System.out.println("Player 1's payoff: " + totals[0]);
        System.out.println("Player 2's payoff: " + totals[1]);

        // test with history: pretend p1 terminates way earlier than p2
        System.out.println("--- round with fictitious history");
        p1 = new Centipede_kxiao(); // notice that instances get wiped between games
        p2 = new Centipede_karans();
        double[] history = {7.1, 7.2, 53.1, 53.2};
        payoffs = Centipede.test(p1, p2, history);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
	}


}