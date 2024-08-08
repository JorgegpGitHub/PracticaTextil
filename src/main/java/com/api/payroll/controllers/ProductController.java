package com.api.payroll.controllers;

import com.api.payroll.models.ProductModel;
import com.api.payroll.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path="/registerData")
    public void saveData(@RequestBody ArrayList<ProductModel> productModelList) {
        productService.saveData(productModelList);
    }
}
