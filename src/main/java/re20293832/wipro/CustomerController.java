package re20293832.wipro;

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

	@RestController
	@RequestMapping("/customer")
	public class CustomerController {
		@Autowired
		CustomerService Service;
		
		@Autowired
		CustomerRepository repository; 
		@PostMapping("/add")
		public ResponseEntity<Customer>addCustomer(@RequestBody Customer c) {
			//int x = (int) Math.random();
		//	c.setAccountNo(x);	
			Account acc=new Account();
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
			String account_no= String.format("%06d", number);
			acc.setAccountNo(account_no);
			acc.setAccountType(c.getAccount().getAccountType());
			acc.setBalance(c.getAccount().getBalance());
			c.setAccount(acc);
			
			return new ResponseEntity<Customer>(Service.addCustomer(c), HttpStatus.CREATED);
		
		}
		
		@GetMapping("/getall")
		public ResponseEntity<List<Customer>>getAllCustomers() {
			return new ResponseEntity<List<Customer>>(Service.getAllCustomers(), HttpStatus.OK);
		}
		
		@GetMapping("/customerById/{id}")
		public ResponseEntity<Customer>getcById(@PathVariable int id) {
			if(repository.findById(id).isPresent()) {
				return new ResponseEntity<Customer>(Service.getBycid(id), HttpStatus.OK);
			}
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
			if(Service.deleteCustomer(id)){
				
				return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
		}
		
		
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
