package com.api.payroll.services;

import com.api.payroll.models.BrandInventory;
import com.api.payroll.models.Prices;
import com.api.payroll.repositories.IBrandInventoryRepository;
import com.api.payroll.repositories.IPricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PricesService {

    @Autowired
    public IPricesRepository pricesRepository;

    public void saveData(ArrayList<Prices> pricesList) {
        pricesRepository.saveAll(pricesList);
    }

    public Prices getFirstProductByProductIdDateBrandId(Date paramDate, long paramProductId, long paramBrandId) {
        ArrayList<Prices> pricesList = pricesRepository.getProductsByProductIdDateBrandId(paramDate, paramProductId, paramBrandId);
        return !pricesList.isEmpty() ? pricesList.get(0) : null;
    }
}
