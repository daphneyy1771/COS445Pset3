// Prisoner.java: interface for Iterated Prisoner's Dilemma task
// COS 445, Spring 2017
// correction 3/24/17: swap (1,1) and (2,2) in payoff matrix

public interface Prisoner {
    // return true to cooperate, false to defect
    public boolean cooperate();

    // callback to receive action
    public void receive(boolean action);

    // play head-to-head: return {p1's payoff, p2's payoff}
    // this is for your convenience (notice that it's static)
    public static int[] test(Prisoner p1, Prisoner p2) {
        final int N_ROUNDS = 1000;
        final int[][][] PAYOFFS = {{{1,1},{5,0}},{{0,5},{2,2}}};

        int[] totals = {0, 0};

        for (int i=0; i<N_ROUNDS; i++) {
            // request plays
            boolean p1_play = p1.cooperate(), p2_play = p2.cooperate();

            // get payoffs
            totals[0] += PAYOFFS[p1_play?1:0][p2_play?1:0][0];
            totals[1] += PAYOFFS[p1_play?1:0][p2_play?1:0][1];

            // give feedback
            p1.receive(p2_play); p2.receive(p1_play);
        }

        return totals;
    }
}
