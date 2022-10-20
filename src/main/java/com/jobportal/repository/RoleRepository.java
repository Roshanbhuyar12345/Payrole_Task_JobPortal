package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IListRoleDto;
import com.jobportal.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	List<IListRoleDto> findByOrderByIdDesc(Class<IListRoleDto> class1);

}
