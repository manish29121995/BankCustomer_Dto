package com.mannu.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mannu.dto.Customer_Dto;
import com.mannu.entity.Customer_Data;
import com.mannu.repo.Customer_Data_Repo;

@Service
public class Customer_Service_Impl implements ICustomerService{
	
	@Autowired
	private Customer_Data_Repo repo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Customer_Dto saveCustomer(Customer_Dto dto) {
     Customer_Data data = mapper.map(dto, Customer_Data.class);
       Customer_Data existing_Customre = repo.findByEmail(data.getEmail());
       if(existing_Customre==null) {
    	   
    if(data.getBalance()<=0) {
    throw new IllegalArgumentException("balance should be valid amount ");
   }
      data.setDate(LocalDate.now().minusDays(10));
    	   Customer_Data save = repo.save(data);
    	   return mapper.map(save, Customer_Dto.class);
    	   
       }
       else
    	   throw new IllegalArgumentException("Email is already regestered.");
	}

	@Override
	public Customer_Dto getDataByAccNum(Long accNum) {
		Customer_Data data = repo.findById(accNum).orElseThrow(() -> new IllegalArgumentException("Account number "
				+ "is invalid"));
		
		return mapper.map(data, Customer_Dto.class);
	}

	@Override
	public String depositeBalance(Long accNum, Double amount) {
		if (amount == null || amount <= 0) {
		    throw new IllegalArgumentException("Give Valid Amount");
		}
		Customer_Data customer = repo.findById(accNum).orElseThrow(
     () -> new IllegalArgumentException("Invalid Account Number"));
		
      Double totalBalance = 0.0;
      Double actualBalance = customer.getBalance();
      totalBalance = totalBalance + actualBalance;
      customer.setBalance(totalBalance);
      repo.save(customer);
      
		return amount + " : has credited in your Account : Total Amount : " + totalBalance ;
	}

	@Override
	public String debitBalance(Long accNum, Double amount) {
		if (amount == null || amount <= 0) {
		    throw new IllegalArgumentException("Give Valid Amount");
		}
		Customer_Data customer = repo.findById(accNum).orElseThrow(
     () -> new IllegalArgumentException("Invalid Account Number"));
		
		Double actualAmount = customer.getBalance();
		if(actualAmount<amount)
			throw new IllegalArgumentException("Insufficient balance : " + actualAmount);
		
		Double totalAmount = actualAmount-amount;
		customer.setBalance(totalAmount);
		repo.save(customer);

		
		return amount + " : has been debited from your account remaining balance is : " + totalAmount;
	}

	@Override
	public String getBalance(Long accNum) {
		
		Customer_Data customer = repo.findById(accNum).orElseThrow(
     () -> new IllegalArgumentException("Invalid Account Number"));
		
		Double actualAmount = customer.getBalance();
		String name = customer.getCustomer_Name();
		return name + " :Your Current Balance is : " + actualAmount;
	}

	@Override
	public List<Customer_Dto>  getAllCustomer() {

		List<Customer_Data> all = repo.findAll();
       return   all.stream()
          .map(e->mapper.map(e, Customer_Dto.class))
          .collect(Collectors.toList());
	}

	@Override
	public String deleteAccount(Long accNum) {
		if(repo.existsById(accNum)) {
			repo.deleteById(accNum);
			return "customer account has deleted..";
		}
		else
			throw new IllegalArgumentException("Invalid Account Number..");
	}

	@Override
	public String updateCustomer(Long accNum, Customer_Dto dto) {
		Optional<Customer_Data> customerData = repo.findById(accNum);
		if(!customerData.isPresent())
			throw new IllegalArgumentException(accNum + ": Account number is not valid.");
		
 Customer_Data customer = customerData.get();
 customer.setAcctype(dto.getAcctype());
 customer.setBalance(dto.getBalance());
 customer.setBankName(dto.getBankName());
 customer.setBranchName(dto.getBranchName());
 customer.setCustomer_Name(dto.getCustomer_Name());
 customer.setCustomerId(dto.getCustomerId());
 customer.setEmail(dto.getEmail());
 customer.setGender(dto.getGender());
 customer.setIfscCode(dto.getIfscCode());
 Customer_Data updatedCustomer = repo.save(customer);
	  mapper.map(updatedCustomer, Customer_Dto.class);
		return "Customer record is updated.";

	}

	@Override
	public String patchUpdate(Long accNum, Customer_Dto dto) {
    Optional<Customer_Data> optionalCuatomer = repo.findById(accNum);
  if(!optionalCuatomer.isPresent())
	  throw new IllegalArgumentException(accNum + " : is not a valid Account Number");
  
  Customer_Data customer_Data = optionalCuatomer.get();
  if(dto.getAcctype()!=null)
	  customer_Data.setAccNum(accNum);
  
  if(dto.getBalance()!=null)
	  customer_Data.setBalance(dto.getBalance());
  
  if(dto.getBankName()!=null)
	  customer_Data.setBankName(dto.getBankName());
  
  if(dto.getBranchName()!=null)
	  customer_Data.setBranchName(dto.getBranchName());
  
  if(dto.getCustomer_Name()!=null)
	  customer_Data.setCustomer_Name(dto.getCustomer_Name());
  
  if(dto.getCustomerId()!=null)
	  customer_Data.setCustomerId(dto.getCustomerId());
  
  if(dto.getEmail()!=null)
	  customer_Data.setEmail(dto.getEmail());
  
  if(dto.getGender()!=null)
	  customer_Data.setGender(dto.getGender());
  
  if(dto.getIfscCode()!=null)
	  customer_Data.setIfscCode(dto.getIfscCode());
  Customer_Data updateCustomer = repo.save(customer_Data);
  mapper.map(updateCustomer, Customer_Dto.class);
	return "customer is updated";
	}

}
