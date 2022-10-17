package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
