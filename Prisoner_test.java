// Prisoner_cxzhang.java: sample implementation for Prisoner
// COS 445, Spring 2017

public class Prisoner_test implements Prisoner {
    private int nRounds = 0; // how many rounds have elapsed?
    private boolean lastPlay = true; // what did my partner play last?
    private boolean lastLastPlay = true; // what did my partner play 2 turns ago?
    // sample implementation of all cooperate
    public boolean cooperateAll() {
        return true;
    }
    
    // sample implementation of all defect
    public boolean defectAll() {
        return false;
    }
    
    // sample implementation of tit-for-tat
    public boolean titfortat() {
        return lastPlay;
    }
    
    // sample implementation of tit-for-tat-forgiving
    public boolean titfortat2() {
        if (!lastPlay && !lastLastPlay)
            return false;
        return true;
    }
       
    // one to use in test
    public boolean cooperate() {
        return titfortat2();
    }
    
    // sample implementation of callback
    public void receive(boolean action) {
        // increment number of rounds observed
        nRounds++;

        // cache last play
        lastLastPlay = lastPlay;
        lastPlay = action;
    }

    // test against itself (optional, for your convenience)
    public static void main(String[] args) {
        Prisoner_test p1 = new Prisoner_test();
        Prisoner_kxiao p2 = new Prisoner_kxiao();

        // test run using provided utility
        int[] payoffs = Prisoner.test(p1, p2);
        System.out.println("Player 1's payoff: " + payoffs[0]);
        System.out.println("Player 2's payoff: " + payoffs[1]);
    }
}
