package com.wipro.app.customer.service;

import java.util.List;

import com.wipro.app.customer.common.Account;

public interface AccountService {

    public List<Account> getAllAccounts(); 
	public Account getByaid(int id);

	public boolean deleteAccount(int id);
	public boolean updateAccount(int id, Account a);

    public Account getBalanceOf(double accountNo);

	
	public String trasferFunds(int from, int to, double amount);
	
    
    }