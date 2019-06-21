package app.services;

import app.Entities.Payable;
import app.Entities.Payment;
import app.repositories.PayableRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ReconciliationService {
    public static final long DAYS_THRESHOLD = 25l;
    private PayableRepository payableRepository;

    public ReconciliationService(PayableRepository payableRepository) {
        this.payableRepository = payableRepository;
    }

    public List<Payable> getPayables(Payment payment) {
        Collection<Payable> payables = payableRepository.GetAll();
//        List<Payable> payableSameRefId = payables.stream().filter(payable -> payable.getRef_id().equals(payment.getRef_id())).collect(Collectors.toList());
        List<Payable> payableSameRefId = payables.stream().filter(payable -> payment.getRef_id().contains(payable.getRef_id())).collect(Collectors.toList());
        List<Payable> payableSameRefIdNotOld = payableSameRefId.stream().filter(payable -> isPaymentInThreshold(payment, payable)).collect(Collectors.toList());

        List<Payable> payablebyDate = payables.stream().filter(payable -> {
                    return isPaymentInThreshold(payment, payable);
                }
        ).collect(Collectors.toList());

        if (payableSameRefId.size() == 1)
            return payableSameRefId;
        if (payableSameRefIdNotOld.size() >= 1)
            return payableSameRefIdNotOld;

        List<Payable> paybelsSameAmount = payables.stream().filter(payable -> payable.getAmount() == payment.getAmount()).collect(Collectors.toList());
        if (paybelsSameAmount.size() >= 1)
            return paybelsSameAmount;

        return payablebyDate;
    }

    private boolean isPaymentInThreshold(Payment payment, Payable payable) {
        long days = getDaysDistance(payment, payable);
        if (days > 0 && days < DAYS_THRESHOLD)
            return true;
        else
            return false;
    }

    private long getDaysDistance(Payment payment, Payable payable) {
        long diff = payment.getDateAsDate().getTime() - payable.getDateAsDate().getTime();
        long result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return result;
    }
}
