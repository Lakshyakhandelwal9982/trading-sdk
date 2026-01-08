package com.bajajbroking.tradingsdk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {

    private String symbol;
    private String exchange;
    private String instrumentType;
    private Double lastTradedPrice;
}
