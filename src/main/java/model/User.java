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

    public void setSaving(int amount){
        saving.setAmount(amount);
    }

    public int getSaving(){
        return saving.getAmount();
    }
}
