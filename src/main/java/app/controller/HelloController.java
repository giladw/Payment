package app.controller;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.PayableRepository;
import app.services.ReconciliationService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

@RestController
public class HelloController {
    private ReconciliationService reconciliationService;

    public HelloController() {
        HashMap<String, Payable> payableIdMap = readPayablesFile();
        PayableRepository payableRepository = new PayableRepository(payableIdMap);
        reconciliationService = new ReconciliationService(payableRepository);
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ResponseEntity<List<Payable>> getTicket(String refId, int amount, String date) {
        String[] elements = refId.split(" ");

        Payment p = new Payment(Arrays.asList(elements),amount, date);
        return new ResponseEntity<>(reconciliationService.getPayables(p), HttpStatus.OK);
    }

    private HashMap<String, Payable> readPayablesFile() {
        JSONParser parser = new JSONParser();
        JSONArray a = null;
        try {
            String path = HelloController.class.getClassLoader().getResource("payables_json.txt").getPath();
            a = (JSONArray) parser.parse(new FileReader(path)); //"payables_json.txt"
        } catch (Exception e) {
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
}