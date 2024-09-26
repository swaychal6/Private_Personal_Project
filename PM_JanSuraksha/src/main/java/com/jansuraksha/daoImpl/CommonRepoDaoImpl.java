package com.jansuraksha.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.jansuraksha.bean.CommonBeans;
import com.jansuraksha.dao.CommonRepository;
import com.jansuraksha.dto.GetCustomerDetailDto;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentDetailsResponse;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.entity.JansurakshaTransactions;
import com.jansuraksha.enums.EnrollmentResponseEnum;
import com.jansuraksha.utils.CommonUtility;
import com.jansuraksha.utils.EntityMapper;

import lombok.RequiredArgsConstructor;

@Component
public class CommonRepoDaoImpl<T> extends JanSurakshaDaoImpl<T> implements CommonRepository<T> {

	private T id;

	public CommonRepoDaoImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager.getLogger(CommonRepoDaoImpl.class);

	
	@Retryable(
			 maxAttempts = 100,
			 value = SQLException.class,
			 backoff = @Backoff(delay = 3000)
			 )
	@Override
	public List<EnrollmentRequestDto> getAllTransactionDetails(String bankCode) {
		// TODO Auto-generated method stub
		List<EnrollmentRequestDto> list = null;
		try {
			String sql = "select * from " + id.getClass().getSimpleName();
			list = super.jdbcTemplate.query(sql, new CommonRowMapper());
		} catch (Exception e) {
			logger.error("Geting error while getting the list of the customer for table :" + id.getClass().getName()
					+ " for bankCode:" + bankCode);
		}
		return list;
	}

	
	
	@Retryable(
			 maxAttempts = 100,
			 value = SQLException.class,
			 backoff = @Backoff(delay = 3000)
			 )
	@Override
	public int updateJansurakshaUrnTransactions(JansurakshaTransactions jansurakshaTransactions) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			String sql = "UPDATE " + id.getClass().getSimpleName()
					+ "  set JANSURAKSHA_NUMBER=? ,JANSRAKSHA_STATUS=?  WHERE CUSTOMER_ACC_NUMBER=? AND CIF_CODE=? AND BANK_CODE=?  ";
			i = jdbcTemplate.update(sql,
					new Object[] { jansurakshaTransactions.getJansurakshaUrnNumber(),
							EnrollmentResponseEnum.getMessageByCode(jansurakshaTransactions.getStatus()),
							jansurakshaTransactions.getCustomer_acc_number(), jansurakshaTransactions.getCif_code(),
							jansurakshaTransactions.getBank_code() });

		} catch (Exception e) {
			logger.error("Got error while updating the records for table:" + id.getClass().getName() + " and customer:"
					+ jansurakshaTransactions.getCustomer_acc_number() + ", cifCode="
					+ jansurakshaTransactions.getCif_code() + "and bankCode:" + jansurakshaTransactions.getBank_code());
		}
		return i;
	}


	@Override
	public Object getAccountDetails(GetCustomerDetailDto dto,Class<?> clazz) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql="select * from "+clazz.getSimpleName()+" where customer_acc_number=?  and cif_code=?  ";
		Object queryForObject = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(clazz),new Object[] {dto.getCustomerAcc(),dto.getCifCode()});
		
		return  queryForObject;
		
	}



	@Override
	public int updateAcStatus(GetCustomerDetailDto customerDetailDto) {
		// TODO Auto-generated method stub
		
		//ReasonOF closure column need to add in the database for all the 5 tables
		int i=0;
		Object tableByScheme = CommonUtility.getTableByScheme(customerDetailDto.getScheme());
		String sql="Update "+tableByScheme.getClass().getSimpleName()+" set POLICY_STATUS=? , REASON_OF_CLOSURE=?  where customer_acc_number=? and cif_code=? ";
		
//		i = jdbcTemplate.update(sql,
//				new Object[] { jansurakshaTransactions.getJansurakshaUrnNumber(),
//						EnrollmentResponseEnum.getMessageByCode(jansurakshaTransactions.getStatus()),
//						jansurakshaTransactions.getCustomer_acc_number(), jansurakshaTransactions.getCif_code(),
//						jansurakshaTransactions.getBank_code() });
		return 0;
	}
	
	
	
	

}
