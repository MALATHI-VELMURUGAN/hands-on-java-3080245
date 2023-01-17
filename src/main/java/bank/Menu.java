package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  private Scanner scanner;
  public static void main(String [] args){
    System.out.println("Welcome to Global International Bank!");
    Menu menu = new Menu();//class object for this class
    menu.scanner = new Scanner(System.in);// initiallizing scanner to get input from input stream
    Customer customer = menu.authenticateUser();
    if(customer != null){
      Accounts account = DataSource.getAccounts(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();//closing the scanner
  }
  private Customer authenticateUser(){
    System.out.println("Please enter the username: ");
    String user = scanner.next();
    System.out.println("Please enter the password: ");
    String password = scanner.next();
    Customer customer = null;
    try{
      customer = Authenticate.login(password, user);  
    }catch(LoginException e){
      System.out.println("There was an error: "+ e.getMessage());
    }
    return customer;
  }
  private void showMenu(Customer customer, Accounts account){
    int selection = 0;

    while(selection!=4 && customer.isAuthenticate()){
      System.out.println("=================================================");// divider
      System.out.println("Please select the type of transaction you want!");
      System.out.println("1. Deposit");
      System.out.println("2. Withdrawal");
      System.out.println("3. Check Balance");
      System.out.println("4. Exit");
      System.out.println("=================================================");// divider
      selection = scanner.nextInt();
      double ammount = 0;
      switch(selection){
        case 1:
              System.out.println("How much money you want to deposit?");
              ammount = scanner.nextDouble();
              try{
                account.deposit(ammount);
              }catch (AmountException e){
                System.out.println(e.getMessage());
                System.out.println("Please try again!");
              }
              break;
        case 2:
              System.out.println("How much money you want to withdraw?");
              ammount = scanner.nextDouble();
              try{
              account.withdraw(ammount);
              }catch(AmountException e){
                System.out.println(e.getMessage());
                System.out.println("Something went wrong. Please try again");
              }
              break;
        case 3: 
              System.out.println("Your account balance is:"+ account.getBalance());
              break;
        case 4:
              Authenticate.logout(customer);
              System.out.println("Thanks for banking at Global International Bank :)");
              break;
        default:
        System.out.println("Something went wrong. Please try again");
        break;

      }
    }
  }
}
