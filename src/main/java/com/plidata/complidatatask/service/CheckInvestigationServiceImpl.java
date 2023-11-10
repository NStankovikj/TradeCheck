package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.CheckInvestigation;
import com.plidata.complidatatask.repository.CheckInvestigationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInvestigationServiceImpl implements CheckInvestigationService {

    private final CheckInvestigationRepository checkInvestigationRepository;

    @Autowired
    public CheckInvestigationServiceImpl(CheckInvestigationRepository checkInvestigationRepository) {
        this.checkInvestigationRepository = checkInvestigationRepository;
    }

    @Override
    public CheckInvestigation save(CheckInvestigation checkInvestigation) {
        return checkInvestigationRepository.save(checkInvestigation);
    }

    @Override
    public Optional<CheckInvestigation> findById(Long id) {
        return checkInvestigationRepository.findById(id);
    }

    @Override
    public List<CheckInvestigation> findAll() {
        return checkInvestigationRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        checkInvestigationRepository.deleteById(id);
    }
}
