package sample;

import javafx.beans.property.SimpleStringProperty;

public class TableData {
   private SimpleStringProperty s1;

   public TableData(String s1){
       this.s1=new SimpleStringProperty(s1);
   }

    public String getS1() {
        return s1.get();
    }

    public SimpleStringProperty s1Property() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1.set(s1);
    }
}
