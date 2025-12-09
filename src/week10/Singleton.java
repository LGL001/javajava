package week10;

public class Singleton {
    // private static 필드 (자기 자신 객체 생성)
    private static Singleton singleton = new Singleton();

    // private 생성자 (외부에서 new 차단)
    private Singleton() {}

    // public static 메소드 (유일한 접근 통로)
    public static Singleton getInstance() {
        return singleton;
    }
}