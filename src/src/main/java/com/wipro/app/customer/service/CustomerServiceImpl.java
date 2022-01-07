package com.wipro.app.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.app.customer.common.Customer;
import com.wipro.app.customer.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository repository;

	public Customer addCustomer(Customer c) {

		return repository.save(c);

	}

	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

	public Customer getBycid(int id) {
		return repository.findById(id).get();
	}

	public boolean deleteCustomer(int id) {
		if (repository.findById(id).isPresent()) {
			repository.delete(getBycid(id));
			return true;
		}
		return false;
	}

	public boolean updateCustomer(int id, Customer c) {
		if (repository.findById(id).isPresent()) {
			Customer cus = repository.findById(id).get();
			cus.setCid(id);
			cus.setCname(c.getCname());
			cus.setCaddress(c.getCaddress());
			// cus.setAccountType(c.getAccountType());
			// cus.setAccount(c.getAccount());
			repository.save(c);
			return true;
		}
		return false;
	}

}
