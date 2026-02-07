package org.example.task3.dto;

import org.example.task3.entity.ProductCategory;

import java.util.UUID;

public record ProductDto(UUID id, String name, ProductCategory category) {
}
