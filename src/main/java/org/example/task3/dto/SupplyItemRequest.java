package org.example.task3.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record SupplyItemRequest(UUID productId, BigDecimal weight) {
}
