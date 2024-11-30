# Java의 `Calendar` 클래스

https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

Java의 **`Calendar` 클래스**는 날짜와 시간을 보다 유연하게 다룰 수 있도록 설계된 추상 클래스입니다. 이는 `Date` 클래스의 한계를 보완하고 날짜와 시간의 조작을 가능하게 합니다. `Calendar` 클래스는 `java.util` 패키지에 속하며, **레거시 날짜/시간 API**에 포함됩니다.

---

## `Calendar` 클래스의 특징

1. **추상 클래스**
    - `Calendar` 자체는 추상 클래스이므로 직접 객체를 생성할 수 없습니다. 대신 `Calendar.getInstance()` 메서드를 통해 하위 클래스(일반적으로 `GregorianCalendar`)의 인스턴스를 생성합니다.

2. **달력 시스템**
    - 기본적으로 **그레고리안 달력(Gregorian Calendar)**을 사용하지만, 다른 달력 시스템으로 확장 가능합니다.

3. **날짜 조작 가능**
    - 연도, 월, 일, 시각 등의 값을 쉽게 설정, 변경, 계산할 수 있습니다.

4. **타임존 지원**
    - 시간대를 명시적으로 설정할 수 있습니다.

---

## `Calendar` 클래스의 생성 및 초기화

### 1. `Calendar.getInstance()`
- 현재 시스템의 지역 설정 및 시간대에 맞는 기본 `Calendar` 객체를 생성합니다.
```java
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime()); // 현재 날짜와 시간 출력
    }
}
```

### 2. 특정 시간대 지정
- `TimeZone` 클래스를 사용하여 특정 시간대를 설정할 수 있습니다.
```java
import java.util.Calendar;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        System.out.println(calendar.getTime()); // UTC 시간 출력
    }
}
```

---

## 주요 필드

`Calendar` 클래스는 날짜와 시간을 구성하는 다양한 필드를 제공합니다.

| 필드                       | 설명                   |
|----------------------------|------------------------|
| `Calendar.YEAR`            | 연도                   |
| `Calendar.MONTH`           | 월 (0 ~ 11)            |
| `Calendar.DATE`            | 일                     |
| `Calendar.HOUR`            | 시 (12시간제, 0 ~ 11)  |
| `Calendar.HOUR_OF_DAY`     | 시 (24시간제, 0 ~ 23)  |
| `Calendar.MINUTE`          | 분                     |
| `Calendar.SECOND`          | 초                     |
| `Calendar.DAY_OF_WEEK`     | 요일 (1 = 일요일)      |
| `Calendar.DAY_OF_YEAR`     | 해당 연도의 몇 번째 날  |

---

## 주요 메서드

### 1. 날짜 및 시간 정보 가져오기
`get(int field)` 메서드를 사용하여 특정 필드 값을 가져옵니다.
```java
Calendar calendar = Calendar.getInstance();

int year = calendar.get(Calendar.YEAR);
int month = calendar.get(Calendar.MONTH) + 1; // 0부터 시작하므로 +1
int day = calendar.get(Calendar.DATE);

System.out.println("Year: " + year);
System.out.println("Month: " + month);
System.out.println("Day: " + day);
```

### 2. 날짜 및 시간 설정
`set(int field, int value)` 메서드로 특정 필드 값을 설정할 수 있습니다.
```java
Calendar calendar = Calendar.getInstance();

// 연도, 월(0부터 시작), 일 설정
calendar.set(Calendar.YEAR, 2024);
calendar.set(Calendar.MONTH, 10); // 11월 (0 = 1월)
calendar.set(Calendar.DATE, 15);

System.out.println(calendar.getTime()); // Fri Nov 15 00:00:00 KST 2024
```

---

### 3. 날짜 조작
`add(int field, int amount)` 또는 `roll(int field, int amount)` 메서드로 날짜를 조작할 수 있습니다.

#### `add`
- 특정 필드의 값을 더하거나 뺍니다.
- 다른 필드에 영향을 줍니다(예: 월을 넘기면 연도 변경).
```java
Calendar calendar = Calendar.getInstance();
calendar.add(Calendar.DATE, 10); // 현재 날짜에 10일 더하기
System.out.println(calendar.getTime());
```

#### `roll`
- 특정 필드의 값을 더하거나 빼지만, 다른 필드에 영향을 주지 않습니다.
```java
Calendar calendar = Calendar.getInstance();
calendar.roll(Calendar.DATE, 10); // 10일 더하기 (월 변경 없음)
System.out.println(calendar.getTime());
```

---

### 4. 날짜 비교
`Calendar` 클래스는 날짜 비교를 위한 다양한 메서드를 제공합니다.

#### `before`와 `after`
- 두 날짜를 비교하여 이전인지 이후인지 확인합니다.
```java
Calendar calendar1 = Calendar.getInstance();
Calendar calendar2 = Calendar.getInstance();
calendar2.add(Calendar.DATE, 5); // 5일 후

System.out.println(calendar1.before(calendar2)); // true
System.out.println(calendar1.after(calendar2));  // false
```

#### `compareTo`
- 날짜를 비교하여 정수 값을 반환합니다.
    - 양수: 호출한 객체가 이후.
    - 0: 동일한 날짜.
    - 음수: 호출한 객체가 이전.
```java
int result = calendar1.compareTo(calendar2);
System.out.println(result); // -1
```

---

## 포맷팅과 `Date` 변환

### 1. `getTime()`
`Date` 객체로 변환합니다.
```java
Calendar calendar = Calendar.getInstance();
Date date = calendar.getTime();
System.out.println(date); // Date 형식 출력
```

### 2. `SimpleDateFormat`과 함께 사용
`Calendar` 객체의 날짜를 포맷팅하려면 `SimpleDateFormat`을 사용합니다.
```java
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime())); // 2024-11-28 12:34:56
    }
}
```

---

## 타임존 설정

### 1. 기본 타임존 확인
```java
Calendar calendar = Calendar.getInstance();
System.out.println(calendar.getTimeZone().getID()); // 시스템 기본 시간대 출력
```

### 2. 특정 타임존 설정
```java
Calendar calendar = Calendar.getInstance();
calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
System.out.println(calendar.getTime()); // UTC 기준 시간 출력
```

---

## `Calendar` 클래스의 한계와 대안

### 1. 한계
- **복잡한 사용법**: 단순한 날짜 조작에도 여러 메서드 호출 필요.
- **비직관적인 필드**: 월이 0부터 시작하는 등 직관적이지 않음.
- **스레드 안전성 문제**: 다중 스레드 환경에서 적합하지 않음.

### 2. 대안: `java.time` API
- **`LocalDate`, `LocalDateTime`, `ZonedDateTime` 등**은 더 직관적이고 강력한 기능을 제공합니다.

**예제 (LocalDateTime)**:
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(formatter)); // 2024-11-28 12:34:56
    }
}
```

---

## 결론

`Calendar` 클래스는 `Date` 클래스의 단점을 보완하고 날짜 및 시간 조작 기능을 제공합니다. 하지만 현대적인 요구를 충족하기에는 부족한 점이 많으며, Java 8 이후에는 `java.time` 패키지를 사용하는 것이 권장됩니다. 그러나 레거시 코드와의 호환성을 위해 `Calendar` 클래스의 기본 사용법을 이해하는 것은 여전히 중요합니다.