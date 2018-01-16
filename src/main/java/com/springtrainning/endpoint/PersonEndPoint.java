package com.springtrainning.endpoint;


import com.springtrainning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/people")
public class PersonEndPoint {

    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    public PersonEndPoint(EntityManager manager) {
        this.manager = manager;
    }


    @GetMapping
    public ResponseEntity<?> listAll(){
        try {
            return new ResponseEntity<>(manager.createQuery("SELECT p FROM Person p").getResultList(), HttpStatus.OK);
        } catch (IllegalArgumentException e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<?> findPersonByName(@PathVariable(value ="id") Long id){
        try{
            return new ResponseEntity<>(manager.find(Person.class, id), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/findById/")
    public ResponseEntity<?> findPersonByNameDom(@RequestParam(value ="id") Long id){
        try{
            return new ResponseEntity<>(manager.find(Person.class, id), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Person person){
        try {
            manager.persist(person);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (TransactionRequiredException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping
    public @ResponseBody ResponseEntity<?> remove(@Valid @RequestBody Person person) {
        try {
            Person removedPerson = manager.find(Person.class, person.getId());
            manager.remove(removedPerson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Person person){
        try {
            manager.merge(person);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TransactionRequiredException e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}