package com.wipro.app.customer.service;

import java.util.List;

import com.wipro.app.customer.common.Customer;

public interface CustomerService {
public Customer addCustomer(Customer c);

public List<Customer> getAllCustomers();
public Customer getBycid(int id);

public boolean deleteCustomer(int id);
public boolean updateCustomer(int id, Customer c);

}
