package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.MinBalanceException;
import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component(value = "accountService")
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.createAccount(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.getAllAccounts();
	}

	@Override
	public Account findAccountByAccNo(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.findAccountByAccNo(accountNumber);
	}

	@Override
	public Account updateAccount(String accountNumber, Account account) throws AccountNotFoundException{
		// TODO Auto-generated method stub
		return accountRepository.updateAccount(accountNumber, account);
	}

	@Override
	public void deleteAccount(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		accountRepository.deleteAccount(accountNumber);
	}

	@Override
	public Account findAccountByEmail(String email) throws EmailNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.findAccountByEmail(email);
	}

	@Override
	public Account deposit(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.deposit(accountNumber, account);
	}

	@Override
	public Account withdraw(String accountNumber, Account account) throws AccountNotFoundException, MinBalanceException {
		// TODO Auto-generated method stub
		return accountRepository.withdraw(accountNumber, account);
	}

	

}