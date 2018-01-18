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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Annotations
 *  @RestController - irá incluir duas anotações a classe, sendo elas:
 *    @Controller - indica que a classe é um controlador Spring MVC.
 *    @ResponseBody - indica ao controlador que o objeto de retorno é automaticamente serializado para JSON e enviado no corpo da requisição.
 *  @Autowired - Marca um método para ser ligado automaticamente com as injeção de dependencia do Spring.
 *  @GetMapping - é um atalho para @RequestMapping(method = RequestMethod.GET).
 *  @RequestBody - indica que o parâmetro deve ser vinculado ao corpo da requisição HTTP.
 *  @Valid - marca um método @RequestBody, para ser automaticamente validado.
 *  @RequestParam - Indica que os parâmetros do método deve ser vinculado ao parâmetro da requisição web
**/

@RestController
@RequestMapping("/people")
public class PersonController  {

  @Autowired
  private PersonService personService;


  @GetMapping
  public ResponseEntity<?> findAll(){
    return personService.findAll();
  }

  @GetMapping(path = "/find/")
  public ResponseEntity<?> findById(@RequestParam(value ="id") String id){
    return personService.findById(id);
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody Person person){
    return personService.save(person);
  }

  @DeleteMapping
  public ResponseEntity<?> remove(@Valid @RequestBody Person person) {
    personService.delete(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@Valid @RequestBody Person person){
    personService.save(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}