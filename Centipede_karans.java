// Centipede_karans.java: sample implementation for Centipede
// COS 445, Spring 2017

public class Centipede_karans implements Centipede {
    // compute average round length of other player
    private double averageLength;

    // return true to cooperate, false to defect
    public void init(double a, double b) {
        averageLength = (a + b) / 2;

        // first round: push until round 30
        if (a == -1) averageLength = 30;
    }

    // strategy: push for as long
    public boolean push(int t) {
        // don't push illegally at final round
        if (t == 100) return false;

        // otherwise, make it last as long
        return t <= averageLength;
    }

    // test against itself (optional, for your convenience)
    public static void main(String[] args) {
        Centipede_karans p1, p2; 
        int[] payoffs;

        // test without history
        System.out.println("--- first round, no history");
        p1 = new Centipede_karans();
        p2 = new Centipede_karans();
        double[] noHistory = {-1, -1, -1, -1};
        payoffs = Centipede.test(p1, p2, noHistory);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);

        // test with history: pretend p1 terminates way earlier than p2
        System.out.println("--- round with fictitious history");
        p1 = new Centipede_karans(); // notice that instances get wiped between games
        p2 = new Centipede_karans();
        double[] history = {7.1, 7.2, 53.1, 53.2};
        payoffs = Centipede.test(p1, p2, history);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
