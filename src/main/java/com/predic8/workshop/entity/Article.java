package com.predic8.workshop.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

// see https://github.com/rzwitserloot/lombok/issues/1389
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Article {
	@Id
	private String uuid;
	private String name;
	private String description;
	private BigDecimal price;
}