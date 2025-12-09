package week10;

public class Car {
    // 필드
    String model;
    int speed;

    // 생성자
    public Car(String model) {
        this.model = model; // 매개변수와 필드명이 같을 때 this 사용
    }

    // 메소드
    void setSpeed(int speed) {
        this.speed = speed;
    }

    void run() {
        for(int i=10; i<=50; i+=10) {
            this.setSpeed(i);
            System.out.println(this.model + "가 달립니다.(시속:" + this.speed + "km/h)");
        }
    }
}