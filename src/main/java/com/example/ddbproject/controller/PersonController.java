package com.example.ddbproject.controller;

import com.example.ddbproject.model.Person;
import com.example.ddbproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonService service;

    @PostMapping("/person")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        try {
            service.createPerson(person);
            return new ResponseEntity<>("Operation performed successfully.", HttpStatus.OK);
        } catch (SQLNonTransientConnectionException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLException ex) {
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(@RequestParam String database) {
        try {
            List<Person> listOfPersons = service.getPersons(database);
            return new ResponseEntity<>(listOfPersons, HttpStatus.OK);
        } catch (SQLNonTransientConnectionException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") int id, @RequestParam String database) {
        try {
            Person person = service.getPersonById(id, database);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (SQLNonTransientConnectionException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable(value = "id") int id, @RequestBody Person person) {
        try {
            service.updatePerson(person, id);
            return new ResponseEntity<>("Operation performed successfully.", HttpStatus.OK);
        } catch (SQLNonTransientConnectionException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLException ex) {
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> DeletePerson(@PathVariable(value = "id") int id) {
        try {
            service.deletePerson(id);
            return new ResponseEntity<>("Operation performed successfully.", HttpStatus.OK);
        } catch (SQLNonTransientConnectionException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLException ex) {
            return new ResponseEntity<>(String.format("An  error has occurred: %s", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
