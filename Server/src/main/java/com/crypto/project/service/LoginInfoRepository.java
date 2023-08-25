package com.crypto.project.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.project.model.LoginInfo;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, String>{
	//public List<Users> findAll();
}