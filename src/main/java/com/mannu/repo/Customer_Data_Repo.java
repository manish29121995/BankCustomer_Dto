package com.mannu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mannu.entity.Customer_Data;

public interface Customer_Data_Repo extends JpaRepository<Customer_Data, Long> {
	
	public Customer_Data findByEmail(String email);

}
