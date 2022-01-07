package com.wipro.app.customer.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.app.customer.common.Account;
import com.wipro.app.customer.common.Customer;
import com.wipro.app.customer.repository.CustomerRepository;
import com.wipro.app.customer.service.CustomerService;

@RestController
@RequestMapping("rest/app")
public class CustomerController {

	@Autowired
	private CustomerService Service;
	@Autowired
	private CustomerRepository repository;
	

	/*
	 * @PostMapping("/save") public ResponseEntity<Customer>addAccount(@RequestBody
	 * Customer a) { return new ResponseEntity<Customer>(repository.save(a),
	 * HttpStatus.CREATED);
	 * 
	 * }
	 */

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) { 
		Account acc = new Account();
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String account_no = String.format("%06d", number);
		acc.setAccountNo(account_no);
		acc.setAccountType(c.getAccount().getAccountType());
		acc.setBalance(c.getAccount().getBalance());
		c.setAccount(acc);

		return new ResponseEntity<Customer>(Service.addCustomer(c), HttpStatus.CREATED);

	}
 
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		try {
			List<Customer> list = repository.findAll();
			
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/customerById/{id}")
	public ResponseEntity<Customer>getcById(@PathVariable int id) {
		if(repository.findById(id).isPresent()) {
			return new ResponseEntity<Customer>(Service.getBycid(id), HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	
	  @DeleteMapping("/delete/{id}") public ResponseEntity<String>
	  deleteCustomer(@PathVariable int id) { if(Service.deleteCustomer(id)){
	 
	  return new ResponseEntity<String>("Customer deleted successfully",
	  HttpStatus.OK); } return new ResponseEntity<String>("Customer not found",
	  HttpStatus.NOT_FOUND); }
	 
	
@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer c) {
		if(repository.findById(id).isPresent()) {
			Customer cus = repository.findById(id).get();
			cus.setCname(c.cname);
			cus.setCaddress(c.caddress);
			//cus.setAccountType(c.accountType);
         //   cus.setAccount(c.account);
			return new ResponseEntity<Customer>(Service.addCustomer(c), HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

}
