// Ultimatum_smattw.java: sample implementation for Ultimatum
// COS 445, Spring 2017

public class Ultimatum_smattw implements Ultimatum {
 // sample Alice strategy
    public int propose(double a) {
     // first round: propose that I get 60, you get 40
     if (a == -1) {
      return 60;
     }

     // otherwise, propose opponent's history +/- 5
     int p = (int)Math.round(a + 10*Math.random() - 5);
     if (p < 0) p = 0;
     if (p > 100) p = 100;
     return p;
    }

    // sample Bob strategy
    public boolean accept(double a, int x) {
     // first round: accept if I get at least 30
     if (a == -1) {
      return x <= 70;
     }

     // otherwise, accept if proposal is at most $2 worse for me than average
     // (remember that my payoff is 100-x)
     return x <= a + 2;
    }

    public static void main(String[] args) {
        Ultimatum_smattw p1, p2;
        int[] payoffs;

        // test without history
        System.out.println("--- first round, no history");
        p1 = new Ultimatum_smattw();
        p2 = new Ultimatum_smattw();
        double[] noHistory = {-1, -1};
        payoffs = Ultimatum.test(p1, p2, noHistory);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);

        // test with history
        System.out.println("--- round with fictitious history");
        p1 = new Ultimatum_smattw(); // notice that instances get wiped between games
        p2 = new Ultimatum_smattw();
        double[] history = {77.7, 77.7};
        payoffs = Ultimatum.test(p1, p2, history);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);;
    }
}
