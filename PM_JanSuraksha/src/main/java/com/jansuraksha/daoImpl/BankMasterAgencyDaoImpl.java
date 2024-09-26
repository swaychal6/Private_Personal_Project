package com.jansuraksha.daoImpl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jansuraksha.dao.BankMasterRepository;
import com.jansuraksha.entity.BankMasterAgency;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public  class BankMasterAgencyDaoImpl implements BankMasterRepository<BankMasterAgency> {

	private final JdbcTemplate jdbcTemplate;


	@Override
	public List<BankMasterAgency> getAllBankDetails() {
		List<BankMasterAgency> bankList = null;
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM BANK_MASTER_AGENCY";
		bankList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BankMasterAgency.class));
		return bankList;
	}



}
