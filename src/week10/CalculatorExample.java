package week10;

public class CalculatorExample {
    public static void main(String[] args) {
        // 객체 생성 없이 클래스명으로 바로 접근
        double result1 = 10 * 10 * Calculator.pi;
        int result2 = Calculator.plus(10, 5);
        int result3 = Calculator.minus(10, 5);

        System.out.println("result1 : " + result1);
        System.out.println("result2 : " + result2);
        System.out.println("result3 : " + result3);
    }
}