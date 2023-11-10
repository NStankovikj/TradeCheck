package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Trade;
import com.plidata.complidatatask.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckServiceImpl implements CheckService {

    private final CheckRepository checkRepository;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Override
    public Check save(Check check) {
        return checkRepository.save(check);
    }

    @Override
    public Optional<Check> findById(Long id) {
        return checkRepository.findById(id);
    }

    @Override
    public List<Check> findAll() {
        return checkRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        checkRepository.deleteById(id);
    }

    @Override
    public List<Check> findAllByTrade(Trade trade) {
        return checkRepository.findAllByTrade(trade);
    }
}
