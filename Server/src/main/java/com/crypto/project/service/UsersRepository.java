package com.crypto.project.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.project.model.ApplicationUser;
import com.crypto.project.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByUserId(String userId);
	Users findByUserNumId(Long userNumId);
	List<Users> findByRole(String role);
	int deleteByUserNumId(long userNumId);
	List<Users> findByUserType(String userType);

}