package com.test.mongo.sevis.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.test.mongo.config.Constants;
import com.test.mongo.domain.User;
import com.test.mongo.security.AuthoritiesConstants;
import com.test.mongo.service.dto.UserDTO;
import com.test.mongo.sevis.domain.SevisPerson;
import com.test.mongo.sevis.service.SevisPersonService;
import com.test.mongo.web.rest.UserResource;
import com.test.mongo.web.rest.errors.BadRequestAlertException;
import com.test.mongo.web.rest.errors.EmailAlreadyUsedException;
import com.test.mongo.web.rest.errors.LoginAlreadyUsedException;
import com.test.mongo.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;


/**
 * REST controller for handling Sevis Persons.
 */
@RestController
@RequestMapping("/sevis")
public class SevisResource {
	private final Logger log = LoggerFactory.getLogger(SevisResource.class);
	
	@Autowired
	SevisPersonService sevisPersonService;

	/**
     * GET /audits : get list of persons
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/listPersons")
    @ResponseBody
    public List<SevisPerson> getAll() {    
    	log.debug("Get - All persons");
    	/*
       if(sevisPersonService.findPersonById("1") == null) {
    	    SevisPerson person1 = new SevisPerson("1", "Joe", "Doe", 26, "Detriot", "F1", "8/15/2018");
    	    SevisPerson person2 = new SevisPerson("2", "Jane", "Doe", 24, "Washington DC", "F1", "8/17/2017");
    	    SevisPerson person3 = new SevisPerson("3", "John", "Ken", 30, "New York", "B2", "9/15/2018");
    	    sevisPersonService.saveSevisPerson(person1);
    	    sevisPersonService.saveSevisPerson(person2);
    	    sevisPersonService.saveSevisPerson(person3);
       }
       /*
       List<SevisPerson> persons = new ArrayList<>();
 
       SevisPerson person1 = new SevisPerson("1", "Joe", "Doe", 26, "Detriot", "F1", "8/15/2018");
       SevisPerson person2 = new SevisPerson("2", "Jane", "Doe", 24, "Washington DC", "F1", "8/17/2017");
       SevisPerson person3 = new SevisPerson("3", "John", "Ken", 30, "New York", "B2", "9/15/2018");
       
       persons.add(person1);
       persons.add(person2);
       persons.add(person3);
       */
       
       return sevisPersonService.findAll();
    }
    
    /**
     * PUT /updatePerson : Updates an existing Person.
     *
     * @param SevisPerson the person to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated person  
     */
    @PutMapping("/updatePerson")
    @Timed
    @ResponseBody
    public SevisPerson updatePerson(@Valid @RequestBody SevisPerson person) {  
    	log.debug("Put - Update person : " + person.getId());
        sevisPersonService.saveSevisPerson(person);
        return person;
    }
    
    /**
     * DELETE /delePerson/:id : delete the person.
     *
     * @param id the id of the person to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deletePerson/{id}")
    @ResponseBody
    @Timed
    public SevisPerson deleteUser(@PathVariable String id) {
    	log.debug("Delete - Delete person : " + id);
    	SevisPerson person = sevisPersonService.findPersonById(id);
    	if(person != null) {
    		sevisPersonService.deleteSevisPerson(person);
    	}
        return person;
    }
    
    /**
     * POST  /createPerson  : Creates a new person.
  
     * @param SevisPerson the person to create
      * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/createPerson")
    @Timed
    @ResponseBody
    public SevisPerson createPerson(@Valid @RequestBody SevisPerson person) throws URISyntaxException {
    	log.debug("Post - create person : " + person.getFirstName());
    	person.setId("" + (int)(Math.random() * 1000000));
    	sevisPersonService.saveSevisPerson(person);
    	return person;
    }
}
