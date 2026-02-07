package org.example.task3.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SupplyCreateRequest(UUID supplierId, LocalDate supplyDate, List<SupplyItemRequest> items) {
}
