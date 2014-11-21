public class MC_Core_player {
    //variable definition
    int nCan = 0;
    int nMac = 0;
    // Side boatSideStart = Side.LEFT;
    int maxBoatNumber = 2;
    int maxNumber = 3;
    int nCanCurrent = 0;
    int nMacCurrent = 0;
    Side boatSide;
    boolean won = false; // false --> not won


    MC_Core_player(int nCan, int nMac, Side side) {
        this.nCan = nCan;
        this.nMac = nMac;
        this.boatSide = side;
        // printState();
    }
    //  function definition
    //  the final goal to reach
    public boolean goalState() {
        if (nCan == 0 && nMac == 0)
            return true;
        return false;
    }

    //  move the characters
    public boolean Move(int nCan, int nMac) {
        // printState();

        this.nCanCurrent = nCan;
        this.nMacCurrent = nMac;
        if (ValidityCheck()) {
            sendBoat();
            printState();
            return true;
        }
        return false;
    }
    public boolean status() {
        return won;
    }
    public Side getSide() {
        if (boatSide == Side.LEFT) {
            return Side.LEFT;
        } else
            return Side.RIGHT;

    }

    boolean ValidityCheck() {
        if (!checkBoatNumber()) {
            System.out.println("Boat number Error");
            return false;
        }
        if (!checkOverCapacitySides(boatSide)) {
            System.out.println("Capacity Mismatch Error");
            return false;
        }
        // we can include the checkCannibalMachinery() function and make the cannibal to eat.
        if (!checkCannibalMachinery(boatSide)) {
            System.out.println("Cannibals > Machineries Error");
            return false;
        }
        return true;
    }

    //  check the number of boarding members
    boolean checkBoatNumber() {
        if (nCanCurrent > maxNumber || nMacCurrent > maxNumber ||
                nCanCurrent > maxBoatNumber ||
                nMacCurrent > maxBoatNumber ||
                nMacCurrent + nCanCurrent > maxBoatNumber ||
                nMacCurrent < 0 || nCanCurrent < 0 ||
                (nMacCurrent <= 0 && nCanCurrent <= 0))
            return false;
        return true;
    }

    //  check the number of cannibals and/or machineries in both sides
    boolean checkOverCapacitySides(Side s) {
        if (s == Side.LEFT) {
            if (nCan - nCanCurrent < 0  ||
                    nMac - nMacCurrent < 0)
                return false;
        } else if (s == Side.RIGHT) {

            if ((maxNumber - nCan) - nCanCurrent < 0 ||
                    (maxNumber - nMac) - nMacCurrent < 0 )
                return false;
        }
        return true;
    }

    void getPossibleMoves() {
        //using BFS algorithm

    }
    void testAndAdd(Side s) {
        if (s == Side.LEFT) {
            checkCannibalMachinery(s);
        }
    }
    int numberOfCannibalsLeft() {
        return nCan;
    }
    int numberOfMachineriesLeft() {
        return nMac;
    }
    int numberOfCannibalsRight() {
        return maxNumber - nCan ;
    }
    int numberOfMachineriesRight() {
        return maxNumber - nMac ;
    }
    //check if the number of cannibals is greater than machineries
    boolean checkCannibalMachinery(Side s) {
        // System.out.println("\nnCan: " + nCan + " nCanCurrent: " + nCanCurrent + " nMac: " + nMac + " nMacCurrent: " + nMacCurrent);
        if (numberOfCannibalsLeft() > numberOfMachineriesLeft()) { // || numberOfCannibalsRight() > numberOfMachineriesRight()) {
            return false;
        }

        // if (s == Side.LEFT) {
        //     if (numberOfCannibalsLeft() > numberOfMachineriesLeft()) {
        //         // if ((nCan - nCanCurrent ) > ( nMac - nMacCurrent) && (nMac < 0 || nCan < 0)) {
        //         // System.out.println("\nnCan: " + nCan + " nCanCurrent: " + nCanCurrent + " nMac: " + nMac + " nMacCurrent: " + nMacCurrent);
        //         System.out.println("\nLeft side error");
        //         return false;
        //     }
        // } else if (s == Side.RIGHT) {
        //     if (numberOfCannibalsRight() > numberOfMachineriesRight()) {
        //         // if ((maxNumber - nCan - nCanCurrent) > (maxNumber - nMac - nMacCurrent) && (maxNumber - nMac < 0 || maxNumber - nCan < 0)) {
        //         // System.out.println("\nnCan: " + nCan + " nCanCurrent: " + nCanCurrent + " nMac: " + nMac + " nMacCurrent: " + nMacCurrent);
        //         System.out.println("\nRight side error");
        //         return false;
        //     }
        // }
        return true;
    }

    // send the boat to other direction
    void sendBoat() {
        //change the number of machineries and cannibals in each side (we consider on left side)
        Side boatDirectionToMove;
        changeNumbersInLeft();
        if (checkCannibalMachinery(boatSide)) {

            // return false;

            if (boatSide == Side.LEFT) {
                boatDirectionToMove = Side.RIGHT;

                System.out.println("\n\tLeft Number: Cannibals: " + numberOfCannibalsLeft() + " Machineries: " + numberOfMachineriesLeft());
                boatSide = Side.RIGHT;
                System.out.println("Boat moved to Right and moved by dinesh");
            } else if (boatSide == Side.RIGHT) {
                boatDirectionToMove = Side.LEFT;
                System.out.println("\n\tRight Number: Cannibals: " + numberOfCannibalsRight() + " Machineries: " + numberOfMachineriesRight());
                boatSide = Side.LEFT;
                System.out.println("Boat moved to Left");
            } else {
                System.out.println("\nBoat going to other side.");
            }
            won = false;
        } else {
            System.out.println("Cannibals > Machineries Error");
            // System.out.println("you are eaten");
            won = true;
        }

    }

    //  display the state of the game
    // void printState() {
    //     System.out.println("\n\t\t\t+---Left Side---|---Right Side--+");
    //     System.out.println("\tMachineries:    |\t" + nMac + "\t|   \t" + (  maxNumber - nMac) + "\t|");
    //     System.out.println("\tCannibals:      |\t" + nCan + "\t|   \t" + ( maxNumber - nCan ) + "\t|\n");
    // }

    void printState() {
        System.out.println("\n\t\t\t+---Left Side---|---Right Side--+");
        System.out.println("\tMachineries:    |\t" + numberOfMachineriesLeft() + "\t|   \t" + numberOfMachineriesRight() + "\t|");
        System.out.println("\tCannibals:      |\t" + numberOfCannibalsLeft() + "\t|   \t" + numberOfCannibalsRight() + "\t|\n");
    }
    // change the values of number of Cannibals and Machineries
    void changeNumbersInLeft() {
        if (boatSide == Side.LEFT) {
            this.nCan = this.nCan - nCanCurrent;
            this.nMac = this.nMac - nMacCurrent;
        } else if (boatSide == Side.RIGHT) {
            this.nCan = this.nCan + nCanCurrent;
            this.nMac = this.nMac + nMacCurrent;
        }
    }


}