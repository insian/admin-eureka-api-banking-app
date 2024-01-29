
package com.example.demo.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.MinBalanceException;
import com.example.demo.model.Account;

import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component(value = "accountRepository")
@EnableTransactionManagement
public class AccountRepositoryImpl implements AccountRepository {

	private final SessionFactory sessionFactory;

	@Override
	@Transactional
	public Account createAccount(Account account) { // TODO
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.persist(account);
		session.getTransaction().commit();
		return account;
	}

	@Override
	public List<Account> getAllAccounts() { // TODO Auto-generated
		Session session = sessionFactory.openSession();
		TypedQuery<Account> query = session.createQuery("FROM Account A", Account.class);
		return query.getResultList();
	}

	@Override
	public Account findAccountByAccNo(String accountNumber) throws AccountNotFoundException{
		Session session = sessionFactory.openSession();
		Account account = session.find(Account.class,accountNumber);
		if(account==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		return account;
	}
	
	public Account updateAccount(String accountNumber, Account account) throws AccountNotFoundException {
		Account tempAccount = findAccountByAccNo(accountNumber);
		System.out.println(tempAccount);
		if(tempAccount==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		Session session = sessionFactory.openSession();
		tempAccount.setAccountHolderName(account.getAccountHolderName());
		tempAccount.setAccountHolderAddress(account.getAccountHolderAddress());
		tempAccount.setAccountHolderEmail(account.getAccountHolderEmail());
		tempAccount.setAccountBalance(account.getAccountBalance());
		tempAccount.setMinBalance(account.getMinBalance());
		session.getTransaction().begin();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
	}
	
	@Override
	public void deleteAccount(String accountNumber) throws AccountNotFoundException{
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Account tempAccount = findAccountByAccNo(accountNumber);
		if(tempAccount==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		session.remove(session.merge(tempAccount));
		session.getTransaction().commit();
	}

	@Override
	public Account findAccountByEmail(String email) throws EmailNotFoundException {
		Session session = sessionFactory.openSession();
		TypedQuery<Account> query = session.createQuery("FROM Account a where a.accountHolderEmail=:e", Account.class);
		query.setParameter("e", email);
		if(query.getResultList().size()>0)
		{
			return query.getResultList().get(0);
		}
		else
		{
			throw new EmailNotFoundException(email + " not found in records!");
		}
	}

	/*@Override
	public Account deposit(String accountNumber, Account account) throws AccountNotFoundException {
		Account tempAccount = findAccountByAccNo(accountNumber);
		if(tempAccount==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		Session session = sessionFactory.openSession();
		tempAccount.setAccountBalance(tempAccount.getAccountBalance()+account.getAccountBalance());
		session.getTransaction().begin();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
	}*/
	@Override
	public Account deposit(String accountNumber, Account account) throws AccountNotFoundException {
		Account tempAccount = findAccountByAccNo(accountNumber);
		if(tempAccount==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		tempAccount.setAccountBalance(tempAccount.getAccountBalance()+account.getAccountBalance());
		return updateAccount(accountNumber, tempAccount);
	}
	@Override
	public Account withdraw(String accountNumber, Account account) throws AccountNotFoundException, MinBalanceException {
		Account tempAccount = findAccountByAccNo(accountNumber);
		if(tempAccount==null) {
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		if(tempAccount.getAccountBalance() - account.getAccountBalance() < tempAccount.getMinBalance()) {
			throw new MinBalanceException("Cant withdraw "+ account.getAccountBalance() + " because of min balance criteria");
		}
		Session session = sessionFactory.openSession();
		tempAccount.setAccountBalance(tempAccount.getAccountBalance()-account.getAccountBalance());
		session.getTransaction().begin();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
	}


}