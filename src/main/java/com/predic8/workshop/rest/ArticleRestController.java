package com.predic8.workshop.rest;

import com.predic8.workshop.entity.Article;
import com.predic8.workshop.error.NotFoundException;
import com.predic8.workshop.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@RestController
public class ArticleRestController {
	private final ArticleService articleService;

	@GetMapping
	public List<Article> index() {
		return articleService.index();
	}

	@GetMapping("/{id}")
	public Article show(@PathVariable Long id) {
		return articleService.show(id).orElseThrow(NotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Article article, UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(articleService.save(article).getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		articleService.delete(id);

		return ResponseEntity.noContent().build();
	}
}