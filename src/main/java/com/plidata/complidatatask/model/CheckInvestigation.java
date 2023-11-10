package com.plidata.complidatatask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CheckInvestigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String investigator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampEnd;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;


}
