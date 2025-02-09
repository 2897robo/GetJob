# 예외 처리 (Exception Handling)

![](https://i.ibb.co/gDGvc1R/image.png)

예외 처리는 프로그램 실행 중 발생할 수 있는 다양한 오류 상황을 처리하고, 이를 통해 프로그램의 비정상적인 종료를 방지하는 메커니즘입니다. Java에서는 예외를 객체로 관리하며, 체계적이고 유연한 방식으로 오류를 처리할 수 있도록 다양한 클래스와 문법을 제공합니다.

---

## 왜 예외 처리가 필요한가?

프로그램은 실행 중 다양한 상황에서 예외를 만날 수 있습니다. 아래는 대표적인 사례입니다:

- **파일 입출력**: 파일이 없거나, 접근 권한이 없거나, 파일이 손상된 경우.
- **네트워크 연결**: 서버 연결 실패, 네트워크 지연, 잘못된 URL 등.
- **사용자 입력**: 사용자가 잘못된 형식의 데이터를 입력한 경우.
- **리소스 부족**: 메모리 부족, 디스크 공간 부족 등.

이러한 상황을 적절히 처리하지 않으면 프로그램은 예기치 않게 종료될 수 있습니다. 예외 처리를 통해 오류를 복구하거나, 사용자에게 적절한 피드백을 제공하여 안정적인 프로그램 실행을 보장할 수 있습니다.

---

## 예외의 분류

Java에서는 예외(Exceptions)를 효과적으로 처리하기 위해 두 가지 주요 유형으로 분류합니다. 각각의 예외는 **발생 원인**과 **처리 방식**이 다르며, 프로그램의 안정성을 높이고 오류 상황을 제어하는 데 중요한 역할을 합니다. 또한, 예외와 더불어 **Error**라는 별도의 시스템 오류도 존재합니다.

---

## **1. Checked Exception (검사 예외)**

### **정의**
Checked Exception은 **컴파일 시점**에 예외 처리 여부를 검사받아야 하는 예외입니다. 즉, 이러한 예외를 처리하지 않으면 컴파일 자체가 실패합니다. 이는 예외 상황이 **예측 가능**하며, 호출하는 쪽에서 이를 처리하거나 호출자에게 전달하는 방식으로 복구할 수 있도록 설계되었습니다.

### **특징**
- **컴파일러 강제**: 반드시 `try-catch` 블록 또는 `throws` 절을 사용해 예외를 처리해야 합니다.
- **복구 가능성**: 외부 자원(파일, 네트워크, 데이터베이스 등)과 상호작용할 때 발생할 가능성이 높은 예외.
- **예제**: `IOException`, `SQLException`, `ClassNotFoundException`, `FileNotFoundException` 등.

### **사용 사례**
Checked Exception은 주로 **외부 환경**과의 상호작용에서 발생할 수 있는 예외를 처리하는 데 적합합니다. 예를 들어, 파일 입출력, 네트워크 통신, 데이터베이스 접근 등 복구 가능한 상황에서 사용됩니다.

### **예제 코드**
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            // 파일을 읽으려고 시도
            Scanner scanner = new Scanner(new File("nonexistent.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
    }
}
```

위 코드는 파일이 존재하지 않을 경우 `FileNotFoundException`을 발생시키며, `try-catch` 블록으로 이를 처리합니다.

---

## **2. Unchecked Exception (비검사 예외)**

### **정의**
Unchecked Exception은 **런타임 시점**에 발생하는 예외로, 컴파일러가 예외 처리 여부를 강제하지 않습니다. 대부분 프로그래밍 실수 또는 논리적 오류로 인해 발생하며, 프로그램의 결함으로 간주되는 경우가 많습니다.

### **특징**
- **컴파일러 비강제**: 예외 처리를 강제하지 않으므로, 필요에 따라 개발자가 직접 처리하거나 예외를 무시할 수 있습니다.
- **주로 논리적 오류**: 잘못된 코드로 인해 발생하는 예외.
- **예제**: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `IllegalArgumentException` 등.

### **사용 사례**
Unchecked Exception은 주로 **프로그래밍 오류**와 관련된 상황에서 사용됩니다. 예를 들어, 잘못된 인덱스 접근, `null` 참조, 잘못된 메서드 인자 등이 포함됩니다.

### **예제 코드**
```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            // 잘못된 배열 인덱스 접근
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과!");
            e.printStackTrace();
        }

        try {
            String text = null;
            // Null 값을 참조하려고 시도
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("널 참조 오류!");
            e.printStackTrace();
        }
    }
}
```

---

## **3. Checked vs Unchecked: 언제 사용해야 할까?**

### **Checked Exception**
- 외부 환경과의 상호작용에서 발생할 가능성이 높은 예외.
- 복구 가능성이 높고, 호출자가 적절히 처리해야 하는 예외.
- 예:
    - 파일이 존재하지 않을 때: `FileNotFoundException`
    - 네트워크 연결이 끊겼을 때: `IOException`
    - 데이터베이스 연결 실패: `SQLException`

### **Unchecked Exception**
- 프로그래머의 실수로 인해 발생하는 예외.
- 복구 가능성이 낮고, 호출자가 처리하지 않아도 되는 예외.
- 예:
    - 잘못된 데이터 접근: `ArrayIndexOutOfBoundsException`
    - `null` 참조: `NullPointerException`
    - 잘못된 연산: `ArithmeticException`

### **결론**
Checked Exception은 복구 가능성이 높은 외부적인 문제를 처리할 때 사용하고, Unchecked Exception은 복구할 필요가 없는 코드의 논리적 오류를 처리하는 데 적합합니다.

---

## **4. Error와 Exception의 차이**

### **Error**
- JVM에서 발생하는 심각한 오류.
- 복구 불가능하며, 애플리케이션 코드에서 처리하지 않는 것이 일반적.
- 예: `OutOfMemoryError`, `StackOverflowError`, `VirtualMachineError`.

### **Exception**
- 프로그램 실행 중 발생하는 오류 상황.
- 복구 가능성이 있으며, `try-catch` 또는 `throws`로 처리 가능.
- Checked Exception과 Unchecked Exception으로 구분.

---

## **5. 혼합 예제: Checked와 Unchecked**

아래 코드는 Checked Exception과 Unchecked Exception을 함께 처리하는 방법을 보여줍니다.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MixedExceptionExample {
    public static void main(String[] args) {
        try {
            // Checked Exception: FileNotFoundException
            Scanner scanner = new Scanner(new File("example.txt"));

            // Unchecked Exception: ArrayIndexOutOfBoundsException
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과!");
            e.printStackTrace();
        } finally {
            System.out.println("예외 처리 완료.");
        }
    }
}
```

