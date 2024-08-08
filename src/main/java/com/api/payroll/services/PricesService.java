package com.api.payroll.services;

import com.api.payroll.models.PricesModel;
import com.api.payroll.repositories.IPricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PricesService {

    @Autowired
    public IPricesRepository pricesRepository;

    public void saveData(ArrayList<PricesModel> pricesModelList) {
        pricesRepository.saveAll(pricesModelList);
    }

    public PricesModel getFirstProductByProductIdDateBrandId(Date paramDate, long paramProductId, long paramBrandId) {
        ArrayList<PricesModel> pricesModelList = pricesRepository.getProductsByProductIdDateBrandId(paramDate, paramProductId, paramBrandId);
        return !pricesModelList.isEmpty() ? pricesModelList.get(0) : null;
    }


}
