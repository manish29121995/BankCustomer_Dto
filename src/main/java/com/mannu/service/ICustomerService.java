package com.mannu.service;


import java.util.List;

import com.mannu.dto.Customer_Dto;

public interface ICustomerService {

	
public Customer_Dto saveCustomer(Customer_Dto dto);

public Customer_Dto getDataByAccNum(Long accNum);

public String depositeBalance(Long accNum, Double amount);

public String debitBalance(Long accNum,Double amount);

public String getBalance(Long balance);

public List<Customer_Dto>  getAllCustomer();

public String deleteAccount(Long accNum);

public String updateCustomer(Long accNum, Customer_Dto dto);

public String patchUpdate(Long accNum,Customer_Dto dto);

}
