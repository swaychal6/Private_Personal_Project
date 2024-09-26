package com.jansuraksha;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jansuraksha.config.ApplicationConfig;
import com.jansuraksha.daoImpl.BankMasterAgencyDaoImpl;
import com.jansuraksha.dto.updateAcstatusDto.UpdateAcStatusFrontendReq;
import com.jansuraksha.entity.BankMasterAgency;
import com.jansuraksha.utils.CommonUtility;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
@RequiredArgsConstructor
public class PmJanSurakshaApplication implements CommandLineRunner {

	private static final Logger logger=LogManager.getLogger(PmJanSurakshaApplication.class);
	
	private final BankMasterAgencyDaoImpl bankMasterAgencyDaoImpl;

	public static List<BankMasterAgency> bankDetails = null;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PmJanSurakshaApplication.class, args);
		logger.info(bankDetails);
		String string = PmJanSurakshaApplication
		.bankDetails
		.stream()
		.filter(t->"61".equals(t.getBANK_CODE()))
		.map(t ->(String) t.getBANK_NAME()).findFirst().get();
		
		logger.info(string+":"+ApplicationConfig.getProperty("61"));
		
		logger.info(CommonUtility.generateJansurakshaToken("61", "JAVA")+":"+UpdateAcStatusFrontendReq.builder().toString());
}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		PmJanSurakshaApplication.bankDetails = bankMasterAgencyDaoImpl.getAllBankDetails();
	}

}
