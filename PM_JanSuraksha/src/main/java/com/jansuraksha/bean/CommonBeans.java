package com.jansuraksha.bean;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class CommonBeans {

	protected final JdbcTemplate jdbcTemplate;
}
