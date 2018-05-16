package RaidDropCalc;


import javafx.beans.property.SimpleStringProperty;

public class Chance
{
    private final SimpleStringProperty chanceType;

    public Chance(String s) {
        this.chanceType = new SimpleStringProperty(s);
    }
    public String getChanceType(){
        return chanceType.get();
    }
    public void setChanceType(String s){
        this.chanceType.set(s);
    }
}
