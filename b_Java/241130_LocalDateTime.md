# Java의 `LocalDateTime` 클래스

https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html

Java 8에서 도입된 **`LocalDateTime` 클래스**는 날짜와 시간을 나타내는 **불변 객체(Immutable Object)**입니다. 이 클래스는 시간대 정보 없이 날짜와 시간을 관리하며, `java.time` 패키지에 속합니다. `LocalDateTime`은 기존의 `Date` 및 `Calendar` 클래스보다 사용법이 간단하고 직관적입니다.

---

## `LocalDateTime` 클래스의 특징

1. **시간대 미지원**
    - 시간대가 필요 없는 애플리케이션에서 적합하며, 시간대 정보를 포함하지 않습니다.

2. **날짜와 시간 관리**
    - 날짜(`LocalDate`)와 시간(`LocalTime`)을 함께 관리하는 기능 제공.

3. **불변성**
    - `LocalDateTime` 객체는 생성된 이후 변경할 수 없습니다. 모든 변경 작업은 새로운 객체를 반환합니다.

4. **ISO-8601 표준 준수**
    - 기본적으로 `ISO-8601` 표준 형식을 사용합니다.

---

## `LocalDateTime` 객체 생성

### 1. 현재 날짜와 시간 생성
`now()` 메서드를 사용하여 현재 날짜와 시간을 가져옵니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("현재 날짜와 시간: " + now);
    }
}
```

### 2. 특정 날짜와 시간 생성
`of()` 메서드를 사용하여 특정 날짜와 시간을 지정할 수 있습니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 28, 15, 30, 45);
        System.out.println("특정 날짜와 시간: " + dateTime);
    }
}
```

### 3. 문자열 파싱
`LocalDateTime.parse()` 메서드를 사용하여 문자열을 `LocalDateTime` 객체로 변환합니다.
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String dateTimeStr = "2024-11-28T15:30:45";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        System.out.println("파싱된 날짜와 시간: " + dateTime);

        // 사용자 정의 형식
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String customDateStr = "2024/11/28 15:30:45";
        LocalDateTime customDateTime = LocalDateTime.parse(customDateStr, formatter);
        System.out.println("사용자 정의 형식 파싱: " + customDateTime);
    }
}
```

---

## 주요 메서드

### 1. 날짜와 시간 가져오기
각 구성 요소를 가져올 수 있습니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        int year = now.getYear();
        int month = now.getMonthValue();  // 월 (1 ~ 12)
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        System.out.println("년도: " + year);
        System.out.println("월: " + month);
        System.out.println("일: " + day);
        System.out.println("시: " + hour);
        System.out.println("분: " + minute);
        System.out.println("초: " + second);
    }
}
```

### 2. 날짜와 시간 수정
`with` 메서드를 사용하여 특정 필드를 수정합니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();

        // 연도를 2025로 변경
        LocalDateTime updatedYear = dateTime.withYear(2025);

        // 월을 12월로 변경
        LocalDateTime updatedMonth = dateTime.withMonth(12);

        System.out.println("변경된 연도: " + updatedYear);
        System.out.println("변경된 월: " + updatedMonth);
    }
}
```

### 3. 날짜와 시간 계산
`plus` 또는 `minus` 메서드를 사용하여 값을 더하거나 뺄 수 있습니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();

        LocalDateTime nextWeek = dateTime.plusWeeks(1);  // 1주일 후
        LocalDateTime yesterday = dateTime.minusDays(1); // 1일 전

        System.out.println("1주일 후: " + nextWeek);
        System.out.println("1일 전: " + yesterday);
    }
}
```

### 4. 날짜 비교
`isBefore`와 `isAfter` 메서드를 사용하여 날짜 비교를 수행합니다.
```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime future = now.plusDays(10);

        System.out.println("현재가 미래 이전인가? " + now.isBefore(future));
        System.out.println("현재가 미래 이후인가? " + now.isAfter(future));
    }
}
```

---

## 날짜/시간 포맷팅

### 1. 기본 포맷팅
`toString()` 메서드로 기본 `ISO-8601` 형식 출력.
```java
LocalDateTime now = LocalDateTime.now();
System.out.println("기본 포맷: " + now); // 예: 2024-11-28T15:30:45
```

### 2. 사용자 정의 포맷
`DateTimeFormatter`를 사용하여 포맷을 지정합니다.
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        // 사용자 정의 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        System.out.println("사용자 정의 포맷: " + formattedDate);
    }
}
```

---

## `LocalDateTime`과 다른 클래스 비교

### 1. `LocalDate`
- 날짜만 처리하며, 시간 정보는 포함되지 않음.
```java
import java.time.LocalDate;

LocalDate date = LocalDate.now();
```

### 2. `LocalTime`
- 시간만 처리하며, 날짜 정보는 포함되지 않음.
```java
import java.time.LocalTime;

LocalTime time = LocalTime.now();
```

### 3. `ZonedDateTime`
- 날짜, 시간, 시간대 정보를 포함.
```java
import java.time.ZonedDateTime;

ZonedDateTime zonedDateTime = ZonedDateTime.now();
```

---

## 주요 사용 사례

1. **로그 시간 기록**
   ```java
   LocalDateTime now = LocalDateTime.now();
   System.out.println("로그 시간: " + now);
   ```

2. **스케줄 관리**
   ```java
   LocalDateTime meeting = LocalDateTime.of(2024, 12, 25, 10, 0);
   System.out.println("미팅 시간: " + meeting);
   ```

3. **유효 기간 계산**
   ```java
   LocalDateTime expiration = LocalDateTime.now().plusDays(30);
   System.out.println("유효 기간: " + expiration);
   ```

---

## 장점과 단점

### 장점
1. **불변성**: 스레드 안전하며 안전한 프로그래밍 가능.
2. **명확한 API**: 날짜와 시간 조작이 직관적.
3. **시간대 관련 오류 방지**: 시간대 없이 날짜/시간만 필요할 때 적합.

### 단점
1. **시간대 정보 부족**: 시간대를 처리해야 한다면 `ZonedDateTime`이나 `OffsetDateTime`을 사용해야 함.
2. **레거시 API와의 호환성 문제**: `Date`나 `Calendar`와 함께 사용할 경우 변환 필요.

---

## 결론

`LocalDateTime` 클래스는 시간대가 필요 없는 날짜 및 시간 데이터를 다룰 때 강력하고 직관적인 API를 제공합니다. 기존 `Date`와 `Calendar` 클래스보다 현대적인 설계를 가지고 있으며, 스레드 안전성과 불변성 덕분에 유지보수가 용이합니다. 필요에 따라 다른 `java.time` API와 결합하여 더욱 효율적으로 사용할 수 있습니다.