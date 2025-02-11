# SOLID 원칙과 객체지향 프로그래밍(OOP)

SOLID 원칙은 객체지향 프로그래밍(OOP)에서 유지보수성과 확장성을 높이는 데 도움을 주는 다섯 가지 설계 원칙입니다. 이 원칙들은 OOP의 네 가지 주요 개념인 **추상화, 캡슐화, 상속, 다형성**과 밀접하게 연결되어 있습니다.

---

## 객체지향 프로그래밍(OOP)의 네 가지 원칙

### 1. **추상화 (Abstraction)**
불필요한 세부 사항을 숨기고 중요한 것만 표현하는 개념으로, 인터페이스나 추상 클래스를 사용하여 설계합니다.

**SOLID 원칙과의 관계**
- **SRP(단일 책임 원칙)**: 추상화를 통해 클래스를 작은 단위로 나누고, 한 가지 책임만 수행하도록 합니다.
- **OCP(개방-폐쇄 원칙)**: 추상 클래스를 사용하여 기존 클래스를 수정하지 않고 새로운 기능을 추가할 수 있습니다.
- **ISP(인터페이스 분리 원칙)**: 인터페이스를 세분화하여 클라이언트가 불필요한 메서드에 의존하지 않도록 합니다.

---

### 2. **캡슐화 (Encapsulation)**
데이터(필드)와 메서드를 하나로 묶고, 외부에서 접근을 제한하는 개념입니다. **getter/setter** 메서드를 사용하여 데이터 보호를 강화합니다.

**SOLID 원칙과의 관계**
- **SRP(단일 책임 원칙)**: 캡슐화를 통해 클래스를 독립적인 단위로 유지하고, 역할을 분리할 수 있습니다.
- **OCP(개방-폐쇄 원칙)**: 내부 데이터를 직접 수정하지 않고 메서드를 통해 접근하도록 설계하면, 기능 확장 시 기존 클래스를 수정하지 않아도 됩니다.

```java
class BankAccount {
    private double balance;
    
    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
```

---

### 3. **상속 (Inheritance)**
기존 클래스를 확장하여 새로운 클래스를 만드는 개념으로, 코드 재사용성을 높이고 기능을 확장할 수 있습니다.

**SOLID 원칙과의 관계**
- **OCP(개방-폐쇄 원칙)**: 부모 클래스를 변경하지 않고 새로운 기능을 가진 자식 클래스를 추가하여 확장할 수 있습니다.
- **LSP(리스코프 치환 원칙)**: 자식 클래스가 부모 클래스를 대체할 수 있도록 올바른 상속 관계를 유지해야 합니다.

```java
// 부모 클래스
class Animal {
    void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
}

// 자식 클래스 (OCP, LSP 적용)
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("멍멍!");
    }
}
```

---

### 4. **다형성 (Polymorphism)**
하나의 인터페이스로 여러 가지 구현을 가능하게 하는 개념입니다. 상속과 함께 사용되며, 동일한 메서드를 다른 방식으로 실행할 수 있습니다.

**SOLID 원칙과의 관계**
- **LSP(리스코프 치환 원칙)**: 다형성을 통해 부모 클래스와 동일한 방식으로 자식 클래스를 사용할 수 있어야 합니다.
- **DIP(의존성 역전 원칙)**: 인터페이스를 사용하여 구현체가 아닌 추상 개념에 의존하도록 설계하면, 다양한 구현을 쉽게 교체할 수 있습니다.

```java
// 상위 개념 (추상화)
interface Vehicle {
    void move();
}

// 다양한 하위 구현체 (다형성 적용)
class Car implements Vehicle {
    public void move() {
        System.out.println("자동차가 도로를 달립니다.");
    }
}

class Airplane implements Vehicle {
    public void move() {
        System.out.println("비행기가 하늘을 납니다.");
    }
}
```

---

## SOLID 원칙 설명

### 1️⃣ 단일 책임 원칙 (SRP)
하나의 클래스는 하나의 책임만 가져야 하며, 변경 이유도 하나여야 합니다.

```java
public class User {
    private String name;
    private String email;
    // Getters and setters
}

public class EmailService {
    public void sendEmail(User user, String message) {
        // Code to send email
    }
}
```

---

### 2️⃣ 개방-폐쇄 원칙 (OCP)
기존 코드를 수정하지 않고 확장할 수 있도록 인터페이스를 사용합니다.

```java
public interface Shape {
    int calculateArea();
}

public class Rectangle implements Shape {
    private int width;
    private int height;

    @Override
    public int calculateArea() {
        return width * height;
    }
}
```

---

### 3️⃣ 리스코프 치환 원칙 (LSP)
자식 클래스가 부모 클래스를 대체할 수 있도록 올바르게 설계해야 합니다.

```java
public interface Flyable {
    void fly();
}

public class Sparrow implements Flyable {
    @Override
    public void fly() {
        System.out.println("새가 날아갑니다.");
    }
}
```

---

### 4️⃣ 인터페이스 분리 원칙 (ISP)
큰 인터페이스를 여러 개로 나누어 필요 없는 의존성을 줄입니다.

```java
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        // Human work implementation
    }
    
    @Override
    public void eat() {
        // Human eat implementation
    }
}
```

---

### 5️⃣ 의존성 역전 원칙 (DIP)
구체적인 클래스가 아닌 추상 클래스나 인터페이스에 의존해야 합니다.

```java
public interface Switchable {
    void turnOn();
    void turnOff();
}

public class LightBulb implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("전구가 켜졌습니다.");
    }
}
```

---

## 🔥 최종 정리

| OOP 원칙 | SOLID 원칙과의 관계 |
|----------|-----------------------------|
| **추상화** | SRP, ISP, OCP |
| **캡슐화** | SRP, OCP |
| **상속** | OCP, LSP |
| **다형성** | LSP, DIP |

OOP 개념이 SOLID 원칙을 지키는 데 핵심 역할을 하며, 이를 잘 이해하고 적용하면 유지보수성이 뛰어난 소프트웨어를 만들 수 있습니다! 🚀

