package com.crypto.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crypto.project.model.EncData;
import com.crypto.project.service.EncDataStore;
@RestController
@RequestMapping("/msg")
public class MessageCheck {

	@Autowired
	private EncDataStore encData;
	
	@GetMapping("/getAllMsg/{userNumId}")
	public List<EncData> getMsgs(@PathVariable Long userNumId){
		try {
			return encData.findByUserNumId(userNumId);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
	
	@GetMapping("/deleteMsg/{id}")
	public void deleteMsgs(@PathVariable Long id){
		try {
			 encData.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}		
	}
}
