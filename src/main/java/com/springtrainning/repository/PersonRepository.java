package com.springtrainning.repository;

/*
import com.springtrainning.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>{
    List<Person> findByNameIgnoreCaseContaining(String name);
}
*/


import java.util.List;
import com.springtrainning.model.Person;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByNameIgnoreCaseContaining(String name);
}