---

## **6. 예외 처리의 Best Practice**

1. **Checked Exception 처리**
    - 외부 자원과의 상호작용에서 예외를 호출자에게 명확히 전달.
    - 불필요한 예외 전파를 줄이고, 필요한 경우만 처리.

2. **Unchecked Exception 처리**
    - 코드 품질을 높여 예외를 예방.
    - 발생한 예외는 프로그램 종료 전 로깅(logging) 처리.

3. **`finally` 블록 사용**
    - 자원 정리(예: 파일 닫기, 네트워크 연결 해제 등) 보장.

4. **사용자 정의 예외**
    - 프로그램의 도메인에 적합한 의미 있는 예외 생성.

---

## 예외 처리 문법

### 1. `try/catch` 블록
`try` 블록 내부에서 예외가 발생하면 해당 예외를 처리할 수 있는 `catch` 블록으로 제어가 이동합니다.

#### 기본 구조:
```java
try {
    // 예외가 발생할 가능성이 있는 코드
} catch (예외타입 변수) {
    // 예외 처리 코드
}
```

#### 다중 `catch` 블록:
하나의 `try` 블록에 대해 여러 유형의 예외를 처리할 수 있습니다.

```java
try {
    int result = 10 / 0; // ArithmeticException 발생
} catch (ArithmeticException e) {
    System.out.println("산술 오류 발생!");
} catch (Exception e) {
    System.out.println("알 수 없는 오류 발생!");
}
```

#### 다중 예외 처리 (Java 7 이상):
`|` 연산자를 사용하여 여러 예외를 한 번에 처리할 수 있습니다.

```java
catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
    System.out.println("예외 발생: " + e.getMessage());
}
```

---

### 2. `throw` 문
`throw` 문을 사용하면 특정 예외를 명시적으로 발생시킬 수 있습니다.

```java
public static void validateDayNumber(int dayNumber) {
    if (dayNumber < 1 || dayNumber > 31) {
        throw new IllegalArgumentException("잘못된 날짜 입력: " + dayNumber);
    }
}
```

---

### 3. `throws` 절
메소드 선언부에 발생 가능한 예외를 명시합니다.

```java
public static int readFileSize(String fileName) throws IOException {
    // 파일 크기 읽기 로직
    return 0; // 예시
}
```

`throws` 절을 사용하는 메소드는 반드시 `try/catch`로 처리되거나 호출자에게 예외를 전달해야 합니다.

---

### 4. `finally` 블록
`try/catch` 블록의 실행 여부와 관계없이 항상 실행되는 코드를 작성할 때 사용됩니다. 주로 리소스 정리에 사용됩니다.

```java
Scanner scanner = null;
try {
    scanner = new Scanner(new File("data.txt"));
    // 파일 처리 코드
} catch (FileNotFoundException e) {
    System.out.println("파일을 찾을 수 없습니다.");
} finally {
    if (scanner != null) {
        scanner.close(); // 리소스 정리
    }
}
```

---

### 5. Try-with-Resources
Java 7 이상에서는 `try-with-resources`를 사용하여 자동으로 리소스를 정리할 수 있습니다. `AutoCloseable` 인터페이스를 구현한 객체는 `try` 블록이 끝날 때 자동으로 `close()` 메소드가 호출됩니다.

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

## 결론
Java의 예외 처리 메커니즘을 적절히 사용하면 코드의 안정성과 가독성을 높일 수 있습니다. Checked 예외와 Unchecked 예외를 이해하고, 상황에 맞는 예외 처리 방법을 선택하는 것이 중요합니다.
