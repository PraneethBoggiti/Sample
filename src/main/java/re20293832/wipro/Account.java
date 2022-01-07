package re20293832.wipro;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import javax.persistence.Table;

@Entity
@Table(name="Account_tab")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int accountId;
	public String accountNo;
	public String accountType;

	public double balance;

	@OneToOne(mappedBy="account") 
    private Customer customer;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String account_no) {
		this.accountNo = account_no;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	

	/*	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, String accountNo, String accountType, double balance, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
	}
}
	
	

	
	

