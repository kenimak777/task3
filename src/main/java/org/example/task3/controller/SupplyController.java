package org.example.task3.controller;

import org.example.task3.dto.SupplyCreateRequest;
import org.example.task3.dto.SupplyResponse;
import org.example.task3.service.SupplyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {
    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @PostMapping
    public SupplyResponse createSupply(@RequestBody SupplyCreateRequest request) {
        return supplyService.createSupply(request);
    }
}
