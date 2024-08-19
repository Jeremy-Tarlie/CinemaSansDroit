package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Movies;
import com.example.demo.repositories.MovieRepository;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;
    
    public MovieController(MovieRepository movieRepository) {
    	this.movieRepository = movieRepository;
	}

    @GetMapping("/movies")
    public ResponseEntity<List<Movies>> seeMovie() {
    	List<Movies> movies = movieRepository.findAll();
    	if(movies.size() > 0) {
    		return ResponseEntity.ok(movies);
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movies> seeMovie(@PathVariable Long id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/movies/search")
    public ResponseEntity<Page<Movies>> searchMovie(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Movies> movies = movieRepository.findByTitleContaining(title, PageRequest.of(page, size));

        if (movies.hasContent()) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
