package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.CheckInvestigation;

import java.util.List;
import java.util.Optional;

public interface CheckInvestigationService {
    CheckInvestigation save(CheckInvestigation checkInvestigation);

    Optional<CheckInvestigation> findById(Long id);

    List<CheckInvestigation> findAll();

    void delete(Long id);
}
