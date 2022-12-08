package edu.qc.seclass.glm.Models;

public class Data {

    public String type;
    public String quantity;

    public Data(String t_name, String s) {
        this.type = t_name;
        this.quantity = s;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
