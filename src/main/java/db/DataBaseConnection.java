package db;

import models.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection {

  public void contactRecorder(Contact contact) throws SQLException {
    String url = "jdbc:mysql://localhost:3306/phonebook";
    String username = "root";
    String password = "b051d7a3eE";

    Connection connection = DriverManager.getConnection(url, username, password);
    String query = "insert into contacts(id, name, lastName, email, phone, address, description) "
        + "values(?,?,?,?,?,?,?) on duplicate key update "
        + "name = values(name), lastName = values(lastName), email = values(email), phone = values(phone), "
        + "address = values(address), description = values(description)";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, contact.getId());
    preparedStatement.setString(2, contact.getName());
    preparedStatement.setString(3, contact.getLastName());
    preparedStatement.setString(4, contact.getEmail());
    preparedStatement.setString(5, contact.getPhone());
    preparedStatement.setString(6, contact.getAddress());
    preparedStatement.setString(7, contact.getDescription());

    int row = preparedStatement.executeUpdate();
    if(row>0){
      System.out.println("Successful!");
    }else{
      System.out.println("Failed!");
    }
    preparedStatement.close();
    connection.close();
  }

}
