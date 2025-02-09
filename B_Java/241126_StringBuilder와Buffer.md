# StringBuffer와 StringBuilder 클래스의 차이점

## 개요
`StringBuffer`와 `StringBuilder`는 Java에서 문자열을 동적으로 조작하기 위한 클래스입니다. String 클래스와 달리 이 두 클래스는 가변(mutable) 문자열을 지원하여 메모리를 효율적으로 사용할 수 있습니다.

## 1. 스레드 안전성

### StringBuffer
- 모든 public 메소드에 `synchronized` 키워드가 적용되어 있습니다.
- 멀티스레드 환경에서 안전하게 사용할 수 있습니다.
- 동기화로 인한 성능 저하가 있지만, 데이터 무결성이 보장됩니다.
- 내부적으로 synchronized 블록을 사용하여 임계 영역(Critical Section)을 보호합니다.

### StringBuilder
- 동기화를 지원하지 않아 멀티스레드 환경에서 안전하지 않습니다.
- 동기화 처리가 없어 더 빠른 성능을 제공합니다.
- 단일 스레드 환경에서 최적의 선택입니다.

## 2. 성능 특성

### StringBuffer
```java
// 성능 측정 예제
long startTime = System.currentTimeMillis();
StringBuffer buffer = new StringBuffer();
for (int i = 0; i < 1000000; i++) {
    buffer.append("test");
}
long endTime = System.currentTimeMillis();
System.out.println("StringBuffer: " + (endTime - startTime) + "ms");
```

### StringBuilder
```java
// 성능 측정 예제
long startTime = System.currentTimeMillis();
StringBuilder builder = new StringBuilder();
for (int i = 0; i < 1000000; i++) {
    builder.append("test");
}
long endTime = System.currentTimeMillis();
System.out.println("StringBuilder: " + (endTime - startTime) + "ms");
```

일반적으로 StringBuilder가 StringBuffer보다 15-20% 더 빠른 성능을 보입니다.

## 3. 주요 메서드 비교

### 공통 메서드
1. **append() 메서드**
   ```java
   // 다양한 타입의 데이터 추가
   buffer.append("Hello");
   buffer.append(123);
   buffer.append(true);
   ```

2. **insert() 메서드**
   ```java
   // 특정 위치에 문자열 삽입
   buffer.insert(5, " World");
   ```

3. **delete() 메서드**
   ```java
   // 특정 범위의 문자열 삭제
   buffer.delete(5, 10);
   ```

4. **reverse() 메서드**
   ```java
   // 문자열 뒤집기
   buffer.reverse();
   ```

5. **substring() 메서드**
   ```java
   // 부분 문자열 추출
   String sub = buffer.substring(0, 5);
   ```

## 4. 메모리 관리

### 초기 용량 설정
```java
// 초기 용량 지정
StringBuffer buffer = new StringBuffer(32);
StringBuilder builder = new StringBuilder(32);
```

### 용량 자동 확장
- 둘 다 내부적으로 char[] 배열을 사용합니다.
- 기본 용량은 16문자입니다.
- 용량이 부족할 경우 자동으로 확장됩니다 (현재 용량 + 2).

## 5. 사용 시나리오

### StringBuffer 사용이 적합한 경우
- 멀티스레드 환경에서 문자열을 조작할 때
- 데이터의 안전성이 중요한 경우
- 웹 애플리케이션의 공유 리소스를 다룰 때

### StringBuilder 사용이 적합한 경우
- 단일 스레드 환경에서 문자열을 조작할 때
- 성능이 중요한 경우
- 로컬 변수로 사용할 때
- 대용량 문자열 연산이 필요할 때

## 6. 기타 특징

### String과의 비교
```java
// String concatenation
String str = "Hello";
str = str + " World";  // 새로운 객체 생성

// StringBuilder 사용
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");   // 기존 객체 수정
```

### 형식화된 출력
```java
// StringBuffer/StringBuilder에서 형식화된 문자열 생성
StringBuilder sb = new StringBuilder();
Formatter formatter = new Formatter(sb);
formatter.format("Name: %s, Age: %d", "John", 25);
```

## 결론
- 멀티스레드 환경: StringBuffer 사용
- 단일스레드 환경: StringBuilder 사용
- 성능이 중요한 경우: StringBuilder 사용
- 스레드 안전성이 필요한 경우: StringBuffer 사용

## 성능 최적화 팁
1. 적절한 초기 용량 설정으로 불필요한 버퍼 재할당 방지
2. 가능한 한 StringBuilder 사용 선호
3. 문자열 연결 작업이 많은 경우 + 연산자 대신 StringBuilder 사용
4. 반복문 내에서는 StringBuilder를 반복문 밖에서 생성