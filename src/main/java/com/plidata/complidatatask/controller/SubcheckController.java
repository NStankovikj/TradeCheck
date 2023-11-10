package com.plidata.complidatatask.controller;

import com.plidata.complidatatask.dto.SubcheckInvestigationCreateUpdateDTO;
import com.plidata.complidatatask.dto.SubcheckInvestigationResponseDTO;
import com.plidata.complidatatask.model.SubcheckInvestigation;
import com.plidata.complidatatask.service.SubcheckInvestigationService;
import com.plidata.complidatatask.service.SubcheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subchecks")
public class SubcheckController {

    private final SubcheckService subcheckService;
    private final SubcheckInvestigationService subcheckInvestigationService;

    @Autowired
    public SubcheckController(SubcheckInvestigationService subcheckInvestigationService, SubcheckService subcheckService) {
        this.subcheckInvestigationService = subcheckInvestigationService;
        this.subcheckService = subcheckService;
    }

    // Add subcheckInvestigation
    @PostMapping("/{subcheckId}/subcheckInvestigations")
    public ResponseEntity<SubcheckInvestigationResponseDTO> createsubcheckInvestigation(@PathVariable Long subcheckId, @RequestBody SubcheckInvestigationCreateUpdateDTO dto) {
        return subcheckService.findById(subcheckId).map(subcheck -> {
            SubcheckInvestigation subcheckInvestigation = new SubcheckInvestigation();
            subcheckInvestigation.setEscalate(dto.getEscalate());
            subcheckInvestigation.setSubcheck(subcheck);
            SubcheckInvestigation savedSubcheckInvestigation = subcheckInvestigationService.save(subcheckInvestigation);

            SubcheckInvestigationResponseDTO responseDTO = new SubcheckInvestigationResponseDTO(
                    savedSubcheckInvestigation.getId(),
                    savedSubcheckInvestigation.getEscalate()
            );
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

