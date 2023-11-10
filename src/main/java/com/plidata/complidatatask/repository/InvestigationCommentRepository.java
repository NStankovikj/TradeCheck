package com.plidata.complidatatask.repository;

import com.plidata.complidatatask.model.InvestigationComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationCommentRepository extends JpaRepository<InvestigationComment, Long> {
}
