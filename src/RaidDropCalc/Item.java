package RaidDropCalc;


import javafx.beans.property.SimpleStringProperty;

public class Item
{
    private final SimpleStringProperty itemType;

    public Item(String s) {
        this.itemType = new SimpleStringProperty(s);
    }
    public String getItemType(){
        return itemType.get();
    }
    public void setItemType(String s){
        this.itemType.set(s);
    }
}
