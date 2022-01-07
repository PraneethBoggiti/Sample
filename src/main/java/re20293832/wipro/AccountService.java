package re20293832.wipro;

import java.util.List;

import org.springframework.http.HttpStatus;

public interface AccountService {
	//public Account addAccount(Account a);

    public List<Account> getAllAccounts(); 
	public Account getByaid(int id);

	public boolean deleteAccount(int id);
	public boolean updateAccount(int id, Account a);

    public Account getBalanceOf(double accountNo);

	
	public String trasferFunds(int from, int to, double amount);
	
    
    }