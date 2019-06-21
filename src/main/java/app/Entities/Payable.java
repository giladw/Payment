package app.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Date getDateAsDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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

