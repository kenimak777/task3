package org.example.task3.controller;

import org.example.task3.dto.ProductDto;
import org.example.task3.dto.SupplierDto;
import org.example.task3.repository.ProductRepository;
import org.example.task3.repository.SupplierRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReferenceController {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public ReferenceController(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/suppliers")
    public List<SupplierDto> getSuppliers() {
        return supplierRepository.findAll().stream()
                .map(s -> new SupplierDto(s.getId(), s.getName()))
                .toList();
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getCategory()))
                .toList();
    }
}
