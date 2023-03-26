package model;

import java.util.ArrayList;

public class User {
    String name;
    int age;
    Saving saving;
    Goal goal;
    ArrayList<Liability> liabilities;

    public User(String name, int age){
        this.name = name;
        this.age = age;
        this.saving = new Saving(0);
    }

    public void setSaving(double amount){
        saving.setAmount(amount);
    }

    public double getSaving(){
        return saving.getAmount();
    }
}
