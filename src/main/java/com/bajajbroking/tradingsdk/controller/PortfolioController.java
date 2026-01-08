package com.bajajbroking.tradingsdk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajajbroking.tradingsdk.model.PortfolioHolding;
import com.bajajbroking.tradingsdk.service.PortfolioService;

@RestController
@RequestMapping("/api/v1/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<PortfolioHolding> getPortfolio() {
        return portfolioService.getPortfolioHoldings();
    }
}
