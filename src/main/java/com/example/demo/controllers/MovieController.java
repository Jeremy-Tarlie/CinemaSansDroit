package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;

@Controller
@RequiredArgsConstructor
public class MovieController {
	private final MovieRepository movieRepository;
	
	MovieController(MovieRepository movieRepository) {
	    this.movieRepository = movieRepository;
	  }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> seeMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } 
        
        return ResponseEntity.notFound().build();
    }
    
    
    @GetMapping("/movies/{title}")
    public ResponseEntity<Stream<Movie>> searchMovie(@PathVariable String title){
    	Page<Movie> movie = movieRepository.findByTitleContaining(title, 1, 20);
    	
    	if(!movie.isEmpty()) {
    		return ResponseEntity.ok(movie.get());
    	}
    	return ResponseEntity.notFound().build();
    }
    
    
    @GetMapping("/movies/{title}/{page}")
    public ResponseEntity<Stream<Movie>> searchMovie(@PathVariable String title, @PathVariable int page){
    	Page<Movie> movie = movieRepository.findByTitleContaining(title, page, 20);
    	
    	if(!movie.isEmpty()) {
    		return ResponseEntity.ok(movie.get());
    	}
    	return ResponseEntity.notFound().build();
    }
    
    
    
}