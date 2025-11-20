package week12_team;

public class Account {
	
	
	private String number;
	private String owner;
	private int balance;
	
	
	
	public Account(String number, String owner, int balance) {	
		this.number=number;
		this.owner=owner;
		this.balance=balance;	
	}
	
	public String getnumber() {return number;}
	public String getowner() {return owner;}
	public int getbalance() {return balance;}
	
	public void setbalance(int balance) {
		this.balance=balance;
	}
	
	
}
