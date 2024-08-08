package com.api.payroll.controllers;

import com.api.payroll.dto.PricesDto;
import com.api.payroll.models.Prices;
import com.api.payroll.services.PricesService;
import com.api.payroll.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @PostMapping(path="/registerData")
    public void saveData(@RequestBody ArrayList<Prices> pricesList) {
        pricesService.saveData(pricesList);
    }

    @GetMapping(path="/getProductByProductIdDateBrandId")
    public PricesDto getFirstProductByProductIdDateBrandId(@RequestBody Map<String, Object> request) throws ParseException {
        PricesDto pricesDto = null;

        if(request != null) {
            //get input params
            long paramProductId = ((Number) request.get("productId")).longValue();
            long paramBrandId = ((Number) request.get("brandId")).longValue();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date paramDate=dateFormat.parse((String)request.get("date"));

            //find Prices by productId, date, brandId
            Prices prices = pricesService.getFirstProductByProductIdDateBrandId(paramDate, paramProductId, paramBrandId);

            pricesDto = Util.pricesToPricesDto(prices);
        }

        return pricesDto;
    }

}
