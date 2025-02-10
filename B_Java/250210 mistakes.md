# 자바 개발자가 피해야 하는 실수

## 1. Null 반환을 피하고 Optional 사용하기
**잘못된 예:**
메서드에서 `null`을 반환하면 `NullPointerException`(NPE)을 유발할 수 있다.

```java
public String getUserName(User user) {
    if (user == null) {
        return null;
    }
    return user.getName();
}
```

**올바른 예:**
`Optional`을 사용하여 보다 명확한 null 처리 방법을 제공한다.

```java
import java.util.Optional;

public Optional<String> getUserName(User user) {
    return Optional.ofNullable(user).map(User::getName);
}
```

---

## 2. 문자열 변환 시 `String.valueOf()` 사용하기
**잘못된 예:**
`+` 연산자를 사용하면 불필요한 객체가 생성될 수 있다.

```java
String result = "숫자: " + number;
```

**올바른 예:**
`String.valueOf()`를 사용하여 성능을 최적화한다.

```java
String result = "숫자: " + String.valueOf(number);
```

---

## 3. 배열 복사 시 `Arrays.copyOf()` 사용하기
**잘못된 예:**
반복문을 사용하여 수동으로 배열을 복사하는 것은 비효율적이다.

```java
int[] source = {1, 2, 3};
int[] destination = new int[source.length];
for (int i = 0; i < source.length; i++) {
    destination[i] = source[i];
}
```

**올바른 예:**
`Arrays.copyOf()`를 사용하여 더 간결하고 효율적으로 처리한다.

```java
import java.util.Arrays;

int[] source = {1, 2, 3};
int[] destination = Arrays.copyOf(source, source.length);
```

---

## 4. 컬렉션의 빈 상태 확인 시 `isEmpty()` 사용하기
**잘못된 예:**
`size()` 또는 `length()`를 사용하여 빈 상태를 확인하는 것은 가독성이 떨어진다.

```java
if (list.size() == 0) {
    // 리스트가 비어 있음
}
```

**올바른 예:**
`isEmpty()`를 사용하여 더 명확하게 표현한다.

```java
if (list.isEmpty()) {
    // 리스트가 비어 있음
}
```

---

## 5. `ConcurrentModificationException` 방지하기
**잘못된 예:**
리스트를 순회하면서 요소를 제거하면 `ConcurrentModificationException`이 발생할 수 있다.

```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String item : list) {
    if (item.equals("A")) {
        list.remove(item);
    }
}
```

**올바른 예:**
반복자의 `remove()` 메서드 또는 `removeIf()`를 사용하여 안전하게 제거한다.

```java
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    if (iterator.next().equals("A")) {
        iterator.remove();
    }
}
```

또는 Java 8 이상에서는 `removeIf()`를 활용한다.

```java
list.removeIf(item -> item.equals("A"));
```

---

## 6. 정규 표현식 성능 최적화
**잘못된 예:**
반복적으로 정규식을 컴파일하면 성능 저하가 발생한다.

```java
String pattern = "[0-9]+";
boolean matches = "12345".matches(pattern);
```

**올바른 예:**
미리 정규식을 컴파일하여 성능을 향상시킨다.

```java
import java.util.regex.Pattern;

Pattern pattern = Pattern.compile("[0-9]+");
boolean matches = pattern.matcher("12345").matches();
```

---

## 7. 데이터 존재 여부 사전 검사 지양하기
**잘못된 예:**
Map에서 값을 가져오기 전에 `containsKey()`를 사용하는 것은 불필요한 검사이다.

```java
if (map.containsKey(id)) {
    Role role = map.get(id);
}
```

**올바른 예:**
`get()` 메서드를 사용한 후 null 확인을 수행한다.

```java
Role role = map.get(id);
if (role != null) {
    // 역할 존재
}
```

---

## 8. 컬렉션을 배열로 변환할 때 성능 최적화
**잘못된 예:**
배열 크기를 미리 계산하는 것은 불필요한 작업이다.

```java
String[] array = list.toArray(new String[list.size()]);
```

**올바른 예:**
배열 크기를 `0`으로 설정하면 내부적으로 자동 조정된다.

```java
String[] array = list.toArray(new String[0]);
```

---

## 9. 인터페이스에 `default` 메서드 활용
**잘못된 예:**
인터페이스에 새로운 메서드를 추가하면 모든 구현 클래스에서 변경이 필요하다.

```java
public interface Logger {
    void log(String message);
}
```

**올바른 예:**
`default` 메서드를 추가하면 기존 구현을 변경하지 않고도 기능을 확장할 수 있다.

```java
public interface Logger {
    void log(String message);
    default void logError(String error) {
        log("ERROR: " + error);
    }
}
```

---

## 10. `Date` 대신 `LocalDate` 사용하기
**잘못된 예:**
`Date` 클래스는 불변성이 없고, 많은 메서드가 deprecated 되었다.

```java
Date date = new Date();
```

**올바른 예:**
Java 8의 `LocalDate`를 사용하여 더 안전하고 직관적인 코드를 작성한다.

```java
import java.time.LocalDate;

LocalDate date = LocalDate.now();
```

---

## 11. 제네릭을 사용하여 타입 안정성 확보
**잘못된 예:**
제네릭을 사용하지 않으면 런타임 오류 가능성이 높아진다.

```java
ArrayList list = new ArrayList();
list.add(10);
list.add("Hello");
```

**올바른 예:**
제네릭을 사용하여 타입 안정성을 확보한다.

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
```

---

