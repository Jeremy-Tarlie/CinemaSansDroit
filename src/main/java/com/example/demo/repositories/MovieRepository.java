package com.example.demo.repositories;

import com.example.demo.entities.Movie;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	Page<Movie> findByTitleContaining(String keyword, int take, int skip);

	Slice<Movie> findByTitleContaining(String keyword, PageRequest pageable);
}