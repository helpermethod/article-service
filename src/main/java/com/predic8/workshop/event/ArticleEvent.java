package com.predic8.workshop.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.math.BigDecimal;

@Data
public class ArticleEvent {
	private final String operation;
	private final String name;
	private final String description;
	private final BigDecimal price;
}