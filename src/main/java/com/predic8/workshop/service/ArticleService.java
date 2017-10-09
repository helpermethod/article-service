package com.predic8.workshop.service;

import com.predic8.workshop.dto.ArticleDto;
import com.predic8.workshop.entity.Article;
import com.predic8.workshop.error.NotFoundException;
import com.predic8.workshop.event.ArticleEvent;
import com.predic8.workshop.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
	private final KafkaTemplate<String, ArticleEvent> kafkaTemplate;
	private final ArticleRepository articleRepository;

	// Query
	public List<Article> index() {
		return articleRepository.findAll();
	}

	// Query
	public Article show(String uuid) {
		return Optional.ofNullable(articleRepository.findOne(uuid)).orElseThrow(NotFoundException::new);
	}

	// Query
	public void save(String uuid, ArticleDto articleDto) {
		kafkaTemplate.send("articles", uuid, new ArticleEvent("created", articleDto.getName(), articleDto.getDescription(), articleDto.getPrice()));
	}

	// Command
	public void update(String uuid, ArticleDto articleDto) {
		kafkaTemplate.send("articles", uuid, new ArticleEvent("updated", articleDto.getName(), articleDto.getDescription(), articleDto.getPrice()));
	}

	// Command
	public void delete(String uuid) {
		kafkaTemplate.send("articles", uuid, new ArticleEvent("deleted", null, null, null));
	}
}