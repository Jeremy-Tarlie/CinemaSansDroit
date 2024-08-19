package com.example.demo.repositories;

import com.example.demo.entities.Movies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movies, Long> {
    // Utilisez Pageable pour gérer la pagination
    Page<Movies> findByTitleContaining(String keyword, Pageable pageable);
}
