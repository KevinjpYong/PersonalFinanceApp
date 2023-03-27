package model;

public class Liability {
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

    public void setRemainingDuration(int remainingDuration){
        this.remainingDuration = remainingDuration;
    }

    public Expense createExpense(){
        String name = this.name + " Repayment";
        double monthlyPayment = getMonthlyPayment();
        reduceRemainingDuration();
        return new Expense(name, monthlyPayment);
    }

    public double getMonthlyPayment(){
        double monthRates = this.rates /12;
        double monthlyPayment = (this.amount * monthRates * Math.pow((1+monthRates),duration)) /
                (Math.pow((1+monthRates),duration) - 1);
        monthlyPayment = Math.round(monthlyPayment* 100.0) / 100.0;
        return monthlyPayment;
    }

    private void reduceRemainingDuration(){
        setRemainingDuration(this.duration - 1);
    }
}
