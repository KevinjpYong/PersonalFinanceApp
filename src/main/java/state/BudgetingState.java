package state;

import model.Budgeting;
import model.BudgetingType;
import model.Liability;
import model.User;

import java.util.Scanner;

public class BudgetingState implements State{

    Scanner scanner = new Scanner(System.in);
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Budgeting Menu");
        System.out.println("---------------");
        BudgetingType budgetingType = handleBudgetType();
        double totalCost = handleTotalCost();
        double loanAmount = handleLoanAmount();
        double interestRates = handleRates();
        int loanDuration = handleLoanDuration();
        Liability loan = new Liability(budgetingType.name(),loanAmount,interestRates,loanDuration);
        Budgeting budgeting = new Budgeting(budgetingType, totalCost, loan, user);
        System.out.println("---------------------------------------------------");
        System.out.println("Budgeting Result");
        System.out.println("---------------------------------------------------");
        displayInitialPaymentReport(user, budgeting);
        displayMonthlyPaymentReport(user, budgeting);
        if(budgeting.calInitialPaymentWithSaving()>=0 && budgeting.calNetIncomeAfterExtraExpenses()>=0){
            System.out.println("Sufficient Saving and Income to carry out plan.");
        }else{
            System.out.println("Insufficient Saving or Income to carry out plan. ");
        }
        return handleReturnMainMenu();
    }

    private void displayInitialPaymentReport(User user, Budgeting budgeting){
        System.out.println("Initial Payment Report");
        System.out.println("------------------------");
        System.out.printf("%-30s %-15.2f \n", "Saving:",user.getSaving().getAmount());
        System.out.printf("%-45s %-15.2f \n", "InitialPayment:", budgeting.calCurrentPayment());
        System.out.println("------------------------------------------------");
        System.out.println(budgeting.calInitialPaymentWithSaving());
        if(budgeting.calInitialPaymentWithSaving()>=0){
            System.out.println("Sufficient saving for initial payment.");
        }else{
            System.out.printf("%-60s %-15.2f \n", "Required Amount:", budgeting.calInitialPaymentWithSaving()*-1);
        }
        System.out.println("------------------------------------------------");
    }

    private void displayMonthlyPaymentReport(User user, Budgeting budgeting){
        System.out.println("Monthly Payment Report");
        System.out.println("------------------------");
        System.out.printf("%-30s %-15.2f \n", "Average Net Income:",user.getAverageNetIncome());
        System.out.printf("%-45s %-15.2f \n", "Extra Expenses:", budgeting.calExtraExpenses());
        System.out.println("------------------------------------------------");
        if(budgeting.calNetIncomeAfterExtraExpenses()>=0){
            System.out.println("Sufficient Monthly Net Income.");
        }else{
            System.out.printf("%-60s %-15.2f \n", "Required Extra Income:", budgeting.calNetIncomeAfterExtraExpenses()*-1);
        }
        System.out.println("------------------------------------------------");
    }



    private BudgetingType handleBudgetType(){
        System.out.print("Choose Budget Type: \n");
        System.out.println("1.Car Purchase\n2.House Purchase");
        String option = scanner.nextLine();
        switch (option){
            case("1"):
                return BudgetingType.CAR_PURCHASE;
            case("2"):
                return BudgetingType.HOUSE_PURCHASE;
            default:
                System.out.println("Invalid Option");
                return handleBudgetType();
        }
    }

    private double handleTotalCost(){
        System.out.print("Enter Total Cost: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                System.out.println("Invalid input");
                return handleTotalCost();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleTotalCost();
        }
    }

    private double handleLoanAmount(){
        System.out.print("Enter Loan Amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                System.out.println("Invalid input");
                return handleLoanAmount();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleLoanAmount();
        }
    }

    private double handleRates(){
        System.out.print("Enter interest rates in %: ");
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

    private int handleLoanDuration(){
        System.out.print("Enter loan duration(months): ");
        String duration = scanner.nextLine();
        try{
            int durationInt  = Integer.parseInt(duration);
            if(durationInt >= 0){
                return durationInt;
            }else{
                System.out.println("Invalid input");
                return handleLoanDuration();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleLoanDuration();
        }
    }

    private State handleReturnMainMenu(){
        System.out.println("1. Return to Main Menu.");
        String option = scanner.nextLine();
        if(option.equals("1")){
            return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }else{
            System.out.println("Invalid Option.");
            return handleReturnMainMenu();
        }
    }


}
