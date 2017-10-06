package com.predic8.workshop.event;

import com.predic8.workshop.entity.Article;
import com.predic8.workshop.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_MESSAGE_KEY;

@RequiredArgsConstructor
@Service
public class ArticleListener {
	private final ArticleRepository articleRepository;

	@KafkaListener(topics = "articles", group = "")
	public void listen(@Payload ArticleEvent articleEvent, @Header(RECEIVED_MESSAGE_KEY) String uuid) {
		switch (articleEvent.getOperation()) {
			case "created":
			case "updated":
				articleRepository.save(new Article(uuid, articleEvent.getName(), articleEvent.getDescription(), articleEvent.getPrice()));
				break;
			case "deleted":
				articleRepository.delete(uuid);
		}
	}
}
