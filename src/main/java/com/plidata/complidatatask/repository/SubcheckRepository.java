package com.plidata.complidatatask.repository;

import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Subcheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcheckRepository extends JpaRepository<Subcheck, Long> {
    List<Subcheck> findAllByCheck(Check check);
}
