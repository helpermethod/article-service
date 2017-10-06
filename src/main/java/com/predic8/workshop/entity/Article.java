package com.predic8.workshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
}