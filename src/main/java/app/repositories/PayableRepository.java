package app.repositories;

import app.Entities.Payable;

import java.util.Collection;
import java.util.Map;

public class PayableRepository{
    private Map<String, Payable> payableIdMap;

    public Collection<Payable> GetAll(){
        return payableIdMap.values();
    }

}
