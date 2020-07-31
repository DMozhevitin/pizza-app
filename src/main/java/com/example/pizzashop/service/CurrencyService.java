package com.example.pizzashop.service;


import com.example.pizzashop.domain.Currency;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repository;


    public Currency loadUSD() {
        return repository.findByName("USD");
    }

    public Currency loadEUR() {
        return repository.findByName("EUR");
    }
}
