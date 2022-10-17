package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.UserEntity;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmailContainingIgnoreCase(String email);
}
