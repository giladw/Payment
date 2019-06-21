package app.services;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.CouponRepository;
import app.repositories.PayableRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testReconciliationService{

    @Test
    public void ReconciliationService_testForDebugging() {
        //Arrange

        HashMap<String, Payable> payableIdMap = new HashMap<>();
        Payable payable = new Payable("UGF5YWJsZU5vZGU6YmMxMjJmZjctNTExNy00M2QxLWExNzQtYzE4MjViZWQyMzEz", 313, "AB1273","2018-02-28");
        payableIdMap.put("AB1273", payable);
        PayableRepository payableRepository = new PayableRepository(payableIdMap);
        ReconciliationService service = new ReconciliationService(payableRepository);
        //Act
        //: '2018-01-13'
        Payment payment = new Payment("AB1273", 313, "2018-01-13");
        List<Payable> result = service.getPayables(payment);

        //Assert
        Assert.assertEquals(1, result.size());
    }
    
}
