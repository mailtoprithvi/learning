package com.enumcrm.dao;

import com.enumcrm.domain.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

  private static final String JDBC_URL = "jdbc:h2:~/test;IFEXISTS=TRUE";
  private static final String USERNAME = "";
  private static final String PASSWORD = "";
  private static final String CREATE_TABLE_SQL = "CREATE TABLE Customer (" +
      "id bigint auto_increment PRIMARY KEY," +
      "firstname varchar(20)," +
      "lastname varchar(256)," +
      "email varchar(256)," +
      "UNIQUE(email))";
  private static final String INSERT_SQL_CUSTOMER =
      "INSERT INTO Customer(firstname,lastname, email) VALUES (?,?,?)";

  private static final String SELECT_ALL_SQL =
      "SELECT * from Customer";

  private static final String SELECT_BY_EMAIL_SQL =
      "SELECT * from Customer WHERE email=?";

  public void create(Customer customer) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(INSERT_SQL_CUSTOMER);
      statement.setString(1, customer.getFirstName());
      statement.setString(2, customer.getLastName());
      statement.setString(3, customer.getEmail());
      statement.executeUpdate();
    }
  }

  public List<Customer> getAll() throws SQLException {
    try (Connection connection = getConnection()) {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
      List<Customer> customers = new ArrayList<>();
      while (resultSet.next()) {
        customers.add(toCustomer(resultSet));
      }
      return customers;
    }
  }

  public Customer getByEmail(String email) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_SQL);
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      return toCustomer(resultSet);
    }
  }

  public void createTable() throws SQLException {
    try (Connection connection = getConnection()) {
      Statement statement = connection.createStatement();
      statement.executeUpdate(CREATE_TABLE_SQL);
    }
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
  }

  private Customer toCustomer(ResultSet resultSet) throws SQLException {
    String firstName = resultSet.getString("firstname");
    String lastName = resultSet.getString("lastname");
    String email = resultSet.getString("email");
    return new Customer(firstName, lastName, email);
  }
}
