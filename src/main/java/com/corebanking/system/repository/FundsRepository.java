package com.corebanking.system.repository;

import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.FundsTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundsRepository extends JpaRepository<FundsTransfer, Long> {
    List<FundsTransfer> findBySenderAccount(Account account);
}
