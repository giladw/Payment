package app.services;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.PayableRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ReconciliationService {
    private PayableRepository payableRepository;

    public ReconciliationService(PayableRepository payableRepository) {
        this.payableRepository = payableRepository;
    }

    public List<Payable> getPayables(Payment payment){
        Collection<Payable> payables = payableRepository.GetAll();
        List<Payable> reslut = payables.stream().filter(payable -> payable.getRef_id().equals(payment.getRef_id())).collect(Collectors.toList());


        return reslut;
    }
}
