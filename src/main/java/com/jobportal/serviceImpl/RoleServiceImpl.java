package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.RoleDto;
import com.jobportal.entity.RoleEntity;
import com.jobportal.repository.RoleRepository;
import com.jobportal.serviceInterface.RoleInterface;

@Service
public class RoleServiceImpl implements RoleInterface {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDto addRoleToUser(RoleDto roleDto) {
		RoleEntity entity = new RoleEntity();
		entity.setRoleName(roleDto.getRoleName());
		entity.setDescription(roleDto.getDescription());
		roleRepository.save(entity);
		return roleDto;
	}

	@Override
	public RoleDto updateRole(Long roleId, RoleDto roleDto) throws Exception {

		RoleEntity entity = this.roleRepository.findById(roleId).orElseThrow(() -> new Exception("Rple not found"));
		entity.setRoleName(roleDto.getRoleName());
		entity.setDescription(roleDto.getDescription());
		roleRepository.save(entity);

		return roleDto;
	}

	@Override
	public RoleDto deleteRole(Long roleId, RoleDto roleDto) throws Exception {
		this.roleRepository.findById(roleId).orElseThrow(() -> new Exception("Role not found"));
		return roleDto;
	}

}
