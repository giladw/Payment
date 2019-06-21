package app.Entities;

import java.util.Date;

public class Payable {
    private String Id;
    private int amount;
    private String ref_id;
    private String date;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Payable(String id, int amount, String ref_id, String date) {
        Id = id;
        this.amount = amount;
        this.ref_id = ref_id;
        this.date = date;
    }
}

