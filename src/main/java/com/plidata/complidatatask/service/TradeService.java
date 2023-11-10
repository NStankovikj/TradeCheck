package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    Trade save(Trade trade);

    Optional<Trade> findById(Long id);

    List<Trade> findAll();

    void delete(Long id);
}

