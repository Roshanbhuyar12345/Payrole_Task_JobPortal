package com.jobportal.serviceInterface;

import java.util.List;

import com.jobportal.dto.IListUserRole;
import com.jobportal.dto.UserRoleDto;

public interface UserRoleInterface {

	UserRoleDto addRoleToUser(UserRoleDto userRoleDto);

	List<IListUserRole> getAllUserRoles();
}
