package com.bajajbroking.tradingsdk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bajajbroking.tradingsdk.model.PortfolioHolding;
import com.bajajbroking.tradingsdk.model.Trade;
import com.bajajbroking.tradingsdk.repository.InstrumentRepository;
import com.bajajbroking.tradingsdk.repository.TradeRepository;

@Service
public class PortfolioService {

    private final TradeRepository tradeRepository;
    private final InstrumentRepository instrumentRepository;

    public PortfolioService(TradeRepository tradeRepository,
                            InstrumentRepository instrumentRepository) {
        this.tradeRepository = tradeRepository;
        this.instrumentRepository = instrumentRepository;
    }

    public List<PortfolioHolding> getPortfolioHoldings() {

        List<Trade> trades = tradeRepository.findAll();
        Map<String, List<Trade>> tradesBySymbol = new HashMap<>();

        // Group trades by symbol
        for (Trade trade : trades) {
            tradesBySymbol
                    .computeIfAbsent(trade.getSymbol(), k -> new ArrayList<>())
                    .add(trade);
        }

        List<PortfolioHolding> holdings = new ArrayList<>();

        for (Map.Entry<String, List<Trade>> entry : tradesBySymbol.entrySet()) {
            String symbol = entry.getKey();
            List<Trade> symbolTrades = entry.getValue();

            int totalQuantity = 0;
            double totalCost = 0.0;

            for (Trade trade : symbolTrades) {
                totalQuantity += trade.getQuantity();
                totalCost += trade.getQuantity() * trade.getPrice();
            }

            double averagePrice = totalQuantity == 0 ? 0 : totalCost / totalQuantity;

            double lastTradedPrice = instrumentRepository.getAllInstruments()
                    .stream()
                    .filter(i -> i.getSymbol().equalsIgnoreCase(symbol))
                    .findFirst()
                    .map(i -> i.getLastTradedPrice())
                    .orElse(0.0);

            double currentValue = totalQuantity * lastTradedPrice;

            holdings.add(new PortfolioHolding(
                    symbol,
                    totalQuantity,
                    averagePrice,
                    currentValue
            ));
        }

        return holdings;
    }
}
