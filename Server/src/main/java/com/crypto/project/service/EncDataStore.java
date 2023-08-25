package com.crypto.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.project.model.EncData;
@Repository
public interface EncDataStore extends JpaRepository<EncData, Long>{
	
	List<EncData> findByUserNumId(Long userNumId);
	Optional<EncData> findById(Long id);
}
