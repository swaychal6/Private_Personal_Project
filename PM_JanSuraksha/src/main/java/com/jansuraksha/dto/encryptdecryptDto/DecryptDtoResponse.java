package com.jansuraksha.dto.encryptdecryptDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecryptDtoResponse {
	@JsonProperty("meta-data")
	private String meta_data;
}
