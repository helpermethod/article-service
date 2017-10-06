package com.predic8.workshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Article {
	@NonNull
	@Id
	private String uuid;
	@NonNull
	private String name;
	@NonNull
	private String description;
	@NonNull
	private BigDecimal price;
}