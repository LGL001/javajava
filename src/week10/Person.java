package week10;

public class Person {
    final String nation = "Korea"; // 값 변경 불가
    final String ssn;
    String name;

    public Person(String ssn, String name) {
        this.ssn = ssn;
        this.name = name;
    }
}