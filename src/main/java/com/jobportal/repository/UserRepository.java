package com.jobportal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IListUserDto;
import com.jobportal.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<IListUserDto> getUserById(Long userId, Class<IListUserDto> class1);

	Page<IListUserDto> findByOrderByIdDesc(Pageable page, Class<IListUserDto> topicDtoList);

	Page<IListUserDto> findByUserNameContainingIgnoreCase(String search, Pageable pagable, Class<IListUserDto> class1);

	UserEntity findByEmail(String emailString);

}
