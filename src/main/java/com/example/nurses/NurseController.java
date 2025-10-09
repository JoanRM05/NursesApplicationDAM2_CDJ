package com.example.nurses;



import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	    	
	    		JSONParser jsonparser= new JSONParser();
	    		String rutaProyecto= System.getProperty("user.dir");
	    		String fs = File.separator;
	    		
	    		
	    		try {
	    			
	    			FileReader reader = new FileReader(rutaProyecto+fs+"src"+fs+"main"+fs+"resources"+fs+ "static"+fs+"nurses.json");
	    		
	    		    Object obj=jsonparser.parse(reader);
	    		
	    		    JSONObject empjsonobj=(JSONObject)obj;
	    		
	    		    JSONArray arraynurse=(JSONArray)empjsonobj.get("nurses");
	    		   
	    		    
	    		    if(arraynurse !=null) {

	    			   for(int i=0;i<arraynurse.size();i++) {
	    				   
	    				   JSONObject seachjson=(JSONObject) arraynurse.get(i);
	  				  
	    				   String pass= (String) seachjson.get("pass");
	    				   String user= (String) seachjson.get("user");
	    				
	    				   if(user.equals(nurse.getUser()) && pass.equals(nurse.getPass())){
	    			        	return ResponseEntity.ok(true);
	    				}			 					
	    			}
	    			   return ResponseEntity.ok(false);
	    		    }
	    		    
	            }catch (IOException | ParseException e) {
	                e.printStackTrace();             
	            }	
	    		 return ResponseEntity.notFound().build();
	    }

	}