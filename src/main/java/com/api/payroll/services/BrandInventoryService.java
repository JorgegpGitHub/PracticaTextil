package com.api.payroll.services;

import com.api.payroll.models.BrandInventory;

import com.api.payroll.repositories.IBrandInventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
public class BrandInventoryService {

    @Autowired
    public IBrandInventoryRepository brandInventoryRepository;

    public void saveData(ArrayList<BrandInventory> brandInventory) {
        brandInventoryRepository.saveAll(brandInventory);
    }


}
