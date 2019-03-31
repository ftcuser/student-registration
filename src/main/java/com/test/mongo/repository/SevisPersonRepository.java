package com.test.mongo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.mongo.sevis.domain.SevisPerson;

/**
 * Spring Data MongoDB repository for the SevisPerson entity.
 */
@Repository
public interface SevisPersonRepository extends MongoRepository<SevisPerson, String> {

    Optional<SevisPerson> findOneById(String id);

    List<SevisPerson> findAll();
}

