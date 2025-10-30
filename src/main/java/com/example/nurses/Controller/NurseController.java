package com.example.nurses.Controller;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nurses.Repository.NurseRepository;
import com.example.nurses.Entity.Nurse;


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

	@PostMapping()
	public ResponseEntity<Nurse>createNurse(@RequestBody Nurse nurse) {
		try {
			 Nurse _nurse = nurseRepository.save(
			            new Nurse(
			                nurse.getName(),
			                nurse.getSurname(),
			                nurse.getEmail(),
			                nurse.getUser(),
			                nurse.getPass()
			            )
			        );
			  return ResponseEntity.status(HttpStatus.CREATED).body(_nurse);
		
		
		}catch(Exception e){
			
			return ResponseEntity.badRequest().build();
		}
	}
}
	
