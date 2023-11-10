package com.plidata.complidatatask.repository;

import com.plidata.complidatatask.model.CheckInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInvestigationRepository extends JpaRepository<CheckInvestigation, Long> {
}
