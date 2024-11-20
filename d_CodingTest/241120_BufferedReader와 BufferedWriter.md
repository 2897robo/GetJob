# Java `BufferedReader`와 `BufferedWriter`

## 개요
Java의 `BufferedReader`와 `BufferedWriter`는 입출력(I/O)을 효율적으로 처리하기 위해 사용되는 클래스입니다.  
대량의 데이터 읽기/쓰기 작업에서 성능을 향상시키는 버퍼링 기능을 제공합니다.

---

## `BufferedReader`

### 개념
`BufferedReader`는 문자 입력 스트림에 버퍼링을 추가하여 데이터를 효율적으로 읽을 수 있도록 도와줍니다.

### 특징
- 데이터를 줄 단위로 읽거나, 특정 크기의 문자를 한 번에 읽는 데 적합.
- 내부 버퍼를 사용하여 입력 성능을 개선.

### 생성 방법
```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

- **`InputStreamReader`**: 바이트 스트림을 문자 스트림으로 변환.
- **`BufferedReader`**: 입력 스트림에 버퍼링 기능 추가.

### 주요 메서드
| 메서드                  | 설명                                         |
|-------------------------|----------------------------------------------|
| `String readLine()`     | 한 줄을 읽어서 반환 (더 이상 읽을 데이터가 없으면 `null`). |
| `void close()`          | 스트림을 닫아 리소스 해제.                   |

### 예제 코드
```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int n = Integer.parseInt(br.readLine());
String input = br.readLine();
br.close();
```

---

## `BufferedWriter`

### 개념
`BufferedWriter`는 문자 출력 스트림에 버퍼링을 추가하여 데이터를 효율적으로 출력할 수 있도록 도와줍니다.

### 특징
- 데이터를 한 번에 출력하지 않고 버퍼에 저장한 뒤, 버퍼가 가득 차거나 `flush()` 호출 시 출력.
- 대량의 데이터 출력에 적합.

### 생성 방법
```java
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
```

- **`OutputStreamWriter`**: 문자 스트림을 바이트 스트림으로 변환.
- **`BufferedWriter`**: 출력 스트림에 버퍼링 기능 추가.

### 주요 메서드
| 메서드                | 설명                                         |
|-----------------------|----------------------------------------------|
| `void write(String)`  | 문자열을 출력.                              |
| `void newLine()`      | 줄바꿈 추가 (플랫폼 독립적).                |
| `void flush()`        | 버퍼에 저장된 내용을 강제로 출력.            |
| `void close()`        | 스트림을 닫아 리소스 해제.                   |

### 예제 코드
```java
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
bw.write("Hello, World!");
bw.newLine();
bw.flush();
bw.close();
```

---

## 주요 사용 사례

### 대량의 정수 입력과 정렬 후 출력
```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        // 배열 정렬
        Arrays.sort(array);

        // 출력하기
        for (int num : array) {
            bw.write(num + "\n");
        }

        // 스트림 닫기
        br.close();
        bw.flush();
        bw.close();
    }
}
```

---

## `BufferedReader`와 `BufferedWriter` 사용 시 유의점
1. **리소스 관리**: `close()` 메서드를 호출하여 스트림 리소스를 반드시 해제해야 함.
2. **예외 처리**: I/O 작업 중 발생하는 예외를 `try-catch`로 처리하거나 `throws`로 선언.
3. **플랫폼 독립성**: `BufferedWriter.newLine()`을 사용하면 OS에 맞는 줄바꿈 문자 처리 가능.

---

## 참고
- [Java 공식 문서 - `BufferedReader`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedReader.html)
- [Java 공식 문서 - `BufferedWriter`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedWriter.html)