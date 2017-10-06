package com.predic8.workshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleDto {
	private String name;
	private String description;
	private BigDecimal price;
}
