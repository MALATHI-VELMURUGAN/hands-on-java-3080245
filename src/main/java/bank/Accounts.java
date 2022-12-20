package bank;

public class Accounts {
  private int id;
  private String type;
  private Float balance;

  public Accounts() {
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

  public Float getBalance() {
    return this.balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

}
