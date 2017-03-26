// Prisoner_kxiao.java: sample implementation for Prisoner
// COS 445, Spring 2017

public class Prisoner_kxiao implements Prisoner {
    private int nRounds = 0; // how many rounds have elapsed?
    private boolean lastPlay = true; // what did my partner play last?
    private boolean lastLastPlay = true; // what did my partner play 2 rounds ago?
    private boolean lastLastLastPlay = true; // what did my partner play 3 rounds ago?
    private int defects = 0; // how many times has my partner defected

    // sample implementation of tit-for-tat
    public boolean cooperate() {
        // if partner defected three times in a row, then defect
        if (!lastPlay && !lastLastPlay && !lastLastLastPlay) {
            return false;
        }
        else {
            // after round 800, return defect randomly
            // with quadratically increaasing probability 
            if (nRounds > 799) {
                double cutoff = ((nRounds-799) * (nRounds-799)) / (200.0 * 200.0) * 0.8;
                return Math.random() > cutoff;
            }
            else
                return true;
        }
    }

    // sample implementation of callback
    public void receive(boolean action) {
        // increment number of rounds observed
        nRounds++;

        // cache last play
        lastLastLastPlay = lastLastPlay;
        lastLastPlay = lastPlay;
        lastPlay = action;
        
        // if defect, increment counter
        if (!action) { defects++; }
    }

    // test against itself (optional, for your convenience)
    public static void main(String[] args) {
        Prisoner_kxiao p1 = new Prisoner_kxiao();
        Prisoner_kxiao p2 = new Prisoner_kxiao();

        // test run using provided utility
        int[] payoffs = Prisoner.test(p1, p2);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
