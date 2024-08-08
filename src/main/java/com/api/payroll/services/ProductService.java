package com.api.payroll.services;

import com.api.payroll.models.ProductModel;
import com.api.payroll.repositories.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
public class ProductService {

    @Autowired
    public IProductRepository productRepository;

    public void saveData(ArrayList<ProductModel> productModelList) {
        productRepository.saveAll(productModelList);
    }

    public boolean existsById(long id) {
        return productRepository.existsById(id);
    }

}
