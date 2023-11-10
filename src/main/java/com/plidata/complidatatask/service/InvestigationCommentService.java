package com.plidata.complidatatask.service;

import com.plidata.complidatatask.model.InvestigationComment;

import java.util.List;
import java.util.Optional;

public interface InvestigationCommentService {
    InvestigationComment save(InvestigationComment investigationComment);

    Optional<InvestigationComment> findById(Long id);

    List<InvestigationComment> findAll();

    void delete(Long id);
}
