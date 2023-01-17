package bank;

import bank.exceptions.AmountException;

public class Accounts {
  private int id;
  private String type;
  private double balance;

  public Accounts(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double ammount) throws AmountException{
    if(ammount<1){
      throw new AmountException("Minimum ammount can't be deposited");
    }
    else{
      double newBalance = balance + ammount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }

  }
  public void withdraw(double ammount) throws AmountException{
    if(ammount<0){
      throw new AmountException("The withdrawal ammount is invalid");
    }
    else if(ammount>getBalance()){
      throw new AmountException("You donot have sufficient funds in your account");
    }
    else{
      double newBalance = balance - ammount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }

}
