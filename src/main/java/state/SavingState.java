package state;

import model.User;

import java.util.Scanner;

/**
 * Saving State
 * View or change user's saving amount
 * **/

public class SavingState implements State{
    Scanner scanner = new Scanner(System.in);

    // handle state
    @Override
    public State handle(User user) {
        // display options
        System.out.println("---------------");
        System.out.println("Saving Menu");
        System.out.println("---------------");
        System.out.printf("Amount in Saving: %,.2f \n", user.getSaving().getAmount());
        System.out.println("1.Change amount.");
        System.out.println("2.Return to menu.");
        String selection = scanner.nextLine();
        // handle options
        if(selection.equals("1")){
            return handleSavingAmount(user);
        }else if(selection.equals("2")){
            System.out.println("Returning selection state");
            return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }else{
            System.out.println("Option not available. Please reselect option");
            return StateFactory.getInstance().createState(StateType.SAVING_STATE);
        }
    }

    // get saving amount from user (String value)
    private State handleSavingAmount(User user){
        System.out.print("Please enter your saving amount: ");
        String amount = scanner.nextLine();
        return handleSavingAmountConvert(user, amount);
    }

    // convert user input to number
    private State handleSavingAmountConvert(User user, String amount){
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                // round off to 2 decimal to represent money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                user.getSaving().setAmount(roundOff);
            }else{
                // input is negative value re-ask user for input
                System.out.println("Invalid input");
                return handleSavingAmount(user);
            }
            return StateFactory.getInstance().createState(StateType.SAVING_STATE);
        }catch(NumberFormatException nfe){
            // unable to convert user input to number
            System.out.println("Invalid input");
            return handleSavingAmount(user);
        }
    }
}
