package reop;

import dao.DaoPerson;
import model.DbConfiguration;
import model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PersonRepository implements DaoPerson {
    DbConfiguration dbConfiguration = new DbConfiguration();
    ResultSet resultSet;


    // adding a person to the library
    public void addNewPerson(Person person) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("INSERT INTO `library`.`person` (`id`, `name`, `surname`)Values " + "('" + person.getId() + "'," + "'" + person.getName() + "'," + "'" + person.getSurname() + "')");
    }

    @Override
    public List<Person> getAllPerson() throws Exception {
        resultSet = dbConfiguration.dbExecuteQuery("Select * From person");

        List<Person> list = new LinkedList<>();
        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getString(1));
            person.setName(resultSet.getString(2));
            person.setSurname(resultSet.getString(3));

            list.add(person);
        }
        return list;
    }

    @Override
    public Person findPerson(String id) throws Exception {
        resultSet = dbConfiguration.dbExecuteQuery("Select * From `library`.`person` Where id= " + "'" + id + "'");
        Person person = new Person();
        while (resultSet.next()) {
            person.setId(resultSet.getString(1));
            person.setName(resultSet.getString(2));
            person.setSurname(resultSet.getString(3));
        }
        return person;
    }

    public Person updatePerson(Person person) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("UPDATE `library`.`person` SET `id`='" + person.getId() + "', `name`='" + person.getName() + "'," +
                "`surname`='" + person.getSurname() + "'" + "' WHERE `id`='" + person.getId() + "'");
        return person;
    }

    // Delete Person
    public void deletePerson(String id) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("DELETE FROM `library`.`person` WHERE `id`='" + id + "'");
    }
}