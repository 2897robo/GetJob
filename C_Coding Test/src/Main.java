class Person {
    private String name;  // private 필드 (외부 직접 접근 불가)
    private int age;

    // 생성자
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter 메서드 (데이터 읽기)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setter 메서드 (데이터 수정)
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("나이는 0보다 커야 합니다.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Alice", 25);

        // 올바른 방법 (getter 사용)
        System.out.println(p.getName()); // "Alice"

        // 잘못된 방법 (직접 접근 불가)
        // p.name = "Bob";  // 오류 발생

        // 올바른 방법 (setter 사용)
        p.setAge(30);
        System.out.println(p.getAge()); // 30
    }
}
