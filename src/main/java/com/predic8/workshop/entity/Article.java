package com.predic8.workshop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
}