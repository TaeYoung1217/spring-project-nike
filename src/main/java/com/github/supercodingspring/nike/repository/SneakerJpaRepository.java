package com.github.supercodingspring.nike.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerJpaRepository extends JpaRepository<Sneaker,Integer> {
    Page<Sneaker> findAll(Pageable pageable);
}
