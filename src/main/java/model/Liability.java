package model;

/**
 * Model for Liability
 * **/
public class Liability{
    private String name;
    private double amount;
    private double rates;
    private int duration;

    private int remainingDuration;

    public Liability(String name, double amount, double rates, int duration){
        this.name = name;
        this.amount = amount;
        this.rates = rates;
        this.duration = duration;
        this.remainingDuration = duration;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public double getRates() {
        return rates;
    }

    public int getDuration() {
        return duration;
    }

    public int getRemainingDuration(){
        return this.remainingDuration;
    }

    public void setRemainingDuration(int remainingDuration){
        this.remainingDuration = remainingDuration;
    }

    // create expense based on monthly repayment
    public Expense createExpense(){
        String name = this.name + " Repayment";
        double monthlyPayment = getMonthlyPayment();
        reduceRemainingDuration();
        return new Expense(name, monthlyPayment);
    }

    // calculate monthly repayment using principle + interest payment
    // Repayment formula = (Total Amount * rates *((1+rates)^duration))) / (((1+rates)^duration) - 1)
    public double getMonthlyPayment(){
        // dive 12 to get monthly rates
        double monthRates = this.rates /12;
        double monthlyPayment = (this.amount * monthRates * Math.pow((1+monthRates),duration)) /
                (Math.pow((1+monthRates),duration) - 1);
        monthlyPayment = Math.round(monthlyPayment* 100.0) / 100.0;
        return monthlyPayment;
    }

    // reduce the remaining duration of the liability
    private void reduceRemainingDuration(){
        setRemainingDuration(this.duration - 1);
    }
}
