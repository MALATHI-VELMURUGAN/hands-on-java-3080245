package bank;

import javax.security.auth.login.LoginException;

public class Authenticate {
  public static Customer login(String password, String username) throws LoginException{
    Customer customer = DataSource.getCustomer(username);//get customer is static so no need to create obj for data souce class. we can use the class name to access its static methods.
    if(customer == null){// to handle the error if the customer's username is wrong
      throw new LoginException("username not found");//to stop the program abruptly we use builtin java exceptions
    }
    if(password.equals(customer.getPassword())){// if password and username matches
      customer.setAuthenticate(true);
    }
    else{// if password is wrong
      throw new LoginException("Incorrect Password");
    }
    return customer;
  }
  public static void logout(Customer customer){
    customer.setAuthenticate(false);
  }
}
