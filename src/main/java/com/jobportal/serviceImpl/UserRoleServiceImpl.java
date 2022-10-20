package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListUserRole;
import com.jobportal.dto.UserRoleDto;
import com.jobportal.entity.RoleEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.entity.UserRoleEntity;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.RoleRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.repository.UserRoleRepository;
import com.jobportal.serviceInterface.UserRoleInterface;

@Service
public class UserRoleServiceImpl implements UserRoleInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserRoleDto addRoleToUser(UserRoleDto userRoleDto) {

		UserRoleEntity userRoleEntity = new UserRoleEntity();
		UserRoleEntity dto = this.userRoleRepository.findByUserIdAndRoleId(userRoleDto.getUserId(),
				userRoleDto.getRoleId());
		if (dto == null) {

			UserEntity userEntity = this.userRepository.findById(userRoleDto.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("User not found"));
			RoleEntity roleEntity = this.roleRepository.findById(userRoleDto.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
			userRoleEntity.setUser(userEntity);
			userRoleEntity.setRole(roleEntity);
			this.userRoleRepository.save(userRoleEntity);
			return userRoleDto;
		} else {
			throw new ResourceNotFoundException("Role already assign to user");
		}
	}

	@Override
	public List<IListUserRole> getAllUserRoles() {
		List<IListUserRole> listUserRoles = this.userRoleRepository.findByOrderByIdDesc(IListUserRole.class);
		return listUserRoles;
	}

}
