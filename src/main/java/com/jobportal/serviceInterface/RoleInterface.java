package com.jobportal.serviceInterface;

import java.util.List;

import com.jobportal.dto.IListRoleDto;
import com.jobportal.dto.RoleDto;

public interface RoleInterface {

	RoleDto addRoleToUser(RoleDto roleDto);

	RoleDto updateRole(Long roleId, RoleDto roleDto) throws Exception;

	RoleDto deleteRole(Long roleId, RoleDto roleDto) throws Exception;

	List<IListRoleDto> getAllRoles();
}
