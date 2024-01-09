package com.corebanking.system.repository;

import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    @Override
    Optional<Customer> findById(Long aLong);

    Optional<Customer> findByEmailOrId(String email, Long Id);
    Optional<Customer> findByCnicOrEmailOrUserName(String cnic, String email, String userName);
    Optional<Customer> findByCnicAndId(String cnic, Long id);
    Optional<Customer> findByCnic(String cnic);
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByCnicOrEmail(String cnic, String email);

}