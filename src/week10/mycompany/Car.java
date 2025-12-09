package week10.mycompany;

// week10 내부의 다른 패키지를 import
import week10.hankook.SnowTire;
import week10.hankook.Tire;
import week10.kumho.BigWidthTire;

public class Car {
    // 필드
    Tire tire1 = new Tire(); // hankook.Tire 사용
    
    // 이름이 같은 Tire 클래스는 패키지명까지 모두 기술
    week10.kumho.Tire tire2 = new week10.kumho.Tire(); 
    
    SnowTire tire3 = new SnowTire();
    BigWidthTire tire4 = new BigWidthTire();
}