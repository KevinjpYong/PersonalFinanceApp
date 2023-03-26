package state;

import model.Liability;
import model.User;

import java.util.Scanner;

public class LiabilityState implements State{

    Scanner scanner = new Scanner(System.in);
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Liability Menu");
        System.out.println("---------------");
        String option = displayOption();
        switch (option){
            case("1"):
                displayLiabilities(user);
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("2"):
                addLiabilities(user);
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("3"):
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }
        return null;
    }

    private String displayOption(){
        System.out.println("1. Display Liabilities.");
        System.out.println("2. Add Liability.");
        System.out.println("3. Back to Main Menu.");
        return scanner.nextLine();
    }

    private void displayLiabilities(User user){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-4s%-15s %-15s %-10s %-10s ", "NO.","NAME", "AMOUNT", "RATES(%)", "DURATION(Month)");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        int i = 1;
        for(Liability liability: user.getLiabilities()){
            System.out.format("%-4s%-15s %-15.2f %-10.2f %-10s \n", i+".",liability.getName(), liability.getAmount(), liability.getRates()*100, liability.getDuration());
            i++;
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }


   private void addLiabilities(User user){
       System.out.print("Enter liability name: ");
       String name = scanner.nextLine();
       double amount = handleAmount();
       double rates = handleRates();
       int duration = handleDuration();

       user.addLiabilities(new Liability(name, amount, rates, duration));
   }


    private double handleAmount(){
        System.out.print("Enter liability amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                System.out.println("Invalid input");
                return handleAmount();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleAmount();
        }
    }

    private double handleRates(){
        System.out.print("Enter liability rates in %: ");
        String rates = scanner.nextLine();
        try{
            double ratesDouble  = Double.parseDouble(rates);
            ratesDouble = ratesDouble/100;
            if(ratesDouble >= 0){
                return ratesDouble;
            }else{
                System.out.println("Invalid input");
                return handleRates();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleRates();
        }
    }

    private int handleDuration(){
        System.out.print("Enter liability duration(months): ");
        String duration = scanner.nextLine();
        try{
            int durationInt  = Integer.parseInt(duration);
            if(durationInt >= 0){
                return durationInt;
            }else{
                System.out.println("Invalid input");
                return handleDuration();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleDuration();
        }
    }
}
