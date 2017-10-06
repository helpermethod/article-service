package com.predic8.workshop.dto;

import com.predic8.workshop.entity.Article;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
public class ArticleDto {
	private final String operation;
	@Delegate
	private final Article article;
}