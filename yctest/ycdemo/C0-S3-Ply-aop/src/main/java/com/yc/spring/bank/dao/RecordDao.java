package com.yc.spring.bank.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yc.spring.bank.bean.Record;
import com.yc.spring.common.dao.BaseDao;

@Service
public class RecordDao extends BaseDao<Record> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Record e) {
		jdbcTemplate.update(
				"insert into oprecord values(null,?,?,?)",
			e.getAccountId(),e.getMoney(),new Timestamp(System.currentTimeMillis()));
	}
	@Override
	public void delete(Record e) {
		// TODO Auto-generated method stub
		super.delete(e);
	}

	@Override
	public Record selectById(Object id) {
		// TODO Auto-generated method stub
		return super.selectById(id);
	}

	@Override
	public List<Record> selectByObject(Record e) {
		// TODO Auto-generated method stub
		return super.selectByObject(e);
	}

	
}
