package re20293832.wipro;

import java.util.List;

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
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accService;
	
	@Autowired
	AccountRepository repository;
//	@PostMapping("/add")
//	public ResponseEntity<Account>addAccount(@RequestBody Account a) {
//		return new ResponseEntity<Account>(accService.addAccount(a), HttpStatus.CREATED);
		
//	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Account>>getAllAccounts() {
		return new ResponseEntity<List<Account>>(accService.getAllAccounts(), HttpStatus.OK);
	}
	
	@GetMapping("/accountById/{id}")
	public ResponseEntity<Account>getaccById(@PathVariable int id) {
		if(repository.findById(id).isPresent()) {
			return new ResponseEntity<Account>(accService.getByaid(id), HttpStatus.OK);
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) {
		if(accService.deleteAccount(id)){
			
			return new ResponseEntity<String>("Account deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getTransaction")
	
		public ResponseEntity<String> transferFunds(@PathVariable int from, int to, double amount) {
		return new ResponseEntity<String>(accService.trasferFunds(from, to, amount), HttpStatus.CREATED);
		
		
	}

	@GetMapping("/getbalance")
	public ResponseEntity<Account>getBalanceOf(@PathVariable double accountNo) {
		return new ResponseEntity<Account>(accService.getBalanceOf(accountNo), HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
/*	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account a) {
		if(repository.findById(id).isPresent()) {
			Account ac=repository.findById(id).get();
			ac.setAccountType(a.accountType);
			ac.setBalance(a.balance);
			return new ResponseEntity<Account>(accService.addAccount(a), HttpStatus.OK);
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	*/	
		
}