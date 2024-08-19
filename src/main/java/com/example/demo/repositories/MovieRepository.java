package com.example.demo.repositories;

import com.example.demo.entities.Movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByTitleContaining(String keyword, int take, int skip);
}