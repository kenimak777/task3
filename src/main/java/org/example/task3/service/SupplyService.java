package org.example.task3.service;

import org.example.task3.dto.SupplyCreateRequest;
import org.example.task3.dto.SupplyItemRequest;
import org.example.task3.dto.SupplyResponse;
import org.example.task3.entity.Price;
import org.example.task3.entity.Product;
import org.example.task3.entity.Supplier;
import org.example.task3.entity.Supply;
import org.example.task3.entity.SupplyItem;
import org.example.task3.repository.PriceRepository;
import org.example.task3.repository.ProductRepository;
import org.example.task3.repository.SupplierRepository;
import org.example.task3.repository.SupplyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final SupplyRepository supplyRepository;

    public SupplyService(SupplierRepository supplierRepository,
                         ProductRepository productRepository,
                         PriceRepository priceRepository,
                         SupplyRepository supplyRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.supplyRepository = supplyRepository;
    }

    @Transactional
    public SupplyResponse createSupply(SupplyCreateRequest request) {
        if (request == null || request.items() == null || request.items().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supply items are required");
        }
        if (request.supplierId() == null || request.supplyDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier and supplyDate are required");
        }

        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));

        Supply supply = new Supply();
        supply.setSupplier(supplier);
        supply.setSupplyDate(request.supplyDate());

        List<SupplyItem> items = new ArrayList<>();
        for (SupplyItemRequest itemRequest : request.items()) {
            if (itemRequest == null || itemRequest.productId() == null || itemRequest.weight() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each item must have productId and weight");
            }
            if (itemRequest.weight().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight must be greater than zero");
            }
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            Price price = priceRepository.findActivePrice(supplier.getId(), product.getId(), request.supplyDate())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Price not found for supplier/product/date"));

            SupplyItem item = new SupplyItem();
            item.setSupply(supply);
            item.setProduct(product);
            item.setWeight(itemRequest.weight());
            item.setPricePerKg(price.getPricePerKg());
            item.setCost(price.getPricePerKg().multiply(itemRequest.weight()));

            items.add(item);
        }

        supply.setItems(items);
        Supply saved = supplyRepository.save(supply);

        BigDecimal totalCost = items.stream()
                .map(SupplyItem::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SupplyResponse(saved.getId(), supplier.getId(), saved.getSupplyDate(), totalCost);
    }
}