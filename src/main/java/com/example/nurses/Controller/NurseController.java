package com.example.nurses.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nurses.Repository.NurseRepository;
import com.example.nurses.Entity.Nurse;
import com.example.nurses.Repository.NurseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository;
	
	@GetMapping("/index")
	public @ResponseBody ResponseEntity<List<Nurse>> getAll() {
		try {
			return ResponseEntity.ok(nurseRepository.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@GetMapping("/name")
	public ResponseEntity<Nurse> findByName(@RequestParam String name) {
		Optional<Nurse> optionalNurse =  nurseRepository.findByName(name);
		if(optionalNurse.isPresent()) {
			return ResponseEntity.ok(optionalNurse.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
		

	@PostMapping("/login")
	public ResponseEntity<Boolean>login(@RequestBody Nurse nurse) {
	  boolean exists = nurseRepository.existsByUserAndPass( nurse.getUser(), nurse.getPass());
		if(exists) {
		  return ResponseEntity.ok(true);
		} else {
		  return ResponseEntity.ok(false);
		} 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nurse> findById(@PathVariable Long id) {
		
		Optional<Nurse> nurse =  nurseRepository.findById(id);
		
		if(nurse.isPresent()) {
			return ResponseEntity.ok(nurse.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	
	}

}
