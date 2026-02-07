package org.example.task3.controller;

import org.example.task3.dto.ReportRowDto;
import org.example.task3.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportRowDto> getReport(@RequestParam("start") LocalDate start,
                                        @RequestParam("end") LocalDate end) {
        return reportService.getReport(start, end);
    }
}
