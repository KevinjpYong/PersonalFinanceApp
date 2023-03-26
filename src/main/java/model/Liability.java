package model;

public class Liability {
    private String name;
    private double amount;
    private double rates;
    private int duration;

    public Liability(String name, double amount, double rates, int duration){
        this.name = name;
        this.amount = amount;
        this.rates = rates;
        this.duration = duration;
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
}
