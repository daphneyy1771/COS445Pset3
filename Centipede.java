// Centipede.java: interface for Centipede
// COS 445, Spring 2017

public interface Centipede {
    // initialize with other player's history (average game length as Alice/Bob)
    public void init(double a, double b);

    // return true to push, false to terminate game and receive payoff t+3
    public boolean push(int t);

    // play head-to-head: return {p1's payoff, p2's payoff}
    // history: {p1's history as Alice, p1 Bob, p2 Alice, p2 Bob}
    // this is for your convenience (notice that it's static)
    public static int[] test(Centipede p1, Centipede p2, double[] history) {
        final int N_ROUNDS = 100;
        int[] payoffs = {0, 0};

        // initialize players with history
        p1.init(history[2], history[3]);
        p2.init(history[0], history[1]);

        // simulate Centipede game
        for (int t=1; t<=N_ROUNDS; t++) {
            if (t%2 == 1) { // p1's turn
                if (!p1.push(t)) {
                    payoffs[0] = t+3;
                    payoffs[1] = t;
                    return payoffs;
                }
            }
            else { // p2's turn
                if (!p2.push(t)) {
                    payoffs[0] = t;
                    payoffs[1] = t+3;
                    return payoffs;
                }
            }
        }

        throw new RuntimeException("player pushed at final round");
    }
}
