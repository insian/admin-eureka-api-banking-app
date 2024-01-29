package com.example.demo.model;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_table")
public class Account {
	@Id
	@NotBlank(message = "account number can not be blank")
	@Column(name = "account_number")
	private String accountNumber;
	
	@NotBlank(message = "account holder name can not be blank")
	@Column(name = "account_holder_name")
	@Length(min = 5, max = 16, message = "account holder name is wrong")
	private String accountHolderName;
	
	@Column(name = "account_holder_address")
	private String accountHolderAddress;
	
	@Column(name = "account_holder_email",unique = true)
	@Email(message = "invalid email",regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String accountHolderEmail;
	
	@Column(name = "account_balance")
	@Min(value = 1000, message = "minimum amount required")
	@Max(value = 100000, message = "maximum amount required")
	private double accountBalance;
	
	@Column(name = "min_balance")
	@Min(value = 0 , message = "minimum balance should be greater than zero")
	private double minBalance;
	public Account(String accountHolderName, String accountHolderAddress, String accountHolderEmail, double accountBalance, double minBalanace) {
		super();
		this.accountHolderName = accountHolderName;
		this.accountHolderAddress = accountHolderAddress;
		this.accountHolderEmail = accountHolderEmail;
		this.accountBalance = accountBalance;
		this.minBalance = minBalanace;
	}
	
	public Account(double accountBalance) {
		super();
		this.accountBalance = accountBalance;
	}
	
	
	
}