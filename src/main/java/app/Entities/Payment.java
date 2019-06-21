package app.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private String ref_id;
    private int amount;
    private String date;

    public Payment(String ref_id, int amount, String date) {
        this.ref_id = ref_id;
        this.amount = amount;
        this.date = date;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
}
