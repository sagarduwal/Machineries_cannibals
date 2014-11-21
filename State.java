import java.util.ArrayList;
import java.util.List;

public class State {

    private int nCanLeft;
    private int nMacLeft;
    private int nCanRight;
    private int nMacRight;
    private Side boatSide;

    private State parentState;

    public State(int nCanLeft, int nMacLeft, Side boatSide, int nCanRight, int nMacRight) {
        this.nCanLeft = nCanLeft;
        this.nMacLeft = nMacLeft;
        this.boatSide = boatSide;
        this.nCanRight = nCanRight;
        this.nMacRight = nMacRight;
    }

    public boolean goalState() {
        if ( nCanLeft == 0 && nMacLeft == 0)
            return true;
        return false;
    }

    public boolean ValidityCheck() {
        if (nMacLeft >= 0 && nMacRight >= 0 && nCanLeft >= 0 && nCanRight >= 0
                && (nMacLeft == 0 || nMacLeft >= nCanLeft)
                && (nMacRight == 0 || nMacRight >= nCanRight)) {
            return true;
        }
        return false;
    }

    public List<State> getPossibleMoves() {
        List<State> possibleMoves = new ArrayList<State>();
        if (boatSide == Side.LEFT) {
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft(), numberOfMachineriesLeft() - 2, Side.RIGHT, numberOfCannibalsRight(),  numberOfMachineriesRight() + 2));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() - 2,  numberOfMachineriesLeft(), Side.RIGHT, numberOfCannibalsRight() + 2,  numberOfMachineriesRight()));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() - 1,  numberOfMachineriesLeft() - 1, Side.RIGHT, numberOfCannibalsRight() + 1,  numberOfMachineriesRight() + 1));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft(),  numberOfMachineriesLeft() - 1, Side.RIGHT, numberOfCannibalsRight(),  numberOfMachineriesRight() + 1));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() - 1,  numberOfMachineriesLeft(), Side.RIGHT, numberOfCannibalsRight() + 1,  numberOfMachineriesRight()));
        } else {
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft(), numberOfMachineriesLeft() + 2, Side.LEFT, numberOfCannibalsRight(), numberOfMachineriesRight() - 2));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() + 2, numberOfMachineriesLeft(), Side.LEFT, numberOfCannibalsRight() - 2,  numberOfMachineriesRight() ));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() + 1, numberOfMachineriesLeft() + 1, Side.LEFT, numberOfCannibalsRight() - 1,  numberOfMachineriesRight()  - 1));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft(), numberOfMachineriesLeft() + 1, Side.LEFT, numberOfCannibalsRight(),  numberOfMachineriesRight()  - 1));
            checkAndAdd(possibleMoves, new State(numberOfCannibalsLeft() + 1, numberOfMachineriesLeft(), Side.LEFT, numberOfCannibalsRight() - 1,  numberOfMachineriesRight() ));
        }
        return possibleMoves;
    }

    private void checkAndAdd(List<State> possibleMoves, State newState) {
        if (newState.ValidityCheck()) {
            newState.setParentState(this);
            possibleMoves.add(newState);
        }
    }

    public int numberOfCannibalsLeft() {
        return nCanLeft;
    }
    public int numberOfMachineriesLeft() {
        return nMacLeft;
    }

    public int numberOfCannibalsRight() {
        return nCanRight;
    }

    public int numberOfMachineriesRight() {
        return nMacRight;
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    @Override
    public String toString() {
        if (boatSide == Side.LEFT) {
            return "(" + nCanLeft + "," + nMacLeft + "," + boatSide + "," + nCanRight + "," + nMacRight + ")";
        } else {
            return "(" + nCanLeft + "," + nMacLeft + "," + boatSide + "," + nCanRight + "," + nMacRight + ")";
        }
    }

}
