package org.example.task3.repository;

import org.example.task3.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SupplyRepository extends JpaRepository<Supply, UUID> {
}
