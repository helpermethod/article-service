package com.predic8.workshop.service;

import com.predic8.workshop.dto.ArticleDto;
import com.predic8.workshop.entity.Article;
import com.predic8.workshop.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
@Service
public class ArticleService {
	private final KafkaTemplate<String, ArticleDto> kafkaTemplate;
	private final ArticleRepository articleRepository;

	public List<Article> index() {
		return articleRepository.findAll();
	}

	public Optional<Article> show(Long id) {
		return Optional.ofNullable(articleRepository.findOne(id));
	}

	public Article save(Article article) {
		kafkaTemplate.send("articles", randomUUID().toString(), new ArticleDto("create", articleRepository.save(article)));

		return articleRepository.save(article);
	}

	public void update(Long id, Article article) {
		article.setId(id);

		kafkaTemplate.send("articles", randomUUID().toString(), new ArticleDto("update", articleRepository.save(article)));
	}

	public void delete(Long id) {
		articleRepository.delete(id);
	}
}