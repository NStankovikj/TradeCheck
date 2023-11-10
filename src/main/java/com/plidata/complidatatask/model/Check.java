package com.plidata.complidatatask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "\"check\"")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "trade_id")
    @JsonBackReference
    private Trade trade;

    @OneToMany(mappedBy = "check")
    @JsonManagedReference
    private List<Subcheck> subchecks;

    @OneToMany(mappedBy = "check")
    @JsonManagedReference
    private List<CheckInvestigation> checkInvestigation;

}
