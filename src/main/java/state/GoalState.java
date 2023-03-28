package state;

import model.Goal;
import model.User;
import java.util.Scanner;

/**
 * Goal State
 * View or change user's goal
 * **/

public class GoalState implements State{

    Scanner scanner = new Scanner(System.in);

    // handle state
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Goal Menu");
        System.out.println("---------------");
        displayGoal(user);
        String option = displayOption();
        return handleOption(option, user);
    }

    // display option in state
    private String displayOption(){
        System.out.println("1. Change Goal.");
        System.out.println("2. Back to Main Menu.");
        return scanner.nextLine();
    }

    // handle user input option
    private State handleOption(String option, User user){
        switch (option){
            case("1"):
                // change user goal
                changeGoal(user);
                return StateFactory.getInstance().createState(StateType.GOAL_STATE);
            case("2"):
                // redirect back to selection state
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
            default:
                // invalid input redirect back to same state
                System.out.println("Invalid Option!");
                return StateFactory.getInstance().createState(StateType.GOAL_STATE);
        }
    }

    // display user goal
    private void displayGoal(User user){
        // if user has no goal
        if(user.getGoal()==null){
            System.out.printf("%-30s %-15.2f \n", "Saving:",user.getSaving().getAmount());
            System.out.println("NO GOAL IN PROGRESS");
        }else{
            // display user's saving and remaining goal
            System.out.printf("%-30s %-15.2f \n", user.getGoal().getName()+" Goal:",
                    user.getGoal().getAmount());
            System.out.printf("%-30s %-15.2f \n", "Saving:",user.getSaving().getAmount());
            System.out.printf("%-30s %-15.2f \n", "Remaining Amount To Goal",
                    user.getGoal().calAmountRemaining(user));
            // display estimate amount to achieve goal
            displayEstimationDuration(user);
        }
    }


    // display user's estimate duration to achieve goal based on past statements
    private void displayEstimationDuration(User user){
        if(user.getGoal().estimateDurationRequired(user) == 0 ){
            System.out.printf("%-50s %-15d \n", "Estimation Duration To Achieve Goal:",
                    0);
            System.out.println("GOAL ACHIEVE !!!");
        }else if(user.getGoal().estimateDurationRequired(user) < 0 ){
            System.out.printf("%-50s %-15s \n", "Estimation Duration To Achieve Goal:",
                    "UNABLE");
            System.out.println("BASED ON PAST NET INCOME GOAL UNABLE TO ACHIEVE");
        }else{
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-50s %-15.2f \n", "Average Net Income:",
                    user.getAverageNetIncome());
            System.out.printf("%-50s %-15d \n", "Estimation Duration To Achieve Goal(months):",
                    user.getGoal().estimateDurationRequired(user));
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // change user's goal
    private void changeGoal(User user){
        System.out.print("Enter Goal Name: ");
        String name = scanner.nextLine();
        double amount = handleAmount();
        user.setGoal(new Goal(name, amount));
    }

    // get amount from user input
    private double handleAmount(){
        System.out.print("Enter Amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                // round off to 2 decimal to represent money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                // negative value re-ask user for input
                System.out.println("Invalid input");
                return handleAmount();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleAmount();
        }
    }
}
