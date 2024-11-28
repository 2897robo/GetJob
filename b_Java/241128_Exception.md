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

Java에서는 예외를 크게 세 가지로 분류합니다.

### 1. Checked 예외 (Checked Exception)
Checked 예외는 **컴파일 시점에 처리 여부를 확인해야 하는 예외**입니다. 이는 복구 가능성이 높은 예외로, 반드시 `try/catch` 블록으로 처리하거나 `throws` 절을 사용하여 호출자에게 예외를 전달해야 합니다.

#### 예제:
- **FileNotFoundException**: 존재하지 않는 파일에 접근할 경우 발생.
- **ClassNotFoundException**: 클래스 로딩 시 클래스를 찾을 수 없을 때 발생.

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

### 2. Unchecked 예외 (Unchecked Exception)
Unchecked 예외는 **RuntimeException 클래스의 하위 클래스**로, 명시적으로 예외 처리를 강제하지 않습니다. 이는 대부분 프로그래밍 오류로 인해 발생하며, 개발자가 예측 가능한 경우가 많습니다.

#### 예제:
- **ArrayIndexOutOfBoundsException**: 배열의 유효 범위를 벗어난 인덱스 접근 시 발생.
- **NullPointerException**: `null` 값을 참조하려고 할 때 발생.

```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // ArrayIndexOutOfBoundsException 발생
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과!");
            e.printStackTrace();
        }
    }
}
```

### 3. Error
Error는 **심각한 시스템 수준의 문제**를 나타내며, 일반적으로 프로그램에서 복구할 수 없습니다. 예를 들어 메모리 부족(OutOfMemoryError)과 같은 문제가 발생하면 시스템 수준에서 개입이 필요합니다.

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
