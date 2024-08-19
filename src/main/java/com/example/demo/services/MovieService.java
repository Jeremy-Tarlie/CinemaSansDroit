package com.example.demo.services;

import lombok.RequiredArgsConstructor;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Movies;
import com.example.demo.repositories.MovieRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
	@Autowired
    private static MovieRepository movieRepository;

    public static List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }

    public static Optional<Movies> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movies> getMoviesByTitleContaining(String keyword, int take, int skip) {
        // Create a PageRequest object for pagination
        PageRequest pageable = PageRequest.of(skip / take, take);
        
        // Fetch the page using the repository method
        return movieRepository.findByTitleContaining(keyword, pageable).getContent();
    }
    
    
    
}