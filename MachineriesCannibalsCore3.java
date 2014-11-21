import java.util.Scanner;
class MachineriesCannibalsCore3  {


    // cannibals[] c = new cannibals[3];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MC_Core coreAI  = null;
        MC_Core_player corePlayer = null;

        machineries[] m = new machineries[3];
        cannibals[] c = new cannibals[3];
        coreAI = new MC_Core(3, 2, Side.LEFT, 0, 1);
        corePlayer = new MC_Core_player(3, 3, Side.LEFT);
        int nMacBoard = 0, nCanBoard = 0;
        System.out.println("\n\t\t+-------------Enter a option (1/2): -------------+");
        System.out.println("\t\t|		1. Computer Only		 |\n\t\t|		2. Player Only			 |\n\t\t+------------------------------------------------+");
        System.out.print("\t\t >>");
        switch (input.nextInt()) {
        case 1:
            coreAI.play();
            break;
        case 2:
            while (!corePlayer.goalState() && corePlayer.checkCannibalMachinery(corePlayer.getSide())) {
                //get input from user
                System.out.print("Number of Machineries/Cannibals to board: ");
                nMacBoard = input.nextInt();
                nCanBoard = input.nextInt();

                if (!corePlayer.Move(nCanBoard, nMacBoard)) {
                    System.out.println("\nThe move is not valid.\n");
                }
            }
            if (!corePlayer.status()) {
                System.out.println("Congratulations! You have completed the puzzle.");
            } else
                System.out.println("You are eaten.");
            break;
        }


        // if (!core.status()) {
        //     System.out.println("Congratulations! You have completed the puzzle.");
        // } else
        //     System.out.println("You are eaten.");
    }


}

