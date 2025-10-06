package com.example.nurses;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/nurse")
public class NurseController {
	
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
	
}