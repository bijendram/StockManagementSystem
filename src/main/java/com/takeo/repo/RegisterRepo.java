package com.takeo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.takeo.Entity.Register;
@Repository
public interface RegisterRepo extends MongoRepository<Register, String> {

	 Register findByEmail(String email);
	   default void insertDocument(Register register) {
	        insert(register);
	    }
}
