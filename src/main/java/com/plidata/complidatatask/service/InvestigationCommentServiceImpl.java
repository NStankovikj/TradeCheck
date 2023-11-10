package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.InvestigationComment;
import com.plidata.complidatatask.repository.InvestigationCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationCommentServiceImpl implements InvestigationCommentService {

    private final InvestigationCommentRepository investigationCommentRepository;

    @Autowired
    public InvestigationCommentServiceImpl(InvestigationCommentRepository investigationCommentRepository) {
        this.investigationCommentRepository = investigationCommentRepository;
    }

    @Override
    public InvestigationComment save(InvestigationComment investigationComment) {
        return investigationCommentRepository.save(investigationComment);
    }

    @Override
    public Optional<InvestigationComment> findById(Long id) {
        return investigationCommentRepository.findById(id);
    }

    @Override
    public List<InvestigationComment> findAll() {
        return investigationCommentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        investigationCommentRepository.deleteById(id);
    }
}
