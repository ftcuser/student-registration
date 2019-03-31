package com.test.mongo.sevis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.mongo.repository.SevisPersonRepository;
import com.test.mongo.sevis.domain.SevisPerson;

@Service
public class SevisPersonService {
	
	 	private final SevisPersonRepository sevisPersonRepository;

	    public SevisPersonService (SevisPersonRepository sevisPersonRepository) {
	    	this.sevisPersonRepository = sevisPersonRepository;
	    
	    }
	    
	    public List<SevisPerson> findAll(){
	    	return sevisPersonRepository.findAll();
	    }
	    
	    public SevisPerson findPersonById(String id) {
	    	return sevisPersonRepository.findOne(id);
	    }
	    
	    public void saveSevisPerson(SevisPerson person) {
	    	sevisPersonRepository.save(person);
	    }
	    
	    public void deleteSevisPerson(SevisPerson person) {
	    	sevisPersonRepository.delete(person);
	    }
}
