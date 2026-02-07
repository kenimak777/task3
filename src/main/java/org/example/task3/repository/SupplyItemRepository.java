package org.example.task3.repository;

import org.example.task3.dto.ReportRowDto;
import org.example.task3.entity.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SupplyItemRepository extends JpaRepository<SupplyItem, UUID> {
    @Query("""
            select new org.example.task3.dto.ReportRowDto(
                s.supplier.id,
                s.supplier.name,
                p.id,
                p.name,
                sum(i.weight),
                sum(i.cost)
            )
            from SupplyItem i
            join i.supply s
            join i.product p
            where s.supplyDate between :startDate and :endDate
            group by s.supplier.id, s.supplier.name, p.id, p.name
            order by s.supplier.name, p.name
            """)
    List<ReportRowDto> getReport(@Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);
}
