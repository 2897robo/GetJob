# Java 예외 처리 (Exception Handling)

![](https://i.ibb.co/gDGvc1R/image.png)

예외 처리는 프로그램 실행 중 발생할 수 있는 오류를 효과적으로 처리하여 비정상적인 종료를 방지하는 기법입니다. Java는 예외를 객체로 관리하며, 이를 통해 오류 발생 시 적절한 조치를 취할 수 있도록 다양한 예외 클래스와 예외 처리 문법을 제공합니다.

---

## 1. 예외 처리의 필요성

프로그램 실행 중 예외가 발생할 수 있는 대표적인 사례는 다음과 같습니다:

- **파일 입출력 오류**: 파일이 존재하지 않거나 접근 권한이 없는 경우.
- **네트워크 문제**: 서버 연결 실패, 네트워크 지연 등.
- **사용자 입력 오류**: 사용자가 잘못된 데이터를 입력한 경우.
- **리소스 부족**: 메모리 부족, 디스크 공간 부족 등.

이러한 오류를 적절히 처리하지 않으면 프로그램이 예기치 않게 종료될 수 있으며, 예외 처리를 통해 이를 방지하고 적절한 피드백을 제공할 수 있습니다.

---

## 2. 예외의 분류

Java의 예외는 크게 세 가지 유형으로 구분됩니다:

### 2.1 Checked Exception (검사 예외)
- **컴파일 시점**에 예외 처리 여부를 강제하는 예외.
- 반드시 `try-catch` 블록으로 처리하거나 `throws` 절을 사용해야 함.
- **예제**: `IOException`, `SQLException`, `FileNotFoundException` 등.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("nonexistent.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
    }
}
```

### 2.2 Unchecked Exception (비검사 예외)
- **런타임 시점**에 발생하는 예외로, 예외 처리를 강제하지 않음.
- 주로 프로그래밍 오류로 인해 발생.
- **예제**: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException` 등.

```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과!");
        }
    }
}
```

### 2.3 Error (오류)
- 프로그램에서 복구할 수 없는 심각한 문제.
- **예제**: `OutOfMemoryError`, `StackOverflowError` 등.

---

## 3. 예외 처리 방법

### 3.1 `try-catch` 블록
```java
try {
    int result = 10 / 0; // ArithmeticException 발생
} catch (ArithmeticException e) {
    System.out.println("산술 오류 발생!");
} catch (Exception e) {
    System.out.println("알 수 없는 오류 발생!");
}
```

### 3.2 `throws` 절
메소드에서 발생할 수 있는 예외를 호출자에게 알림.

```java
public static int readFileSize(String fileName) throws IOException {
    return 0;
}
```

### 3.3 `throw` 문
예외를 명시적으로 발생시킬 때 사용.

```java
public static void validate(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("나이는 음수가 될 수 없습니다.");
    }
}
```

### 3.4 `finally` 블록
예외 발생 여부와 관계없이 항상 실행되는 코드.

```java
Scanner scanner = null;
try {
    scanner = new Scanner(new File("data.txt"));
} catch (FileNotFoundException e) {
    System.out.println("파일을 찾을 수 없습니다.");
} finally {
    if (scanner != null) {
        scanner.close();
    }
}
```

### 3.5 Try-with-Resources (Java 7 이상)
자동으로 `close()` 메서드를 호출하여 리소스를 정리.

