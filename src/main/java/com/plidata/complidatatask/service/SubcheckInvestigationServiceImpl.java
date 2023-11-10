package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.SubcheckInvestigation;
import com.plidata.complidatatask.repository.SubcheckInvestigationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcheckInvestigationServiceImpl implements SubcheckInvestigationService {

    private final SubcheckInvestigationRepository subcheckInvestigationRepository;

    @Autowired
    public SubcheckInvestigationServiceImpl(SubcheckInvestigationRepository subcheckInvestigationRepository) {
        this.subcheckInvestigationRepository = subcheckInvestigationRepository;
    }

    @Override
    public SubcheckInvestigation save(SubcheckInvestigation subcheckInvestigation) {
        return subcheckInvestigationRepository.save(subcheckInvestigation);
    }

    @Override
    public Optional<SubcheckInvestigation> findById(Long id) {
        return subcheckInvestigationRepository.findById(id);
    }

    @Override
    public List<SubcheckInvestigation> findAll() {
        return subcheckInvestigationRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        subcheckInvestigationRepository.deleteById(id);
    }
}
