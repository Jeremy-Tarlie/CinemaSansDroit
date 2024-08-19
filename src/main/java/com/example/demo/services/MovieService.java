package com.example.demo.services;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
	@Autowired
    private static MovieRepository movieRepository;

    public static List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public static Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public static List<Movie> getMoviesByTitleContaining(String keyword, int take, int skip) {
        return movieRepository.findByTitleContaining(keyword, take, skip);
    }
}