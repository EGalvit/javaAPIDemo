package com.example.demo1.dbSource;

import com.example.demo1.models.Person;
import java.sql.*;
import java.util.ArrayList;

public class MariaDB {

    private String connectionString = "jdbc:mysql://localhost/persondb2";
    private String user = "persondbuser";
    private String password = "passw0rd";
    private Connection con;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void connect() {
        try {
            con = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(con != null) {
            System.out.println("Connected");
        }
    }

    private void close(){
        if(con != null){
            try{
                con.close();
            }
            catch (SQLException e){
                System.out.println("Disconnected");
            }
        }
    }

    public ArrayList<Person> getAllPersons(){
        connect();
        ArrayList<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM person";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()) {
                    Person person = new Person();
                    person.setPersonId(resultSet.getInt("id"));
                    person.setFullName(resultSet.getString("fullName"));
                    person.setEmail(resultSet.getString("email"));
                    person.setNote(resultSet.getString("note"));
                    personList.add(person);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return personList;
    }

    public Person getPerson(int personId){
        Person person = null;
        connect();

        String sql = "SELECT * FROM person WHERE id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                person = new Person();
                person.setPersonId(resultSet.getInt("id"));
                person.setFullName(resultSet.getString("fullName"));
                person.setEmail(resultSet.getString("email"));
                person.setNote(resultSet.getString("note"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return person;
    }

    public int postPerson(Person person) {
        connect();

        String sql = "INSERT INTO person (fullName, email, note)" + " values (?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, person.getFullName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getNote());
            preparedStatement.execute();
            close();
            return 1;
        } catch (SQLException e) {
            System.out.println("ya done fucked up son");
            e.printStackTrace();
            close();
            return 0;
        }
    }

    public int putPerson(Person person) {
        connect();
        String sql = "UPDATE person SET fullName = ?, email = ?, note = ? WHERE id = ?";
        int result = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,person.getFullName());
            preparedStatement.setString(2,person.getEmail());
            preparedStatement.setString(3,person.getNote());
            preparedStatement.setInt(4,person.getPersonId());
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("didnt work chief");
        }
        close();
        return result;
    }

    public int deletePerson(int personId) {
        connect();
        String sql = "DELETE FROM person WHERE id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,personId);
            preparedStatement.execute();
            close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            close();
            return 0;
        }
    }

}
