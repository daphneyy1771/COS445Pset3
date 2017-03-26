// Ultimatum.java: interface for Ultimatum
// COS 445, Spring 2017

public interface Ultimatum {
    // given other player's average proposal, make a proposal
    public int propose(double a);

    // given other player's average proposal, decide wither to accept proposal x
    public boolean accept(double a, int x);

    // test head-to-head, with p1 = Alice, p2 = Bob; return {p1's payoff, p2's payoff}
    // history = {p1's Alice history, p2's Alice history}
    // this is for your convenience (notice that it's static)
    public static int[] test(Ultimatum p1, Ultimatum p2, double[] history) {
        // show p1 p2's history, and solicit a proposal from p1
        int proposal = p1.propose(history[1]);
        if (proposal < 0 || proposal > 100) {
            throw new RuntimeException("illegal proposal");
        }

        // show p2 p1's history and current proposal, and ask whether p2 accepts
        boolean accept = p2.accept(history[0], proposal);

        // compute and return payoffs
        int[] payoffs = {proposal, 100 - proposal};
        if (!accept) payoffs[0] = payoffs[1] = 0;
        return payoffs;
    }
}
