package com.plidata.complidatatask.controller;

import com.plidata.complidatatask.dto.InvestigationCommentCreateUpdateDTO;
import com.plidata.complidatatask.dto.InvestigationCommentResponseDTO;
import com.plidata.complidatatask.model.InvestigationComment;
import com.plidata.complidatatask.service.InvestigationCommentService;
import com.plidata.complidatatask.service.SubcheckInvestigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcheckInvestigationController")
public class SubcheckInvestigationController {

    private final InvestigationCommentService investigationCommentService;
    private final SubcheckInvestigationService subcheckInvestigationService;

    @Autowired
    public SubcheckInvestigationController(InvestigationCommentService investigationCommentService, SubcheckInvestigationService subcheckInvestigationService) {
        this.investigationCommentService = investigationCommentService;
        this.subcheckInvestigationService = subcheckInvestigationService;
    }

    // Add a new investigationComment
    @PostMapping("/{subcheckInvestigationId}/investigationComments")
    public ResponseEntity<InvestigationCommentResponseDTO> addInvestigationComment(@PathVariable Long subcheckInvestigationId, @RequestBody InvestigationCommentCreateUpdateDTO dto) {
        return subcheckInvestigationService.findById(subcheckInvestigationId).map(subcheckInvestigation -> {
            InvestigationComment investigationComment = new InvestigationComment();
            investigationComment.setComment(dto.getComment());
            investigationComment.setTimestamp(new Date());
            investigationComment.setSubcheckInvestigation(subcheckInvestigation);
            InvestigationComment savedInvestigationComment = investigationCommentService.save(investigationComment);
            InvestigationCommentResponseDTO investigationCommentResponse = new InvestigationCommentResponseDTO(savedInvestigationComment.getId(), savedInvestigationComment.getComment(), savedInvestigationComment.getTimestamp());
            return ResponseEntity.ok(investigationCommentResponse);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing investigationComment
    @PutMapping("/investigationComments/{investigationCommentId}")
    public ResponseEntity<InvestigationCommentResponseDTO> updateInvestigationComment(@PathVariable Long investigationCommentId, @RequestBody InvestigationCommentCreateUpdateDTO dto) {
        Optional<InvestigationComment> existingInvestigationComment = investigationCommentService.findById(investigationCommentId);

        if (existingInvestigationComment.isPresent()) {
            InvestigationComment updatedInvestigationComment = existingInvestigationComment.get();
            updatedInvestigationComment.setComment(dto.getComment());
            updatedInvestigationComment.setTimestamp(new Date());
            investigationCommentService.save(updatedInvestigationComment);
            InvestigationCommentResponseDTO investigationCommentResponse = new InvestigationCommentResponseDTO(updatedInvestigationComment.getId(), updatedInvestigationComment.getComment(), updatedInvestigationComment.getTimestamp());
            return ResponseEntity.ok(investigationCommentResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}