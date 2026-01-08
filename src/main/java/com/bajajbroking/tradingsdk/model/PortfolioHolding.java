package com.bajajbroking.tradingsdk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioHolding {

    private String symbol;
    private Integer quantity;
    private Double averagePrice;
    private Double currentValue;
}
