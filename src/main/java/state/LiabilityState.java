package state;

import model.Liability;
import model.User;

import java.util.Scanner;

/**
 * Liability State
 * View or add user's Liability
 * **/

public class LiabilityState implements State{

    Scanner scanner = new Scanner(System.in);

    // handle state
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Liability Menu");
        System.out.println("---------------");
        // display options
        String option = displayOption();
        // handle options
        switch (option){
            case("1"):
                displayLiabilities(user);
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("2"):
                addLiabilities(user);
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("3"):
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
            default:
                System.out.println("Invalid Option");
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
        }
    }

    // display options
    private String displayOption(){
        System.out.println("1. Display Liabilities.");
        System.out.println("2. Add Liability.");
        System.out.println("3. Back to Main Menu.");
        return scanner.nextLine();
    }

    // display liability
    private void displayLiabilities(User user){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-4s%-15s %-15s %-20s %-20s %-20s", "NO.","NAME", "AMOUNT", "RATES(%)", "DURATION(Month)","MONTHLY PAYMENT");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        int i = 1;
        for(Liability liability: user.getLiabilities()){
            System.out.format("%-4s%-15s %-15.2f %-20.2f %-20s %-20.2f \n",
                    i+".",liability.getName(),
                    liability.getAmount(),
                    liability.getRates()*100,
                    liability.getRemainingDuration(),
                    liability.getMonthlyPayment());
            i++;
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    // add liability
   private void addLiabilities(User user){
       System.out.print("Enter liability name: ");
       String name = scanner.nextLine();
       double amount = handleAmount();
       double rates = handleRates();
       int duration = handleDuration();

       user.addLiabilities(new Liability(name, amount, rates, duration));
   }

    // get amount from user input
    private double handleAmount(){
        System.out.print("Enter liability amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                // round off to 2 decimal to represent money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                // user input negative value re-ask user for input
                System.out.println("Invalid input");
                return handleAmount();
            }
        }catch(NumberFormatException nfe){
            // unable to convert user input to number
            System.out.println("Invalid input");
            return handleAmount();
        }
    }

    // get rates from user input
    private double handleRates(){
        System.out.print("Enter liability rates in %: ");
        String rates = scanner.nextLine();
        try{
            double ratesDouble  = Double.parseDouble(rates);
            ratesDouble = ratesDouble/100;
            if(ratesDouble >= 0){
                return ratesDouble;
            }else{
                // user input negative value re-ask user for input
                System.out.println("Invalid input");
                return handleRates();
            }
        }catch(NumberFormatException nfe){
            // unable to convert user input to number
            System.out.println("Invalid input");
            return handleRates();
        }
    }

    // get duration from user input
    private int handleDuration(){
        System.out.print("Enter liability duration(months): ");
        String duration = scanner.nextLine();
        try{
            int durationInt  = Integer.parseInt(duration);
            if(durationInt >= 0){
                return durationInt;
            }else{
                // user input negative value re-ask user for input
                System.out.println("Invalid input");
                return handleDuration();
            }
        }catch(NumberFormatException nfe){
            // unable to convert user input to number
            System.out.println("Invalid input");
            return handleDuration();
        }
    }
}
