package com.plidata.complidatatask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SubcheckInvestigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "escalate")
    private Boolean escalate;

    @OneToMany(mappedBy = "subcheckInvestigation")
    @JsonManagedReference
    private List<InvestigationComment> investigationComment;

    @ManyToOne
    @JoinColumn(name = "subcheck_id")
    @JsonBackReference
    private Subcheck subcheck;
}
