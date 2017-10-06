package com.predic8.workshop.api;

import com.predic8.workshop.dto.ArticleDto;
import com.predic8.workshop.entity.Article;
import com.predic8.workshop.error.NotFoundException;
import com.predic8.workshop.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

	@GetMapping("/{uuid}")
	public Article show(@PathVariable String uuid) {
		return articleService.show(uuid);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody ArticleDto articleDto, UriComponentsBuilder uriComponentsBuilder) {
		String uuid = articleService.save(articleDto);
		String uri = uriComponentsBuilder.path("/{uuid}").buildAndExpand(uuid).toUriString();

		return ResponseEntity
			.accepted()
			.header("Location", uri)
			.build();
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<Void> update(@PathVariable String uuid, @RequestBody ArticleDto article) {
		articleService.update(uuid, article);

		return ResponseEntity.accepted().build();
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> delete(@PathVariable String uuid) {
		articleService.delete(uuid);

		return ResponseEntity.accepted().build();
	}
}