package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.RolePermissionEntity;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

	RolePermissionEntity findByRoleIdAndPermissionId(Long roleId, Long permissionId);

}
