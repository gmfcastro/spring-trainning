package com.springtrainning.repository;

import com.springtrainning.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>{
    List<Person> findByNameIgnoreCaseContaining(String name);
}
