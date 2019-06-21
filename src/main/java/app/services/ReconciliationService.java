package app.services;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.PayableRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReconciliationService {
    public static final long DAYS_THRESHOLD = 90l;
    private PayableRepository payableRepository;

    public ReconciliationService(PayableRepository payableRepository) {
        this.payableRepository = payableRepository;
    }

    public List<Payable> getPayables(Payment payment){
        Collection<Payable> payables = payableRepository.GetAll();
        List<Payable> payableSameRefId = payables.stream().filter(payable -> payable.getRef_id().equals(payment.getRef_id())).collect(Collectors.toList());
        List<Payable> payableSameRefIdNotOld = payableSameRefId.stream().filter(payable -> (payable.getDateAsDate().getTime() - payment.getDateAsDate().getTime()) > DAYS_THRESHOLD).collect(Collectors.toList());

        if (payableSameRefId.size() == 1)
            return payableSameRefId;
        if (payableSameRefIdNotOld.size() >= 1)
            return payableSameRefIdNotOld;

        List<Payable> paybelsSameAmount = payables.stream().filter(payable -> payable.getAmount() == payment.getAmount()).collect(Collectors.toList());

        return paybelsSameAmount;
    }
}
