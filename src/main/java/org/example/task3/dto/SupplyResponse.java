package org.example.task3.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SupplyResponse(UUID id, UUID supplierId, LocalDate supplyDate, BigDecimal totalCost) {
}
