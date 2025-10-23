package com.example.nurses.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.nurses.Entity.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

}
