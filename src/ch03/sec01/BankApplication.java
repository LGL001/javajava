package ch03.sec01; 
import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성

        boolean run = true;  // while 루프를 제어하기 위한 boolean 변수
        int balance = 0;     // 계좌의 잔고를 저장할 변수

        while (run) {
            // 메뉴 출력
            System.out.println("-------------------------------------");
            System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
            System.out.println("-------------------------------------");
            System.out.print("선택> ");

            String menuNum = scanner.nextLine(); // 키보드로부터 메뉴 번호를 문자열로 읽음

            switch (menuNum) {
                case "1":
                    System.out.print("예금액> ");
                    String depositAmount = scanner.nextLine(); // 예금액을 문자열로 읽음
                    balance += Integer.parseInt(depositAmount); // 문자열을 정수로 변환하여 잔고에 더함
                    break;

                case "2":
                    System.out.print("출금액> ");
                    String withdrawAmount = scanner.nextLine(); // 출금액을 문자열로 읽음
                    int withdraw = Integer.parseInt(withdrawAmount); // 문자열을 정수로 변환

                    if (balance >= withdraw) {
                        balance -= withdraw; // 잔고에서 출금액을 뺌
                    } else {
                        System.out.println("잔고가 부족합니다.");
                    }
                    break;

                case "3":
                    System.out.print("잔고> ");
                    System.out.println(balance); // 현재 잔고를 출력
                    break;

                case "4":
                    run = false; // while 루프의 조건식을 false로 만들어 루프를 종료
                    break;

                default:
                    System.out.println("잘못된 메뉴를 선택하셨습니다. 다시 선택해주세요.");
                    break;
            } // switch 끝
        } // while 끝

        System.out.println("프로그램 종료");
        scanner.close(); // Scanner 리소스 정리
    }
}