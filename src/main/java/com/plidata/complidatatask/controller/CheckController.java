package com.plidata.complidatatask.controller;

import com.plidata.complidatatask.dto.CheckInvestigationCreateUpdateDTO;
import com.plidata.complidatatask.dto.CheckInvestigationResponseDTO;
import com.plidata.complidatatask.dto.FinishedCheckInvestigationDTO;
import com.plidata.complidatatask.dto.SubcheckCreateUpdateDTO;
import com.plidata.complidatatask.dto.SubcheckInvestigationResponseDTO;
import com.plidata.complidatatask.dto.SubcheckResponseDTO;
import com.plidata.complidatatask.model.CheckInvestigation;
import com.plidata.complidatatask.model.Subcheck;
import com.plidata.complidatatask.service.CheckInvestigationService;
import com.plidata.complidatatask.service.CheckService;
import com.plidata.complidatatask.service.SubcheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/checks")
public class CheckController {

    private final CheckService checkService;
    private final SubcheckService subcheckService;
    private final CheckInvestigationService checkInvestigationService;

    @Autowired
    public CheckController(CheckInvestigationService checkInvestigationService, CheckService checkService, SubcheckService subcheckService) {
        this.checkInvestigationService = checkInvestigationService;
        this.checkService = checkService;
        this.subcheckService = subcheckService;
    }

    // Start a new checkInvestigation
    @PostMapping("/{checkId}/checkInvestigations")
    public ResponseEntity<CheckInvestigationResponseDTO> startCheckInvestigation(@PathVariable Long checkId, @RequestBody CheckInvestigationCreateUpdateDTO dto) {
        return checkService.findById(checkId).map(check -> {
            CheckInvestigation checkInvestigation = new CheckInvestigation();
            checkInvestigation.setInvestigator(dto.getInvestigator());
            checkInvestigation.setCheck(check);
            checkInvestigation.setTimestampStart(new Date());

            CheckInvestigation savedCheckInvestigation = checkInvestigationService.save(checkInvestigation);

            CheckInvestigationResponseDTO responseDTO = new CheckInvestigationResponseDTO(
                    savedCheckInvestigation.getId(),
                    savedCheckInvestigation.getInvestigator(),
                    savedCheckInvestigation.getTimestampStart(),
                    savedCheckInvestigation.getTimestampEnd()
            );
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Finish check Investigation
    @GetMapping("/finishCheckInvestigations/{checkInvestigationId}")
    public ResponseEntity<List<FinishedCheckInvestigationDTO>> finishCheckInvestigations(@PathVariable Long checkInvestigationId) {
        return checkInvestigationService.findById(checkInvestigationId).map(checkInvestigation -> {
            List<Subcheck> subchecks = subcheckService.findAllByCheck(checkInvestigation.getCheck());

            List<FinishedCheckInvestigationDTO> subcheckInvestigationDTOs = subchecks.stream()
                    .flatMap(subcheck -> subcheck.getSubcheckInvestigations().stream())
                    .map(subcheckInvestigation -> new FinishedCheckInvestigationDTO(
                            new SubcheckResponseDTO(subcheckInvestigation.getSubcheck().getId(), subcheckInvestigation.getSubcheck().getName()),
                            new SubcheckInvestigationResponseDTO(subcheckInvestigation.getId(), subcheckInvestigation.getEscalate())))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(subcheckInvestigationDTOs);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{checkId}}/getAllCommentsForCheck")
    public ResponseEntity<List<FinishedCheckInvestigationDTO>> getAllCommentsForCheck(@PathVariable Long checkId) {
        return checkService.findById(checkId).map(check -> {
            List<Subcheck> subchecks = subcheckService.findAllByCheck(check);

            List<FinishedCheckInvestigationDTO> subcheckInvestigationDTOs = subchecks.stream()
                    .flatMap(subcheck -> subcheck.getSubcheckInvestigations().stream())
                    .map(subcheckInvestigation -> new FinishedCheckInvestigationDTO(
                            new SubcheckResponseDTO(subcheckInvestigation.getSubcheck().getId(), subcheckInvestigation.getSubcheck().getName()),
                            new SubcheckInvestigationResponseDTO(subcheckInvestigation.getId(), subcheckInvestigation.getEscalate())))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(subcheckInvestigationDTOs);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new subcheck to a check
    @PostMapping("/{checkId}/subchecks")
    public ResponseEntity<SubcheckResponseDTO> addSubcheckToCheck(@PathVariable Long checkId, @RequestBody SubcheckCreateUpdateDTO dto) {
        return checkService.findById(checkId).map(check -> {
            Subcheck subcheck = new Subcheck();
            subcheck.setName(dto.getName());
            subcheck.setCheck(check);
            Subcheck savedSubcheck = subcheckService.save(subcheck);
            SubcheckResponseDTO subcheckResponse = new SubcheckResponseDTO(savedSubcheck.getId(), savedSubcheck.getName());
            return ResponseEntity.ok(subcheckResponse);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remove a subcheck from check
    @DeleteMapping("/{checkId}/subchecks/{subcheckId}")
    public ResponseEntity<Object> removeSubcheckFromCheck(@PathVariable Long checkId, @PathVariable Long subcheckId) {
        return checkService.findById(checkId).flatMap(check ->
                subcheckService.findById(subcheckId).map(subcheck -> {
                    if (subcheck.getCheck().equals(check)) {
                        subcheckService.delete(subcheckId);
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.notFound().build();
                })
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

