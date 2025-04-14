## ✅ 팩토리(Factory) 패턴이란?

객체 생성 로직을 별도의 클래스(Factory)로 분리하여 캡슐화하는 **생성 패턴**이다.

- 객체를 사용하는 클라이언트 코드와 객체 생성 책임을 분리한다.
- 어떤 구체 클래스의 인스턴스를 만들지는 Factory가 결정한다.
- `new` 키워드 사용을 최소화하여, 객체 생성의 유연성과 유지보수성이 높아진다.

---

## 🎯 언제 쓰면 좋을까?

- 객체 생성 로직이 복잡할 때
- 코드의 의존성을 줄이고 싶을 때
- 인터페이스/추상 클래스를 기반으로 여러 구현체 중 선택해서 객체를 생성해야 할 때

---

## 🔧 순수 Java 팩토리 패턴 예시

```java
public interface Animal {
    void speak();
}

public class Dog implements Animal {
    public void speak() {
        System.out.println("멍멍!");
    }
}

public class Cat implements Animal {
    public void speak() {
        System.out.println("야옹~");
    }
}

// Factory 클래스
public class AnimalFactory {
    public static Animal createAnimal(String type) {
        if (type.equals("dog")) {
            return new Dog();
        } else if (type.equals("cat")) {
            return new Cat();
        }
        throw new IllegalArgumentException("알 수 없는 동물 타입: " + type);
    }
}

// 사용 코드
public class Main {
    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal("dog");
        animal.speak(); // 멍멍!
    }
}
```

---

## 🧠 스프링에서의 Factory 패턴 예시

### 1️⃣ BeanFactory (스프링 기본 제공)
```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
Animal dog = context.getBean("dog", Animal.class);
dog.speak(); // 멍멍!
```

### 2️⃣ 직접 만드는 FactoryBean 예시
```java
@Component
public class NotificationFactory {

    public Notification getNotification(String type) {
        if ("email".equalsIgnoreCase(type)) return new EmailNotification();
        else if ("sms".equalsIgnoreCase(type)) return new SmsNotification();
        throw new IllegalArgumentException("Invalid type");
    }
}

@Service
public class NotificationService {

    private final NotificationFactory factory;

    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }

    public void notify(String type, String message) {
        Notification notification = factory.getNotification(type);
        notification.send(message);
    }
}
```

---

## 📌 장점 & 단점

| 장점 | 단점 |
|------|------|
| 객체 생성 방식 변경 시 클라이언트 코드 수정 불필요 | 클래스 수 증가로 복잡도 증가 가능 |
| 코드의 유지보수성과 확장성 향상 | 단순한 객체 생성에는 오히려 과할 수 있음 |
| 의존성 분리로 유연한 설계 가능 | 조건문(if/switch) 남발 시 가독성 저하 |

---

## ✅ 정리

- 팩토리 패턴은 객체 생성 로직을 숨기고, 클라이언트가 필요한 객체를 쉽게 사용할 수 있도록 해준다.
- Spring에서는 BeanFactory, FactoryBean, 그리고 직접 구현한 Factory 클래스를 통해 이 패턴을 활용한다.
- 유지보수성과 유연성을 높이는 데 매우 유리하지만, 남발하면 오히려 복잡해질 수 있다.