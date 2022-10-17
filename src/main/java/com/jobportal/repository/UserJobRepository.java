package com.jobportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.IListUserJobDto;
import com.jobportal.entity.UserJobEntity;

@Repository
public interface UserJobRepository extends JpaRepository<UserJobEntity, Long> {

	UserJobEntity findByUserIdAndJobId(Long userId, Long long1);

	Page<IListUserJobDto> findByOrderByIdDesc(Pageable page, Class<IListUserJobDto> topicDtoList);

	Page<IListUserJobDto> findJobIdByUserIdOrderByIdDesc(Long userId, Pageable pagable, Class<IListUserJobDto> class1);

	Page<IListUserJobDto> findUserIdByJobIdOrderByIdDesc(Long jobId, Pageable pagable, Class<IListUserJobDto> class1);

}
