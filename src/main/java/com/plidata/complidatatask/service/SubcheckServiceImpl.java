package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Subcheck;
import com.plidata.complidatatask.repository.SubcheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcheckServiceImpl implements SubcheckService {

    private final SubcheckRepository subcheckRepository;

    @Autowired
    public SubcheckServiceImpl(SubcheckRepository subcheckRepository) {
        this.subcheckRepository = subcheckRepository;
    }

    @Override
    public Subcheck save(Subcheck subcheck) {
        return subcheckRepository.save(subcheck);
    }


    @Override
    public Optional<Subcheck> findById(Long id) {
        return subcheckRepository.findById(id);
    }

    @Override
    public List<Subcheck> findAll() {
        return subcheckRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        subcheckRepository.deleteById(id);
    }

    @Override
    public List<Subcheck> findAllByCheck(Check check) {
        return subcheckRepository.findAllByCheck(check);
    }
}
