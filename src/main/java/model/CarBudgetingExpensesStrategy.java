package model;

public class CarBudgetingExpensesStrategy implements BudgetingExpensesStrategy {

    private double INSURANCE_PRICE_25_UNDER = 142.70;
    private double INSURANCE_PRICE_35_UNDER = 114.20;
    private double INSURANCE_PRICE_45_UNDER = 105.20;
    private double INSURANCE_PRICE_45_OVER = 100.00;

    @Override
    public double calculateBudgetingExpenses(double totalCost, Liability loan, User user) {
        if(user.getAge()<25){
            return loan.getMonthlyPayment() + INSURANCE_PRICE_25_UNDER;
        }else if(user.getAge()<35){
            return loan.getMonthlyPayment() + INSURANCE_PRICE_35_UNDER;
        }else if(user.getAge()<45){
            return loan.getMonthlyPayment()+INSURANCE_PRICE_45_UNDER;
        }else{
            return loan.getMonthlyPayment()+INSURANCE_PRICE_45_OVER;
        }
    }
}
