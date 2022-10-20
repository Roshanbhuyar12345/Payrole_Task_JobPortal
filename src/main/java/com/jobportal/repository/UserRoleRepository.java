package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.IListUserRole;
import com.jobportal.entity.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	UserRoleEntity findByUserIdAndRoleId(Long userId, Long roleId);

	List<IListUserRole> findByOrderByIdDesc(Class<IListUserRole> class1);
}
