package com.wipro.app.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.app.customer.common.Account;
import com.wipro.app.customer.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository repository;

	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	public Account getByaid(int id) {
		return repository.findById(id).get();
	}

	public boolean deleteAccount(int id) {
		if (repository.findById(id).isPresent()) {
			repository.delete(getByaid(id));
			return true;
		}
		return false;
	}

	public boolean updateAccount(int id, Account a) {
		if (repository.findById(id).isPresent()) {
			Account ac = repository.findById(id).get();
			ac.setAccountId(id);
			ac.setAccountNo(a.getAccountNo());
			ac.setAccountType(a.getAccountType());
			ac.setBalance(a.getBalance());
			repository.save(a);
			return true;
		}
		return false;
	}

	public Account getBalanceOf(double accountNo) {

		return (Account) repository.findAll();
	}

	@Override
	public String trasferFunds(int from, int to, double amount) {
		if (repository.findById(from).isPresent() && repository.findById(to).isPresent()) {
			if (repository.findById(from).get().getBalance() < amount) {
				return "INSUFFICIENT FUND";
			}
			Account account1 = repository.findById(from).get();
			account1.setBalance(account1.getBalance() - amount);
			repository.save(account1);
			Account account2 = repository.findById(to).get();
			account2.setBalance(account2.getBalance() + amount);
			repository.save(account2);
			return "SUCCESS";
		}
		return "ID MISMATCH";
	}
}
