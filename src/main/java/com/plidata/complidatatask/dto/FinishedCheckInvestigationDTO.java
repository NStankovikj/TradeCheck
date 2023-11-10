package com.plidata.complidatatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinishedCheckInvestigationDTO {
    private SubcheckResponseDTO subcheck;
    private SubcheckInvestigationResponseDTO subcheckInvestigation;

}
