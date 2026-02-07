package org.example.task3.service;

import org.example.task3.dto.ReportRowDto;
import org.example.task3.repository.SupplyItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
    private final SupplyItemRepository supplyItemRepository;

    public ReportService(SupplyItemRepository supplyItemRepository) {
        this.supplyItemRepository = supplyItemRepository;
    }

    public List<ReportRowDto> getReport(LocalDate startDate, LocalDate endDate) {
        return supplyItemRepository.getReport(startDate, endDate);
    }
}