package org.example.task3.repository;

import org.example.task3.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    @Query("""
            select p from Price p
            where p.supplier.id = :supplierId
              and p.product.id = :productId
              and :date between p.dateFrom and p.dateTo
            """)
    Optional<Price> findActivePrice(@Param("supplierId") UUID supplierId,
                                    @Param("productId") UUID productId,
                                    @Param("date") LocalDate date);
}
