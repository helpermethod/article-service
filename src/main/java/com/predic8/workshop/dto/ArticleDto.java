package com.predic8.workshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleDto {
	private final String operation;
	private final String name;
	private final String description;
	private final BigDecimal price;
}