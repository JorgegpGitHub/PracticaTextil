package com.api.payroll.services;

import com.api.payroll.models.BrandModel;

import com.api.payroll.repositories.IBrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
public class BrandService {

    @Autowired
    public IBrandRepository brandRepository;

    public void saveData(ArrayList<BrandModel> brandModel) {
        brandRepository.saveAll(brandModel);
    }

    public boolean existsById(long id) {
        return brandRepository.existsById(id);
    }

}
