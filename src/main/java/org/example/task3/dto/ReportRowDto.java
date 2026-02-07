package org.example.task3.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ReportRowDto(
        UUID supplierId,
        String supplierName,
        UUID productId,
        String productName,
        BigDecimal totalWeight,
        BigDecimal totalCost
) {
}
