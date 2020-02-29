package com.yc.spring.bank;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.spring.bank.action.BankAction;
import com.yc.spring.bank.bean.Account;
import com.yc.spring.bank.biz.AccountBiz;

@RunWith(SpringRunner.class)
@ContextConfiguration("/bank-beans.xml")
public class BankTest {

	@Autowired
	private BankAction bankAction;
	
	@Resource
	private AccountBiz aBiz;
	
	@Test
	public void test1(){
		bankAction.deposit(new Account());
		System.out.println("=================================");
		bankAction.withdraw(new Account());
		System.out.println("=================================");
		bankAction.details(1);
		System.out.println("=================================");
		bankAction.details1(1);
		System.out.println("=================================");
	}
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test2(){
		Date now = jdbcTemplate.queryForObject("select now()", Date.class);
		System.out.println(now);
	}
	
	@Test
	public void test3(){
		Account account =  new Account();
		account.setAccountid(1);
		account.setBalance(5000d);
		aBiz.deposit(account);
	}
	@Test
	public void test4(){
		Account account =  new Account();
		account.setAccountid(1);
		account.setBalance(500000d);
		aBiz.withdraw(account);
	}
	//
}
