package com.yc.spring.bank.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.spring.bank.bean.Account;
import com.yc.spring.bank.bean.Record;
import com.yc.spring.bank.dao.AccountDao;
import com.yc.spring.bank.dao.RecordDao;

@Service
public class AccountBiz {
	
	@Autowired
	private AccountDao aDao;
	@Autowired
	private RecordDao rDao;
	
	/**
	 * 	存款业务
	 */
	public void deposit(Account account) {
		System.out.println("模拟存款业务！");
		aDao.update(account);
		Record record = new Record();
		record.setAccountId(account.getAccountid());
		record.setMoney(account.getBalance());
		rDao.insert(record);
	}
	
	/**
	 * 	取款业务
	 */
	public void withdraw(Account account) {
		System.out.println("模拟取款业务！");
		Account account2 = aDao.selectById(account.getAccountid());
		if(account2.getBalance() < account.getBalance()){
			System.out.println("当前余额："+account2.getBalance()+ "。余额不足,请充值！！！");
			return;
		}
		aDao.update1(account);
		Record record = new Record();
		record.setAccountId(account.getAccountid());
		record.setMoney(account.getBalance());
		rDao.insert(record);
	}
	
	/**
	 * 	转账业务
	 */
	public void transfer(Account account1, Account account2) {
		System.out.println("模拟转账业务！");
		aDao.update(account1);
		aDao.update(account2);
		rDao.insert(new Record());
		rDao.insert(new Record());
	}
	
	/**
	 * 查询明细
	 */
	public List<Record> details() {
		return new ArrayList<Record>();
	}
	public List<Record> details1() {
		return new ArrayList<Record>();
	}
}
