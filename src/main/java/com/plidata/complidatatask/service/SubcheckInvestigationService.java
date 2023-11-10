package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.SubcheckInvestigation;

import java.util.List;
import java.util.Optional;

public interface SubcheckInvestigationService {
    SubcheckInvestigation save(SubcheckInvestigation subcheckInvestigation);

    Optional<SubcheckInvestigation> findById(Long id);

    List<SubcheckInvestigation> findAll();

    void delete(Long id);
}
