package com.jansuraksha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankMasterAgency {
	private String APY;
	private String ARK_URL;
	private String BANK_CODE;
	private String BANK_NAME;
	private String EMAIL_ID;
	private String PMJJBY;
	private String PMSBY;
	private String BANKSHORTNAME;

}
