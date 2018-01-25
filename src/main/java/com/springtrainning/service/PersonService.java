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


/**
 * @Service - indica que a classe irá conter regras de negócio do sistema.
 * */
@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;


  public ResponseEntity<?> findAll(){
    try {
      return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> findById(@RequestParam(value ="id") String id){
    try {
      return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    } catch(IllegalArgumentException i){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @Transactional
  public ResponseEntity<?> save(@Valid @RequestBody Person person){
    try {
      return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  public @ResponseBody
  ResponseEntity<?> delete(@Valid @RequestBody Person person) {
    try {
      if (personRepository.findOne(person.getId()) != null){
        personRepository.delete(person);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @Transactional
  public ResponseEntity<?> update(@Valid @RequestBody Person person){
    try {
      if (personRepository.findOne(person.getId()) != null) {
        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}