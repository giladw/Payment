package app.services;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.PayableRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class testReconciliationService {

    //        HashMap<String, Payable> payableIdMap = new HashMap<>();
//        Payable payable = new Payable("UGF5YWJsZU5vZGU6YmMxMjJmZjctNTExNy00M2QxLWExNzQtYzE4MjViZWQyMzEz", 313, "AB1273","2018-02-28");
//        payableIdMap.put("AB1273", payable);
    //PayableRepository payableRepository = new PayableRepository(payableIdMap);
    @Test
    public void ReconciliationService_TestCase1_ReturnsTrue() {
        //Arrange
        HashMap<String, Payable> payableIdMap = readJson();
        PayableRepository payableRepository = new PayableRepository(payableIdMap);
        ReconciliationService service = new ReconciliationService(payableRepository);
        //Act
        Payment payment = getPayment("AB1273", 313, "2018-01-13");
        List<Payable> result = service.getPayables(payment);
        //Assert
        Assert.assertEquals(1, result.size());
    }

    private Payment getPayment(String refId, int amount, String date) {
        return new Payment(refId, amount, date);
    }


    private HashMap<String, Payable> readJson() {
        JSONParser parser = new JSONParser();
        JSONArray a = null;
        try {
            String path = testReconciliationService.class.getClassLoader().getResource("payables_json.txt").getPath();
            a = (JSONArray) parser.parse(new FileReader(path)); //"payables_json.txt"
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, Payable> payableIdMap = new HashMap<>();

        for (Object o : a) {
            JSONObject payable = (JSONObject) o;
            String id = (String) payable.get("id");
            Payable p = new Payable(id, (int) payable.get("amount"), (String) payable.get("referenceId"), (String) payable.get("dateOccurred"));
            payableIdMap.put(id, p);

        }
        return payableIdMap;
    }

    @Test
    public void parseDate() {
        String sDate1 = "2017-12-20";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        System.out.println(sDate1 + "\t" + date1);
    }
}
