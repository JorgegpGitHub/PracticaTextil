package com.api.payroll.util;

import com.api.payroll.dto.PricesDto;
import com.api.payroll.models.Prices;

public class Util {

    public static PricesDto pricesToPricesDto(Prices prices) {
        PricesDto pricesDto = null;

        if(prices != null) {
            pricesDto = new PricesDto();
            pricesDto.setPriceList(prices.getPricesID().getPriceList());
            pricesDto.setProductId(prices.getPricesID().getProductId());
            pricesDto.setBrandId(prices.getBrandId());
            pricesDto.setPrice(prices.getPrice());
            pricesDto.setCurr(prices.getCurr());
            pricesDto.setStartDate(prices.getStartDate());
            pricesDto.setEndDate(prices.getEndDate());
        }

        return pricesDto;
    }

}
