package com.wipro.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.app.customer.common.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
