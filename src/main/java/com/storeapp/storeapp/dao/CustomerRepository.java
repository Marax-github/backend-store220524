package com.storeapp.storeapp.dao;

import com.storeapp.storeapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
