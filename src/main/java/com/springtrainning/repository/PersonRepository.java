package com.springtrainning.repository;

import com.springtrainning.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
    List<Person> findByNameIgnoreCaseContaining(String name);
}
