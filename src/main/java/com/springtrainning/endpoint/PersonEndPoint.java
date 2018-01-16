package com.springtrainning.endpoint;


import com.springtrainning.error.CustomErrorType;
import com.springtrainning.model.Person;
import com.springtrainning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PersonEndPoint {

    private final PersonRepository personDAO;

    @Autowired
    public PersonEndPoint(PersonRepository personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(personDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id){
        Person person = personDAO.findOne(id);
        if (person == null)
            return new ResponseEntity<>(new CustomErrorType("Person not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findPersonByName(@PathVariable String name){
        return new ResponseEntity<>(personDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Person person){
        return new ResponseEntity<>(personDAO.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        personDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Person person){
        personDAO.save(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}