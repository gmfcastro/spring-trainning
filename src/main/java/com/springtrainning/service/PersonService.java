package com.springtrainning.service;

import com.springtrainning.model.Person;
import com.springtrainning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;


  public ResponseEntity<?> findAll(){
    return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<?> findById(@RequestParam(value ="id") String id){
    return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<?> save(@Valid @RequestBody Person person){
    return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
  }

  @Transactional
  public @ResponseBody
  ResponseEntity<?> delete(@Valid @RequestBody Person person) {
    personRepository.delete(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<?> update(@Valid @RequestBody Person person){
    personRepository.save(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}