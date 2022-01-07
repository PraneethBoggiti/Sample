package com.wipro.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.app.customer.common.Account;
import com.wipro.app.customer.repository.AccountRepository;
import com.wipro.app.customer.service.AccountService;

@RestController
@RequestMapping("rest/app")
public class AccountController {
	@Autowired
	private AccountService accService;

	@Autowired
	private AccountRepository repository;

	@PostMapping("/add")
	public ResponseEntity<Account> addAccount(@RequestBody Account a) {
		return new ResponseEntity<Account>(repository.save(a), HttpStatus.CREATED);

	}

	@GetMapping("/getallaccountdetails")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<List<Account>>(accService.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping("/accountById/{id}")
	public ResponseEntity<Account> getaccById(@PathVariable int id) {
		if (repository.findById(id).isPresent()) {
			return new ResponseEntity<Account>(accService.getByaid(id), HttpStatus.OK);
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) {
		if (accService.deleteAccount(id)) {

			return new ResponseEntity<String>("Account deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getTransaction")

	public ResponseEntity<String> transferFunds(@PathVariable int from, int to, double amount) {
		return new ResponseEntity<String>(accService.trasferFunds(from, to, amount), HttpStatus.CREATED);

	}

	@GetMapping("/getbalance")
	public ResponseEntity<Account> getBalanceOf(@PathVariable double accountNo) {
		return new ResponseEntity<Account>(accService.getBalanceOf(accountNo), HttpStatus.OK);

	}

}
