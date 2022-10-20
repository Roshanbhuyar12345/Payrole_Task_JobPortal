package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.RolePermissionDto;
import com.jobportal.entity.PermissionEntity;
import com.jobportal.entity.RoleEntity;
import com.jobportal.entity.RolePermissionEntity;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.PermissionRepository;
import com.jobportal.repository.RolePermissionRepository;
import com.jobportal.repository.RoleRepository;
import com.jobportal.serviceInterface.RolePermissionInterface;

@Service
public class RolePermissionServieceImpl implements RolePermissionInterface {
	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public RolePermissionDto addPermissionToRole(RolePermissionDto rolePermissionDto) {
		RolePermissionEntity rolePermissionEntity = this.rolePermissionRepository
				.findByRoleIdAndPermissionId(rolePermissionDto.getRoleId(), rolePermissionDto.getPermissionId());

		if (rolePermissionEntity == null) {
			RoleEntity roleEntity = this.roleRepository.findById(rolePermissionDto.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));

			PermissionEntity permissionEntity = permissionRepository.findById(rolePermissionDto.getPermissionId())
					.orElseThrow(() -> new ResourceNotFoundException("permission Not Found"));

			ArrayList<RolePermissionEntity> rolePermissionEntityList = new ArrayList<RolePermissionEntity>();

			RolePermissionEntity newRolePermissionEntity = new RolePermissionEntity();
			newRolePermissionEntity.setRole(roleEntity);
			newRolePermissionEntity.setPermission(permissionEntity);
			rolePermissionEntityList.add(newRolePermissionEntity);
			rolePermissionRepository.saveAll(rolePermissionEntityList);

		} else {
			throw new ResourceNotFoundException("permission already assign to role");

		}

		return rolePermissionDto;
	}

}
