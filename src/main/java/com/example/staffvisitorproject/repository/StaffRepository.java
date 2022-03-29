package com.example.staffvisitorproject.repository;

import com.example.staffvisitorproject.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmailAddress(String email);
    Optional<Staff> findByUsernameAndPassword(String username, String password);
    Optional<Staff> findById(Long id);
}
