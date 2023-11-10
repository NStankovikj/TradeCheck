package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Trade;

import java.util.List;
import java.util.Optional;

public interface CheckService {
    Check save(Check check);

    Optional<Check> findById(Long id);

    List<Check> findAll();

    void delete(Long id);

    List<Check> findAllByTrade(Trade trade);
}
