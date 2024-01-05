package com.corebanking.system.repository;

import com.corebanking.system.model.entity.FundsTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsRepository extends JpaRepository<FundsTransfer, Long> {
}
