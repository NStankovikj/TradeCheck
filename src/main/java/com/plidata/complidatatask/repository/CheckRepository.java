package com.plidata.complidatatask.repository;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
    List<Check> findAllByTrade(Trade trade);
}
