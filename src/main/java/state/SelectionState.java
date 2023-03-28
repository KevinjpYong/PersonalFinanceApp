package state;

import model.User;

import java.util.Scanner;

/**
 * Main Menu of Personal Finance App
 * display selection menu
 * redirect selection to corresponding state
 * **/

public class SelectionState implements State{
    Scanner scanner = new Scanner(System.in);

    public SelectionState(){

    }
    @Override
    public State handle(User user) {
        // Display option interface
        System.out.println("-------------------------------------");
        System.out.println("Selection Menu");
        System.out.println("-------------------------------------");
        System.out.println("1.Display/Change Saving Account");
        System.out.println("2.Display/Add Liabilities");
        System.out.println("3.Display/Add Statement");
        System.out.println("4.Display/Change Goal.");
        System.out.println("5.Budgeting.");
        System.out.print("Please select action: ");

        String choice = scanner.nextLine();

        // redirect selection to corresponding state
        switch (choice){
            case ("1"):
                return StateFactory.getInstance().createState(StateType.SAVING_STATE);
            case("2"):
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("3"):
                return StateFactory.getInstance().createState(StateType.STATEMENT_STATE);
            case("4"):
                return StateFactory.getInstance().createState(StateType.GOAL_STATE);
            case("5"):
                return StateFactory.getInstance().createState(StateType.BUDGETING_STATE);
            default:
                System.out.println("Invalid Option");
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }
    }
}
