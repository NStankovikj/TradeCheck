package com.plidata.complidatatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubcheckInvestigationResponseDTO {
    private Long id;
    private Boolean escalate;
}
