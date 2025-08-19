package com.mannu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mannu.dto.Customer_Dto;
import com.mannu.entity.Customer_Data;

public interface Customer_Data_Repo extends JpaRepository<Customer_Data, Long> {
	
	public Customer_Data findByEmail(String email);
	public List<Customer_Data> findByActiveSw(String status);

}
