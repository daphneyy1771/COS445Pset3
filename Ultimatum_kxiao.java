
public class Ultimatum_kxiao implements Ultimatum {
    // Alice strategy
    public int propose(double a) {
        // Propose 99 always
        return 99;
    }

    // Bob strategy
    public boolean accept(double a, int x) {
         // if Alice proposes I get nothing, then don't accept
        return (x < 100);
    }

    public static void main(String[] args) {
        Ultimatum_kxiao p1, p2;
        int[] payoffs;

        // test without history
        System.out.println("--- first round, no history");
        p1 = new Ultimatum_kxiao();
        p2 = new Ultimatum_kxiao();
        double[] noHistory = {-1, -1};
        payoffs = Ultimatum.test(p1, p2, noHistory);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);

        // test with history
        System.out.println("--- round with fictitious history");
        p1 = new Ultimatum_kxiao(); // notice that instances get wiped between games
        p2 = new Ultimatum_kxiao();
        double[] history = {77.7, 77.7};
        payoffs = Ultimatum.test(p1, p2, history);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);;
    }
}
