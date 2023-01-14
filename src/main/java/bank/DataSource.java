package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";//path for the database
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);//connection to the DB
      System.out.println("Connection to DB is successfull");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
public static Customer getCustomer( String username){
  String sql = "select * from customers where username = ?";//? is a placeholder to prevent from database attacks. we donot pass raw inputs given by the user  
  Customer customer = null;
  try(Connection connection = connect(); 
      PreparedStatement statement = connection.prepareStatement(sql)){// try with resource can close the specified resources automatically once thery are executed
      statement.setString(1, username);// replacing the place holder to the user given input. we used only one place holder. 1 is pointing to '?'.
      try(ResultSet resultSet = statement.executeQuery()){// result set class is used to store the result of the sql query
       customer = new Customer(resultSet.getInt("id"),
                               resultSet.getString("name"),
                               resultSet.getString("username"),
                               resultSet.getString("password"),
                               resultSet.getInt("account_id"));// the lable must match the column name in the DB 
      }
  }catch(SQLException e){
    e.printStackTrace();
  }
  return customer;
}
public static Accounts getAccounts(int id){
  String sql = "select * from accounts where id = ?";
  Accounts accounts = null;
  try(Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql)){
   statement.setInt(1, id);
   try(ResultSet resultSet = statement.executeQuery()){
    accounts = new Accounts(resultSet.getInt("id"),
                            resultSet.getString("type"),
                            resultSet.getFloat("balance"));
   }
  }catch(Exception e){
    e.printStackTrace();
  }
  return accounts;
}
  public static void main(String[] args) {
    // Customer customer = getCustomer("twest8o@friendfeed.com");
    // System.out.println(customer.getName()); 
    Accounts accounts = getAccounts(14067);
    System.out.println(accounts.getBalance());
  }
}
