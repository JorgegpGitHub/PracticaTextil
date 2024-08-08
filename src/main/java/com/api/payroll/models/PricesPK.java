package com.api.payroll.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PricesPK implements Serializable {
    private Long priceList;
    private Long productId;

    public PricesPK() {

    }

    public PricesPK(Long priceList, Long productId) {
        this.priceList = priceList;
        this.productId = productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
