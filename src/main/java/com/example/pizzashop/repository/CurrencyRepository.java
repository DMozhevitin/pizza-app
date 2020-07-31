package com.example.pizzashop.repository;

import com.example.pizzashop.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByName(String name);

}
