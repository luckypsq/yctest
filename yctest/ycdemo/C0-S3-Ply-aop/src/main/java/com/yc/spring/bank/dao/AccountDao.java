package com.yc.spring.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.yc.spring.bank.bean.Account;
import com.yc.spring.common.dao.BaseDao;

@Service
public class AccountDao extends BaseDao<Account> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Account e) {
		// TODO Auto-generated method stub
		super.insert(e);
	}

	@Override
	public void update(Account e) {
		jdbcTemplate.update(
				"update account set balance = balance + ? where accountid = ?",
				e.getBalance(),e.getAccountid());
	}

	public void update1(Account e) {
		jdbcTemplate.update(
				"update account set balance = balance - ? where accountid = ?",
				e.getBalance(),e.getAccountid());
	}
	@Override
	public void delete(Account e) {
		// TODO Auto-generated method stub
		super.delete(e);
	}

	@Override
	public Account selectById(Object id) {
		String sql="select * from account where accountid = ?";
		RowMapper<Account> rowMapper=new BeanPropertyRowMapper<>(Account.class);
		Account account =  jdbcTemplate.queryForObject(sql, rowMapper,id);
		return account;
	}

	@Override
	public List<Account> selectByObject(Account e) {
		// TODO Auto-generated method stub
		return super.selectByObject(e);
	}
	
}
