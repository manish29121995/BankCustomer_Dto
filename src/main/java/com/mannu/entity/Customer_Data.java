package com.mannu.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer_Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   accNum;
	private String bankName;
	private String branchName;
	private String ifscCode;
	private String acctype;
	private Double balance;
	private Integer customerId;
	private String customer_Name;
	private String email;
	private String gender;
	
	@CreationTimestamp
	@Column(name = "create_date", updatable = false)
	private LocalDate date;
	
	
}
