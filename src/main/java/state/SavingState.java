package state;

import model.User;

import java.util.Scanner;

public class SavingState implements State{
    Scanner scanner = new Scanner(System.in);
    @Override
    public State handle(User user) {
        System.out.printf("Amount in Saving: %,.2f \n", user.getSaving());
        System.out.println("1.Change amount.");
        System.out.println("2.Return to menu.");
        String selection = scanner.nextLine();
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

    private State handleSavingAmount(User user){
        System.out.print("Please enter your saving amount: ");
        String amount = scanner.nextLine();
        return handleSavingAmountConvert(user, amount);
    }

    private State handleSavingAmountConvert(User user, String amount){
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                user.setSaving(roundOff);
            }else{
                System.out.println("Invalid input");
                return handleSavingAmount(user);
            }
            return StateFactory.getInstance().createState(StateType.SAVING_STATE);
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleSavingAmount(user);
        }
    }
}
