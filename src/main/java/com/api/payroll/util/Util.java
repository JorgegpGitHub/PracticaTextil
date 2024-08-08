package com.api.payroll.util;

import com.api.payroll.dto.PricesDto;
import com.api.payroll.models.PricesModel;

public class Util {

    public static PricesDto pricesToPricesDto(PricesModel pricesModel) {
        PricesDto pricesDto = null;

        if(pricesModel != null) {
            pricesDto = new PricesDto();
            pricesDto.setPriceList(pricesModel.getPriceList());
            pricesDto.setProductId(pricesModel.getProductId());
            pricesDto.setBrandId(pricesModel.getBrandId());
            pricesDto.setPrice(pricesModel.getPrice());
            pricesDto.setCurr(pricesModel.getCurr());
            pricesDto.setStartDate(pricesModel.getStartDate());
            pricesDto.setEndDate(pricesModel.getEndDate());
        }

        return pricesDto;
    }

}
