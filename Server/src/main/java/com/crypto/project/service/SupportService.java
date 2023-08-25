package com.crypto.project.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.project.model.SupportForm;

public interface SupportService extends JpaRepository<SupportForm, Long>{
	

}
