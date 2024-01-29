package com.example.demo.repo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.MinBalanceException;
import com.example.demo.model.Account;
@Component(value = "accountRepository")
public interface AccountRepository {
	
	Account createAccount(Account account);
	List<Account> getAllAccounts();
	Account findAccountByAccNo(String accountNumber) throws AccountNotFoundException;
	Account updateAccount(String accountNumber, Account account) throws AccountNotFoundException;
	void deleteAccount(String accountNumber) throws AccountNotFoundException;
	Account findAccountByEmail(String email) throws EmailNotFoundException;
	Account deposit(String accountNumber, Account account) throws AccountNotFoundException;
	Account withdraw(String accountNumber, Account account) throws AccountNotFoundException,MinBalanceException;
}