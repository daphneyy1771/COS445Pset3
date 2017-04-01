/*****************************************************************************/
/*  Names: Daphne Yang, Kyle Xiao                                            */
/*  Net ID: daphney, kxiao                                                   */
/*  Assignment 3 Problem 2: Centipede                                        */
/*****************************************************************************/
public class Centipede_kxiao implements Centipede {
	private double alice = -1; // Alice's average payoff
	private double bob = -1; // Bob's average payoff

  // initialize centipede
	public void init(double a, double b) {
		alice = a;
		bob = b;
	}

	// Returns true to push, false to end the game
	public boolean push(int t) {
    // Bob's average payoff is higher than expected
    if (t == 97 && bob > 99) return false;

    // Don't push in round 98 as Bob, round 99 as Alice
    if (t > 97) return false;

		// Goal is to push as many rounds as possible
		return true;

	}

  // test
	public static void main(String[] args) {
        Centipede_kxiao p1;
        Centipede_kxiao p2; 
        int[] payoffs;
        int[] totals = new int[2];
        totals[0] = 0; //kxiao
        totals[1] = 0; //karans

        // test without history
        System.out.println("--- first round, no history");
        p1 = new Centipede_kxiao();
        p2 = new Centipede_kxiao();
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
        p1 = new Centipede_kxiao();
        p2 = new Centipede_kxiao();
        double[] history = {98, -1, -1, 101};
        payoffs = Centipede.test(p1, p2, history);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
	}


}