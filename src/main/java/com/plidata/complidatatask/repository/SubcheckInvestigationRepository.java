package com.plidata.complidatatask.repository;

import com.plidata.complidatatask.model.SubcheckInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcheckInvestigationRepository extends JpaRepository<SubcheckInvestigation, Long> {
}
