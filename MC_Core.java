import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


public class MC_Core {
    boolean won = false;
    State newState;

    MC_Core(int nCanLeft, int nMacLeft, Side side, int nCanRight, int nMacRight) { //reference to Left side
        newState = new State(nCanLeft, nMacLeft, side, nCanRight, nMacRight);
    }

    public void play() {
        State soln = BFSFunction(newState);
        printSolution(soln);
    }

    //  function definition
    public State BFSFunction(State init) {
        if (init.goalState()) {
            return init;
        }

        Queue<State> q = new LinkedList<State>();
        List<State> expanded = new ArrayList<State>();
        q.add(init);
        int start = 0;
        int count = 0;
        while (true) {
            if (q.isEmpty())
                return null;
            // System.out.println("\n--------------------");
            start++;

            State state = q.poll();

            expanded.add(state);
            System.out.println("count: " + count);
            List<State> possibleMoves = state.getPossibleMoves();
            for (State child : possibleMoves) {
                if (!q.contains(child) && !expanded.contains(child)) {
                    if (start > 5) {
                        printState(child);
                        start = 0;
                        count++;
                    }
                    
                    if (child.goalState()) {
                        System.out.println("\nGoal state reached. :)");
                        return child;
                    }
                    q.add(child);
                }

            }
        }
    }

    public boolean status() {
        return won;
    }

    public void printState(State s) {
        System.out.print(s.toString() + " _ ");
    }

    public void printSolution(State soln) {
        if (soln == null)
            System.out.println("No solution found.");
        else {
            System.out.println("Solution is given below: ");
            List<State> correctSolns = new ArrayList<State>();
            State st = soln;

            while (st != null) {
                correctSolns.add(st);
                st = st.getParentState();
            }

            System.out.println("Corrent Solutions: " + correctSolns.size());
            for (int i = correctSolns.size() - 1; i >= 0; i--) {
                st = correctSolns.get(i);
                System.out.println(st.toString());
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {}
            }
        }
    }
}