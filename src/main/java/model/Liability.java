package model;

public class Liability {
    private String name;
    private double amount;
    private float rates;
    private int duration;

    public Liability(String name, double amount, float rates, int duration){
        this.name = name;
        this.amount = amount;
        this.rates = rates;
        this.duration = duration;
    }
}
