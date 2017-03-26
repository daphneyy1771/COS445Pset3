// Prisoner_cxzhang.java: sample implementation for Prisoner
// COS 445, Spring 2017

public class Prisoner_cxzhang implements Prisoner {
    private int nRounds = 0; // how many rounds have elapsed?
    private boolean lastPlay = true; // what did my partner play last?

    // sample implementation of tit-for-tat
    public boolean cooperate() {
        // every 100 rounds, play something random (a bad idea)
        if (nRounds % 100 == 99) {
            return Math.random() < 0.5;
        }

        // otherwise, copy partner's last move
        return lastPlay;
    }

    // sample implementation of callback
    public void receive(boolean action) {
        // increment number of rounds observed
        nRounds++;

        // cache last play
        lastPlay = action;
    }

    // test against itself (optional, for your convenience)
    public static void main(String[] args) {
        Prisoner_cxzhang p1 = new Prisoner_cxzhang();
        Prisoner_cxzhang p2 = new Prisoner_cxzhang();

        // test run using provided utility
        int[] payoffs = Prisoner.test(p1, p2);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
