package com.bajajbroking.tradingsdk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bajajbroking.tradingsdk.model.Instrument;
import com.bajajbroking.tradingsdk.repository.InstrumentRepository;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> getAllInstruments() {
        return instrumentRepository.getAllInstruments();
    }
}
