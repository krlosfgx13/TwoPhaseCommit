package com.example.ddbproject.service;

import com.example.ddbproject.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {

    void createPerson(Person person) throws SQLException;
    List<Person> getPersons(String database) throws SQLException;
    Person getPersonById(int id, String database) throws SQLException;
    void updatePerson(Person person, int id) throws SQLException;
    void deletePerson(int id) throws SQLException;
}
