package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Subcheck;

import java.util.List;
import java.util.Optional;

public interface SubcheckService {
    Subcheck save(Subcheck subcheck);

    Optional<Subcheck> findById(Long id);

    List<Subcheck> findAll();

    void delete(Long id);

    List<Subcheck> findAllByCheck(Check check);
}
