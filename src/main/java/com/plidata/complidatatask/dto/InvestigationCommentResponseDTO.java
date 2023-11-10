package com.plidata.complidatatask.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class InvestigationCommentResponseDTO {
    private Long id;
    private String comment;
    private Date timestamp;

}
