package com.api.payroll.controllers;

import com.api.payroll.dto.PricesDto;
import com.api.payroll.models.PricesModel;
import com.api.payroll.services.BrandService;
import com.api.payroll.services.PricesService;
import com.api.payroll.services.ProductService;
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

    @Autowired
    private BrandService brandService ;

    @Autowired
    private ProductService productService ;

    @PostMapping(path="/registerData")
    public void saveData(@RequestBody ArrayList<PricesModel> pricesModelList) {
        pricesService.saveData(pricesModelList);
    }

    @GetMapping(path="/getFirstPricesByProductIdBrandIdDate")
    public PricesDto getFirstPricesByProductIdBrandIdDate(@RequestBody Map<String, Object> request) throws ParseException {
        PricesDto pricesDto = null;

        if(request != null) {
            //get input params
            long paramProductId = ((Number) request.get("productId")).longValue();
            long paramBrandId = ((Number) request.get("brandId")).longValue();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date paramDate=dateFormat.parse((String)request.get("date"));

            if (!brandService.existsById(paramBrandId)) {
                throw new IllegalArgumentException("BrandId not found on table BRAND, paramBrandId: " + paramBrandId);
            }

            if (!productService.existsById(paramProductId)) {
                //throw new IllegalArgumentException("ProductId not found on table PRODUCT, paramProductId: " + paramProductId);
            }

            //find Prices by productId, date, brandId
            PricesModel pricesModel = pricesService.getFirstProductByProductIdDateBrandId(paramDate, paramProductId, paramBrandId);

            pricesDto = Util.pricesToPricesDto(pricesModel);
        }

        return pricesDto;
    }

}
