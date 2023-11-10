package com.plidata.complidatatask.controller;

import com.plidata.complidatatask.dto.CheckCreateUpdateDTO;
import com.plidata.complidatatask.dto.CheckResponseDTO;
import com.plidata.complidatatask.dto.TradeCreateUpdateDTO;
import com.plidata.complidatatask.dto.TradeResponseDTO;
import com.plidata.complidatatask.model.Check;
import com.plidata.complidatatask.model.Trade;
import com.plidata.complidatatask.service.CheckService;
import com.plidata.complidatatask.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;
    private final CheckService checkService;

    @Autowired
    public TradeController(TradeService tradeService, CheckService checkService) {
        this.tradeService = tradeService;
        this.checkService = checkService;
    }

    // Add a new trade
    @PostMapping
    public ResponseEntity<TradeResponseDTO> createTrade(@RequestBody TradeCreateUpdateDTO tradeDto) {
        Trade trade = new Trade();
        trade.setName(tradeDto.getName());
        Trade savedTrade = tradeService.save(trade);
        TradeResponseDTO tradeResponse = new TradeResponseDTO(savedTrade.getId(), savedTrade.getName());
        return ResponseEntity.ok(tradeResponse);
    }

    // Add a new check to a trade
    @PostMapping("/{tradeId}/checks")
    public ResponseEntity<CheckResponseDTO> addCheckToTrade(@PathVariable Long tradeId, @RequestBody CheckCreateUpdateDTO dto) {
        return tradeService.findById(tradeId).map(trade -> {
            Check check = new Check();
            check.setName(dto.getName());
            check.setTrade(trade);
            Check savedCheck = checkService.save(check);
            CheckResponseDTO checkResponse = new CheckResponseDTO(savedCheck.getId(), savedCheck.getName());
            return ResponseEntity.ok(checkResponse);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remove an existing check from a trade
    @DeleteMapping("/{tradeId}/checks/{checkId}")
    public ResponseEntity<Object> removeCheckFromTrade(@PathVariable Long tradeId, @PathVariable Long checkId) {
        return tradeService.findById(tradeId).flatMap(trade ->
                checkService.findById(checkId).map(check -> {
                    if (check.getTrade().equals(trade)) {
                        checkService.delete(checkId);
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.notFound().build();
                })
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all checks for a trade
    @GetMapping("/{tradeId}/checks")
    public ResponseEntity<List<Check>> getChecksForTrade(@PathVariable Long tradeId) {
        return tradeService.findById(tradeId).map(trade -> {
            List<Check> checks = checkService.findAllByTrade(trade);
            return ResponseEntity.ok(checks);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
