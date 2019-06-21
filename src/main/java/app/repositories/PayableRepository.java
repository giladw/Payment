package app.repositories;

import app.Entities.Payable;

import java.util.Collection;
import java.util.Map;

public class PayableRepository{
    public PayableRepository(Map<String, Payable> payableIdMap) {
        this.payableIdMap = payableIdMap;
    }

    private Map<String, Payable> payableIdMap;

    public Collection<Payable> GetAll(){
        return payableIdMap.values();
    }

}
