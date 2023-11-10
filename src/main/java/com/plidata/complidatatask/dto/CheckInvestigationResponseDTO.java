package com.plidata.complidatatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CheckInvestigationResponseDTO {
    private Long id;
    private String investigator;
    private Date timestampStart;
    private Date timestampEnd;

}
