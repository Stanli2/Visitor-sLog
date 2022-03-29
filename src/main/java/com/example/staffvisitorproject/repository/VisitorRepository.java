package com.example.staffvisitorproject.repository;

import com.example.staffvisitorproject.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByEmailAddress(String email);
    Optional<Visitor> findById(Long id);
}
