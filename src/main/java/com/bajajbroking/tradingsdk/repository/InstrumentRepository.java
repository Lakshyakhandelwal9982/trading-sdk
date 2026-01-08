package com.bajajbroking.tradingsdk.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bajajbroking.tradingsdk.model.Instrument;

import jakarta.annotation.PostConstruct;

@Repository
public class InstrumentRepository {

    private final List<Instrument> instruments = new ArrayList<>();

    // This method runs once when application starts
    @PostConstruct
    public void loadInstruments() {
        instruments.add(new Instrument("TCS", "NSE", "EQUITY", 3800.0));
        instruments.add(new Instrument("INFY", "NSE", "EQUITY", 1500.0));
        instruments.add(new Instrument("RELIANCE", "NSE", "EQUITY", 2500.0));
    }

    public List<Instrument> getAllInstruments() {
        return instruments;
    }
}
