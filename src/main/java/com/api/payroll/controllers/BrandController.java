package com.api.payroll.controllers;

import com.api.payroll.models.BrandModel;
import com.api.payroll.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping(path="/registerData")
    public void saveData(@RequestBody ArrayList<BrandModel> brandModelList) {
        brandService.saveData(brandModelList);
    }
}
