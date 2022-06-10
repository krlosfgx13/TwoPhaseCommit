package com.example.ddbproject.service.impl;

import com.example.ddbproject.model.Person;
import com.example.ddbproject.service.PersonService;
import com.example.ddbproject.utils.ConnectionConstants;
import com.example.ddbproject.utils.Coordinator;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public void createPerson(Person person) throws SQLException {
        Connection connMySql = Coordinator.getDataBaseConnection(ConnectionConstants.MY_SQL);
        Connection connSqlServer = Coordinator.getDataBaseConnection(ConnectionConstants.SQL_SERVER);
        try {
            if (Objects.isNull(connMySql) || Objects.isNull(connSqlServer))
                throw new SQLNonTransientConnectionException("Error while getting DataBase Connection.");
            connMySql.setAutoCommit(false);
            connSqlServer.setAutoCommit(false);
            String insertStatement = "INSERT INTO Person(Dpi, FirstName, SecondName, FirstLastName, " +
                    "SecondLastName, Address, PhoneNumber, HomePhoneNumber, EmailAddress)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psMySql = connMySql.prepareStatement(insertStatement);
            psMySql.setString(1, person.getDpi());
            psMySql.setString(2, person.getFirstName());
            psMySql.setString(3, person.getSecondName());
            psMySql.setString(4, person.getFirstLastName());
            psMySql.setString(5, person.getSecondLastName());
            psMySql.setString(6, person.getAddress());
            psMySql.setString(7, person.getPhoneNumber());
            psMySql.setString(8, person.getHomePhoneNumber());
            psMySql.setString(9, person.getEmailAddress());

            PreparedStatement psSqlServer = connSqlServer.prepareStatement(insertStatement);
            psSqlServer.setString(1, person.getDpi());
            psSqlServer.setString(2, person.getFirstName());
            psSqlServer.setString(3, person.getSecondName());
            psSqlServer.setString(4, person.getFirstLastName());
            psSqlServer.setString(5, person.getSecondLastName());
            psSqlServer.setString(6, person.getAddress());
            psSqlServer.setString(7, person.getPhoneNumber());
            psSqlServer.setString(8, person.getHomePhoneNumber());
            psSqlServer.setString(9, person.getEmailAddress());

            if (Coordinator.performOperation(psMySql, psSqlServer)) {
                connMySql.commit();
                connSqlServer.commit();
            } else {
                connMySql.rollback();
                connSqlServer.rollback();
            }
            connMySql.close();
            connSqlServer.close();
        } catch (SQLNonTransientConnectionException ex) {
            throw new SQLNonTransientConnectionException(ex.getMessage());
        } catch (SQLException ex) {
            connMySql.rollback();
            connSqlServer.rollback();
            connMySql.close();
            connSqlServer.close();
            throw new SQLException(ex.getMessage());
        }
    }

    @Override
    public List<Person> getPersons(String database) throws SQLException {
        Connection conn = null;
        List<Person> listOfPersons = new ArrayList<>();
        Person person = new Person();
        try {
            conn = Coordinator.getDataBaseConnection(database);
            if (Objects.isNull(conn))
                throw new SQLNonTransientConnectionException("Error while getting DataBase Connection.");
            String selectStatement = "SELECT * FROM Person";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectStatement);

            while (rs.next()) {
                person = new Person.Builder()
                        .withId(rs.getInt("PersonId"))
                        .withDpi(rs.getString("Dpi"))
                        .withFirstName(rs.getString("FirstName"))
                        .withSecondName(rs.getString("SecondName"))
                        .withFirstLastName(rs.getString("FirstLastName"))
                        .withSecondLastName(rs.getString("SecondLastName"))
                        .withAddress(rs.getString("Address"))
                        .withPhoneNumber(rs.getString("PhoneNumber"))
                        .withHomePhoneNumber(rs.getString("HomePhoneNumber"))
                        .withEmailAddress(rs.getString("EmailAddress"))
                        .build();

                listOfPersons.add(person);
            }
        } catch (SQLNonTransientConnectionException ex) {
            throw new SQLNonTransientConnectionException(ex.getMessage());
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (!Objects.isNull(conn))
                conn.close();
        }
        return listOfPersons;
    }

    @Override
    public Person getPersonById(int id, String database) throws SQLException {
        Connection conn = null;
        Person person = new Person();
        try {
            conn = Coordinator.getDataBaseConnection(database);
            if (Objects.isNull(conn))
                throw new SQLNonTransientConnectionException("Error while getting DataBase Connection.");
            String selectSql = "SELECT * FROM Person WHERE PersonId = ?";
            PreparedStatement ps = conn.prepareStatement(selectSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                person = new Person.Builder()
                        .withId(rs.getInt("PersonId"))
                        .withDpi(rs.getString("Dpi"))
                        .withFirstName(rs.getString("FirstName"))
                        .withSecondName(rs.getString("SecondName"))
                        .withFirstLastName(rs.getString("FirstLastName"))
                        .withSecondLastName(rs.getString("SecondLastName"))
                        .withAddress(rs.getString("Address"))
                        .withPhoneNumber(rs.getString("PhoneNumber"))
                        .withHomePhoneNumber(rs.getString("HomePhoneNumber"))
                        .withEmailAddress(rs.getString("EmailAddress"))
                        .build();
            }
        } catch (SQLNonTransientConnectionException ex) {
            throw new SQLNonTransientConnectionException(ex.getMessage());
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (!Objects.isNull(conn))
                conn.close();
        }
        return person;
    }

    @Override
    public void updatePerson(Person person, int id) throws SQLException {
        Connection connMySql = Coordinator.getDataBaseConnection(ConnectionConstants.MY_SQL);
        Connection connSqlServer = Coordinator.getDataBaseConnection(ConnectionConstants.SQL_SERVER);
        try {
            if (Objects.isNull(connMySql) || Objects.isNull(connSqlServer))
                throw new SQLException("Error while getting DataBase Connection.");
            connMySql.setAutoCommit(false);
            connSqlServer.setAutoCommit(false);
            String insertStatement = "UPDATE  Person SET FirstName = ?, SecondName = ?, FirstLastName = ?, " +
                    "SecondLastName = ?, Address = ?, PhoneNumber = ?, HomePhoneNumber = ?, EmailAddress = ? "
                    + "WHERE PersonId = ?";
            PreparedStatement psMySql = connMySql.prepareStatement(insertStatement);
            psMySql.setString(1, person.getFirstName());
            psMySql.setString(2, person.getSecondName());
            psMySql.setString(3, person.getFirstLastName());
            psMySql.setString(4, person.getSecondLastName());
            psMySql.setString(5, person.getAddress());
            psMySql.setString(6, person.getPhoneNumber());
            psMySql.setString(7, person.getHomePhoneNumber());
            psMySql.setString(8, person.getEmailAddress());
            psMySql.setInt(9, id);

            PreparedStatement psSqlServer = connSqlServer.prepareStatement(insertStatement);
            psSqlServer.setString(1, person.getFirstName());
            psSqlServer.setString(2, person.getSecondName());
            psSqlServer.setString(3, person.getFirstLastName());
            psSqlServer.setString(4, person.getSecondLastName());
            psSqlServer.setString(5, person.getAddress());
            psSqlServer.setString(6, person.getPhoneNumber());
            psSqlServer.setString(7, person.getHomePhoneNumber());
            psSqlServer.setString(8, person.getEmailAddress());
            psSqlServer.setInt(9, id);

            if (Coordinator.performOperation(psMySql, psSqlServer)) {
                connMySql.commit();
                connSqlServer.commit();
            } else {
                connMySql.rollback();
                connSqlServer.rollback();
            }
            connMySql.close();
            connSqlServer.close();
        } catch (SQLNonTransientConnectionException ex) {
            throw new SQLNonTransientConnectionException(ex.getMessage());
        } catch (SQLException ex) {
            connMySql.rollback();
            connSqlServer.rollback();
            connMySql.close();
            connSqlServer.close();
            throw new SQLException(ex.getMessage());
        }
    }

    @Override
    public void deletePerson(int id) throws SQLException {
        Connection connMySql = Coordinator.getDataBaseConnection(ConnectionConstants.MY_SQL);
        Connection connSqlServer = Coordinator.getDataBaseConnection(ConnectionConstants.SQL_SERVER);
        try {
            if (Objects.isNull(connMySql) || Objects.isNull(connSqlServer))
                throw new SQLException("Error while getting DataBase Connection.");
            connMySql.setAutoCommit(false);
            connSqlServer.setAutoCommit(false);
            String insertStatement = "DELETE FROM Person WHERE PersonId = ?";
            PreparedStatement psMySql = connMySql.prepareStatement(insertStatement);
            psMySql.setInt(1, id);

            PreparedStatement psSqlServer = connSqlServer.prepareStatement(insertStatement);
            psSqlServer.setInt(1, id);

            if (Coordinator.performOperation(psMySql, psSqlServer)) {
                connMySql.commit();
                connSqlServer.commit();
            } else {
                connMySql.rollback();
                connSqlServer.rollback();
            }
            connMySql.close();
            connSqlServer.close();
        } catch (SQLNonTransientConnectionException ex) {
            throw new SQLNonTransientConnectionException(ex.getMessage());
        } catch (SQLException ex) {
            connMySql.rollback();
            connSqlServer.rollback();
            connMySql.close();
            connSqlServer.close();
            throw new SQLException(ex.getMessage());
        }
    }
}
