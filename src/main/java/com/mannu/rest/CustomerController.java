package com.mannu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mannu.dto.Customer_Dto;
import com.mannu.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;
	
	@PostMapping("/add")
	public ResponseEntity<?>  saveCustomer(@RequestBody Customer_Dto dto) {
		System.out.println("CustomerController.saveCustomer()");
		try {
		Customer_Dto saveCustomer = service.saveCustomer(dto);
		return new ResponseEntity<Customer_Dto>(saveCustomer, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest()
					.body( e.getMessage());
		}
		
	}
	
	@GetMapping("/details/{accNum}")
	  public ResponseEntity<?> getCustomerDetailsByAccNum(@PathVariable Long accNum) {
		  try {
		  Customer_Dto customer_Dto = service.getDataByAccNum(accNum);
		   return new ResponseEntity<Customer_Dto>(customer_Dto, HttpStatus.OK);
		  }
		  catch (IllegalArgumentException e) {
			    return ResponseEntity
			            .badRequest()
			            .body("Error : " +  e.getMessage());
			}
	  }
	
	@GetMapping("/deposite/{accNum}/{amount}")
	  public ResponseEntity<?> depositeBalance(@PathVariable Long accNum,@PathVariable Double amount){
		 
		  try {
		  String balance = service.depositeBalance(accNum, amount);
		  return new ResponseEntity<String>(balance, HttpStatus.OK);
	  }catch (Exception e) {
		
		  return ResponseEntity
				  .badRequest()
				  .body(e.getMessage());
	}
	  }
	
	@GetMapping("/debit/{accNum}/{amount}")
 public ResponseEntity<?> debitAmount(@PathVariable Long accNum,@PathVariable Double amount){
	 try {
		String message = service.debitBalance(accNum, amount);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	} catch (Exception e) {

		return ResponseEntity
				.badRequest()
				.body(e.getMessage());
	}
 }
	
	@GetMapping("/balance/{accNum}")
	public ResponseEntity<?> getBalance(@PathVariable Long accNum){
		try {
	String balance = service.getBalance(accNum);
	 return new ResponseEntity<String>(balance, HttpStatus.OK);
  }
	catch (IllegalArgumentException e) {
	  return ResponseEntity
	  .badRequest()
	.body("Error : " +  e.getMessage());
				}
	}
	
	@GetMapping("/all")
	public List<Customer_Dto> getAllCustomer(){
		List<Customer_Dto> allCustomer = service.getAllCustomer();
		return allCustomer;
				
	}
	
	@DeleteMapping("/delete/{accNum}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long accNum) {
		try {
		String deleteAccount = service.deleteAccount(accNum);
		return new ResponseEntity<String>(deleteAccount,HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity
					.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{accNum}")
	public ResponseEntity<?> updateCUstomer(@PathVariable Long accNum,@RequestBody Customer_Dto dto){
		try {
			String updateCustomer = service.updateCustomer(accNum, dto);
			return new ResponseEntity<String>(updateCustomer,HttpStatus.OK);
		}catch (IllegalArgumentException e) {
        
			return ResponseEntity
					.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PatchMapping("/patch/{accNum}")
	public ResponseEntity<?> patchUpdate(@PathVariable Long accNum,	@RequestBody Customer_Dto dto){
		
		try {
   String patchUpdate = service.patchUpdate(accNum, dto);
	return new ResponseEntity<String>(patchUpdate,HttpStatus.OK);		
		}catch (IllegalArgumentException e) {
			
   return ResponseEntity
		.badRequest()
		.body(e.getMessage());
		}
	}
}
