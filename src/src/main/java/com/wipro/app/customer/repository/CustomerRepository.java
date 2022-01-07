package com.wipro.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.app.customer.common.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
