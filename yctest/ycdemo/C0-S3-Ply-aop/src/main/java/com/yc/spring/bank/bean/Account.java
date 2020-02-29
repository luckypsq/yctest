package com.yc.spring.bank.bean;

import org.springframework.stereotype.Component;

/**
 * 银行账户
 * @author Administrator
 *
 */
@Component("account") // 组件 表示该类是spring管理的   bean   @Bean
public class Account {

	private Integer accountid; // 主键
	private String name; // 姓名
	private Double balance; // 余额

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", name=" + name + ", balance=" + balance + "]";
	}


	/**
	 * 这是一个不完整的单例模式，少了私有的构造方法
	 */
	private static Account singleAccount;

	public static Account getInstance() {
		if (singleAccount == null) {
			singleAccount = new Account();
		}
		return singleAccount;
	}

	/**
	 * count 是创建对象的计数器
	 */
	private static int count;
	public static Account getInstance1() {
		return count++ < 3 ? new Account() : getInstance();
	}

}
