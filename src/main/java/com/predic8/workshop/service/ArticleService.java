package com.predic8.workshop.service;

import com.predic8.workshop.dto.ArticleDto;
import com.predic8.workshop.entity.Article;
import com.predic8.workshop.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
	private final KafkaTemplate<String, ArticleDto> kafkaTemplate;
	private final ArticleRepository articleRepository;

	public Article save(Article article) {
		Article savedArticle = articleRepository.save(article);

		kafkaTemplate.send("articles", new ArticleDto("create", savedArticle.getName(), savedArticle.getDescription(), article.getPrice()));

		return savedArticle;
	}

	public List<Article> index() {
		return articleRepository.findAll();
	}

	public void delete(Long id) {
		articleRepository.delete(id);
	}

	public Optional<Article> show(Long id) {
		return Optional.ofNullable(articleRepository.findOne(id));
	}
}