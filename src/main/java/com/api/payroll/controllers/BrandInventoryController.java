package com.api.payroll.controllers;

import com.api.payroll.models.BrandInventory;
import com.api.payroll.services.BrandInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/brand")
public class BrandInventoryController {

    @Autowired
    private BrandInventoryService brandInventoryService;

    @PostMapping(path="/registerData")
    public void saveData(@RequestBody ArrayList<BrandInventory> brandInventoryList) {
        brandInventoryService.saveData(brandInventoryList);
    }
}
