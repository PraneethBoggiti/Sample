package com.wipro.app.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.app.customer.common.Account;
import com.wipro.app.customer.common.Customer;
import com.wipro.app.customer.repository.CustomerRepository;



@SpringBootTest(classes = CustomerApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CustomerApplicationTests {
	@Autowired
	private CustomerRepository crepo;

	 @Test
	@Order(1)
	public void createTest() {
	Customer c = new Customer();
	Account acc = new Account();
	c.setCname("Bhasker");
	c.setCaddress("Hyd");
	Random rnd = new Random();
	int number = rnd.nextInt(999999);
	String account_no = String.format("%06d", number);
	acc.setAccountNo(account_no);
	acc.setAccountType("Savings");
	acc.setBalance(10000.00);
	c.setAccount(acc);

	 crepo.save(c);
	assertNotNull(crepo.findById(1).get());

	 }

	 @Test
	@Order(2)
	public void testForAll() {
	List<Customer>list = crepo.findAll();
	assertThat(list).size().isGreaterThan(0);
	}
	@Test
	@Order(3)
	public void testForOne() {
	Customer c = crepo.findById(1).get();
	assertEquals(10000.0, c.getAccount().getBalance());
	}
	@Test
	@Order(4)
	public void testForUpdate() {
	Customer customer = crepo.findById(1).get();
	Account acc = new Account();
	acc.setBalance(25000.0);
	customer.setAccount(acc);
	crepo.save(customer);
	assertNotEquals(20000,crepo.findById(1).get().getAccount().getBalance());
	}
	@Test
	@Order(5)
	public void testForDelete() {
	crepo.deleteById(1);
	assertThat(crepo.existsById(2)).isFalse();
	}
	}



