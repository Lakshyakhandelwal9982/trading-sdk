package com.bajajbroking.tradingsdk.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bajajbroking.tradingsdk.model.Trade;

@Repository
public class TradeRepository {

    // In-memory storage for trades
    private final List<Trade> trades = new ArrayList<>();

    // Save a trade
    public Trade save(Trade trade) {
        trades.add(trade);
        return trade;
    }

    // Get all trades
    public List<Trade> findAll() {
        return trades;
    }
}
