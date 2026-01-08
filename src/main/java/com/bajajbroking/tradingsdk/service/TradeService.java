package com.bajajbroking.tradingsdk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bajajbroking.tradingsdk.model.Trade;
import com.bajajbroking.tradingsdk.repository.TradeRepository;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
}
