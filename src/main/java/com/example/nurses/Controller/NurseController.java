package com.example.nurses.Controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Login_RA_02.dao.NurseRepository;
import com.example.nurses.Entity.Nurse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository;
	
	@GetMapping("/index")
	public @ResponseBody ResponseEntity<ArrayList<Nurse>> getAll() {
		
		ObjectMapper mapeador = new ObjectMapper();
		
		try {
			
			 JsonNode root = mapeador.readTree(
					 new ClassPathResource("static/nurses.json").getInputStream()
		     );
			 
			 ArrayList<Nurse> listNurses = mapeador.convertValue(root.get("nurses"), new TypeReference<ArrayList<Nurse>>() {});
			 
			 return ResponseEntity.ok(listNurses);
			 
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
		@GetMapping("/name")
		public ResponseEntity<Nurse> findByName(@RequestParam String name) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(
						 new ClassPathResource("static/nurses.json").getInputStream()
			     );
				 ArrayList<Nurse> nurses = mapper.convertValue(root.get("nurses"), new TypeReference<ArrayList<Nurse>>() {});
				
				for (Nurse nurse : nurses) {
					if(nurse.getName().equals(name)) {
						return ResponseEntity.ok(nurse);
					}
				}
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.notFound().build();
		}
		

		 @PostMapping("/login")
		 public ResponseEntity<Boolean>login(@RequestBody Nurse nurse) {
			   boolean exists = nurseRepository.existsByUserAndPass( nurse.getUser(), nurse.getPass());
			 	if(exists) {
			 		return ResponseEntity.ok(true);
			 	}else {
			 		return ResponseEntity.ok(false);
			 	}
		 }
	}
	
