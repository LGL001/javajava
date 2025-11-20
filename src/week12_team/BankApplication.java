package week12_team;
import java.util.Scanner;

public class BankApplication {
	private static Account[] arry= new Account[100] ;
	private static Scanner scanner = new Scanner(System.in);
	private static int Accountnumber=0;
	public static Boolean exit=true;

	 
	 public static void main(String[] args) {
		 
		 
		 
		
		 while(exit) {
			 System.out.println("--------------------------------------------------	\r\n"
				 		+ "	1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료\r\n"
				 		+ "--------------------------------------------------\r\n"
				 		+ "선택>");
		 
	        int what = Integer.parseInt(scanner.nextLine()); // 
	        if(what==1) {
	        	create();
	        }
	        
	        if(what==2) {
	        	list();
	        }
	        
	        if(what==3) {
	        	plus();
	        }
	        
	        if(what==4) {
	        	withdraw();
	        }
	        
	        if(what==5) {
	        	exit();
	        }
	        
	     
	        }}
	        
	      
	        
	 
	 public static void create() {
		 
		 	
	        System.out.println("계좌번호: ");

	       String number = scanner.nextLine(); // 

	        System.out.println("계좌주: ");

	        String owner = scanner.nextLine(); // 
	  
	        System.out.println("초기입금액: ");

	        int balance = Integer.parseInt(scanner.nextLine()); //
	        
	        Account newAccount=new Account(number,owner,balance);
	        arry[Accountnumber]=newAccount;
	        System.out.println("계좌가 생성되었습니다.");
	       
	      Accountnumber++;
	    		 
	        
		}
	 //목록출력
	 public static void list() {
		 for(int i=0; i<Accountnumber; i++) {
			 System.out.println(arry[i].getnumber() +"\t" + arry[i].getowner() +"\t"+ arry[i].getbalance()+"\n");
		}
		 }
	 
	 public static void plus() {
		 String findnumber;
		 int money;
		 
		 System.out.println("계좌번호: \n");
		 findnumber = scanner.nextLine();
		 System.out.println("예금액: ");
		 money = Integer.parseInt(scanner.nextLine());
		 
		 //계좌찾기
		 Account account=findAccount(findnumber);
		 if(account==null) {System.out.println("계좌가 없습니다");
		 return;}
		 //잔액업데이트
		 account.setbalance(money+account.getbalance());}
		 
		 
		 
		 //출금
		 public static void withdraw() {
			 String number;
			 int money;
			 System.out.println("계좌번호: \n");
			 number = scanner.nextLine();
			 System.out.println("출금액: ");
			 money = Integer.parseInt(scanner.nextLine());

			 //계좌찾기
			 Account account = findAccount(number);
			 if(account==null) {System.out.println("계좌가 없습니다");
			 return;}
				
			 //출금하기
			 if (account.getbalance()>money) {
				 account.setbalance(account.getbalance()-money);
			 }
			 else {System.out.println("잔액부족");}}
			 
			 
			 
		 //종료
		public static void exit() {
			exit=false;
			System.out.println("시스템 종료");
		}
		
		//계좌찾기
		private static Account findAccount(String number) {
			for(int i=0;i<Accountnumber;i++) {
				if(arry[i].getnumber().equals(number)) {
					return arry[i];}
					}
			return null;
		}
	 
	 
	 
    }