```java
try (Scanner scanner = new Scanner(new File("data.txt"))) {
    while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

---

## 4. Checked vs Unchecked 예외 비교

| 비교 항목 | Checked Exception | Unchecked Exception |
|-----------|------------------|------------------|
| 발생 시점 | 컴파일 타임 | 런타임 |
| 예외 처리 강제 여부 | 강제됨 (`try-catch` 또는 `throws`) | 강제되지 않음 |
| 주요 원인 | 외부 환경 문제 (파일, DB, 네트워크 등) | 개발자의 코드 오류 |
| 예제 | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |

---

## 5. 예외 처리 Best Practices

1. **필요한 경우에만 Checked Exception 사용**
    - 불필요한 Checked Exception은 코드의 복잡성을 증가시킬 수 있음.

2. **구체적인 예외를 처리**
    - `catch (Exception e) {}`보다는 `catch (FileNotFoundException e) {}`와 같이 특정 예외를 명확하게 처리.

3. **`finally` 블록으로 자원 해제 보장**
    - 파일, 데이터베이스 연결 등 반드시 닫아야 하는 리소스가 있다면 `finally` 블록에서 처리.

4. **의미 있는 예외 메시지 제공**
    - `throw new IllegalArgumentException("나이는 0보다 커야 합니다.");`

5. **로깅을 활용하여 예외 추적**
    - `e.printStackTrace();` 대신 `Logger`를 사용하여 예외 기록.

---

## **6. 사용자 정의 예외 (Custom Exception)**

Java에서는 `Exception` 클래스를 상속하여 사용자 정의 예외(Custom Exception)를 만들 수 있습니다.  
이는 특정한 비즈니스 로직에서 발생하는 예외를 명확하게 구분하고 처리하는 데 유용합니다.

---

### **6.1 사용자 정의 예외의 필요성**
기본 제공되는 예외 클래스 (`NullPointerException`, `IOException` 등)만으로는 특정한 도메인 로직을 표현하기 어려울 때가 있습니다.  
예를 들어, 은행 시스템에서 잔액이 부족할 때 발생하는 `InsufficientFundsException`을 직접 정의할 수 있습니다.

---

### **6.2 사용자 정의 예외 만들기**
사용자 정의 예외는 `Exception` (Checked 예외) 또는 `RuntimeException` (Unchecked 예외)을 상속하여 생성할 수 있습니다.

---

#### **예제 1: Checked Exception (컴파일러가 예외 처리를 강제)**
```java
// Checked 예외: 반드시 예외 처리를 해야 함
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("잔액 부족: 현재 잔액은 " + balance + "원입니다.");
        }
        balance -= amount;
    }
}

public class CustomCheckedExceptionExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000);

        try {
            account.withdraw(10000); // 예외 발생
        } catch (InsufficientFundsException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
```
**출력 결과**:
```
예외 발생: 잔액 부족: 현재 잔액은 5000원입니다.
```
✅ `InsufficientFundsException`은 **Checked Exception**으로, `throws` 선언이 필요하고 `try-catch`로 처리해야 합니다.

---

#### **예제 2: Unchecked Exception (예외 처리를 강제하지 않음)**
```java
// Unchecked 예외: 예외 처리를 강제하지 않음
class NegativeValueException extends RuntimeException {
    public NegativeValueException(String message) {
        super(message);
    }
}

class Payment {
    public void pay(int amount) {
        if (amount < 0) {
            throw new NegativeValueException("결제 금액은 음수가 될 수 없습니다: " + amount);
        }
        System.out.println(amount + "원이 결제되었습니다.");
    }
}

public class CustomUncheckedExceptionExample {
    public static void main(String[] args) {
        Payment payment = new Payment();

        payment.pay(-5000); // 예외 발생 (Unchecked Exception)
    }
}
```
**출력 결과**:
```
Exception in thread "main" NegativeValueException: 결제 금액은 음수가 될 수 없습니다: -5000
```
✅ `NegativeValueException`은 **Unchecked Exception**으로, `try-catch` 없이도 사용할 수 있으며 런타임에서 예외가 발생합니다.

---

### **6.3 Checked vs Unchecked 사용자 정의 예외**
| 비교 항목 | Checked Exception (`Exception` 상속) | Unchecked Exception (`RuntimeException` 상속) |
|-----------|---------------------------------|------------------------------------|
| 발생 시점 | 컴파일 타임 (예외 처리 강제) | 런타임 (예외 처리 선택) |
| 예외 처리 필요 여부 | `try-catch` 또는 `throws` 필수 | 선택적으로 처리 가능 |
| 사용 목적 | 외부 시스템, 파일, DB 오류 | 논리적 오류, 프로그래밍 실수 |
| 예제 | `IOException`, `SQLException` | `NullPointerException`, `IllegalArgumentException` |

---

### **6.4 사용자 정의 예외 Best Practices**
✅ **명확한 의미를 가진 예외 클래스를 정의하라.**  
❌ `throw new Exception("오류 발생");`  
✔ `throw new InsufficientFundsException("잔액 부족: 출금할 수 없음");`

✅ **기본 생성자와 메시지를 받는 생성자를 제공하라.**
```java
class CustomException extends Exception {
    public CustomException() {
        super("기본 예외 메시지");
    }

    public CustomException(String message) {
        super(message);
    }
}
```

✅ **Checked vs Unchecked 예외를 적절히 선택하라.**
- 복구 가능성이 있는 경우 → `Checked Exception`
- 개발자의 실수로 발생하는 경우 → `Unchecked Exception`

---

### **6.5 결론**
- 사용자 정의 예외를 활용하면 **더 명확한 의미를 가진 오류 처리**가 가능함.
- Checked 예외는 **외부 요소(DB, 네트워크, 파일)와 관련된 오류**에 적합함.
- Unchecked 예외는 **잘못된 값이나 논리적 오류**를 처리할 때 유용함.

✅ 적절한 예외 설계를 통해 가독성과 유지보수성을 높이자! 🚀