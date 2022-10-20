package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.PermissionDto;
import com.jobportal.entity.PermissionEntity;
import com.jobportal.repository.PermissionRepository;
import com.jobportal.serviceInterface.PermissionInterface;

@Service
public class PermissionServiceImpl implements PermissionInterface {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public PermissionDto addPermission(PermissionDto permissionDto) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setBaseUrl(permissionDto.getBaseUrl());
		permissionEntity.setDescription(permissionDto.getDescription());
		permissionEntity.setMethod(permissionDto.getMethod());
		this.permissionRepository.save(permissionEntity);
		return permissionDto;
	}

}
