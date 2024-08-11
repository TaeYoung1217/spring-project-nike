package com.github.supercodingspring.nike.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Orders, Integer> {
    Page<Orders> findAllByGeneralUser_gUserId(Integer gUserId, Pageable pageable);
}
