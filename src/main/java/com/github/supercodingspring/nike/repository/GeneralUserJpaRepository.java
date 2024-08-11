package com.github.supercodingspring.nike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralUserJpaRepository extends JpaRepository<GeneralUser, Integer> {
}
