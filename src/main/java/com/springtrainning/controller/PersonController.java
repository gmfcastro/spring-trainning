package com.springtrainning.controller;


import com.springtrainning.model.Person;
import com.springtrainning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/people")
public class PersonController  {

  @Autowired
  PersonService personService;


  @GetMapping
  public ResponseEntity<?> findAll(){
    return personService.findAll();
  }

  @GetMapping(path = "/find/{id}")
  public ResponseEntity<?> findById(@RequestParam(value ="id") String id){
    return personService.findById(id);
  }

  @Transactional
  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody Person person){
    return personService.save(person);
  }

  @Transactional
  @DeleteMapping
  public @ResponseBody ResponseEntity<?> remove(@Valid @RequestBody Person person) {
    personService.delete(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Transactional
  @PutMapping
  public ResponseEntity<?> update(@Valid @RequestBody Person person){
    personService.save(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}