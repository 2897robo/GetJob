# Comparator

## 개요
`Comparator`는 자바에서 객체를 특정 기준으로 정렬할 때 사용하는 **인터페이스**입니다.  
`compare()` 메소드를 오버라이드하여 원하는 정렬 기준을 직접 정의할 수 있습니다.  
자바 표준 라이브러리인 `java.util` 패키지에 포함되어 있으며, 주로 `Collections.sort()`나 `Arrays.sort()`와 함께 사용됩니다.

---

## Comparator와 Comparable의 차이
### 1. Comparable
- **자체 정렬 기준**을 정의하기 위해 사용합니다.
- 정렬 기준이 객체의 **클래스 내부에 포함**되어 있습니다.
- 클래스는 `Comparable` 인터페이스를 구현하고, `compareTo()` 메소드를 오버라이드합니다.

#### 예시: Comparable 사용
```java
class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return this.id - other.id;  // id를 기준으로 오름차순 정렬
    }
}

// 사용
List<Student> students = Arrays.asList(
    new Student(3, "Alice"),
    new Student(1, "Bob"),
    new Student(2, "Charlie")
);

Collections.sort(students);  // id 기준으로 정렬
```

### 2. Comparator
- **외부에서 정렬 기준**을 정의하기 위해 사용합니다.
- 정렬 기준이 객체의 **클래스 외부에 별도로 작성**됩니다.
- `compare()` 메소드를 오버라이드하거나 람다식으로 정의합니다.

#### 예시: Comparator 사용
```java
class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// Comparator 구현
Comparator<Student> nameComparator = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);  // 이름 기준으로 정렬
    }
};

// 사용
List<Student> students = Arrays.asList(
    new Student(3, "Alice"),
    new Student(1, "Bob"),
    new Student(2, "Charlie")
);
Collections.sort(students, nameComparator);  // 이름 기준으로 정렬
```

---

## Comparator 메소드
### 1. `compare(T o1, T o2)`
- 두 객체를 비교하는 메소드입니다.
- 반환값:
    - **양수**: `o1`이 `o2`보다 크다.
    - **0**: `o1`과 `o2`가 같다.
    - **음수**: `o1`이 `o2`보다 작다.

#### 예시
```java
Comparator<Integer> comparator = new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return a - b;  // 오름차순 정렬
    }
};
```

---

## Comparator와 람다식
람다식을 사용하면 `Comparator`를 간결하게 표현할 수 있습니다.  
람다식은 익명 클래스를 대체하여 코드의 가독성을 높입니다.

### 예시: 람다식으로 Comparator 구현
```java
Comparator<Integer> comparator = (a, b) -> b - a;  // 내림차순 정렬
```

---

## Comparator 응용

### 1. 다중 조건 정렬
여러 조건을 기준으로 정렬하고 싶다면, 조건을 순서대로 정의합니다.

#### 예시: 다중 조건 정렬
1. 첫 번째 기준: 점수
2. 두 번째 기준: 이름
```java
class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

// Comparator 구현
Comparator<Student> multiComparator = (s1, s2) -> {
    if (s1.score == s2.score) {
        return s1.name.compareTo(s2.name);  // 점수가 같으면 이름으로 정렬
    }
    return s2.score - s1.score;  // 점수는 내림차순
};

// 사용
List<Student> students = Arrays.asList(
    new Student("Alice", 90),
    new Student("Bob", 85),
    new Student("Charlie", 90)
);
Collections.sort(students, multiComparator);
```

정렬 결과:
```
Charlie (90), Alice (90), Bob (85)
```

---

## Comparator의 장점과 한계

### 장점
1. **유연성**: 클래스 외부에서 정렬 기준을 정의할 수 있습니다.
2. **다중 정렬 기준**: 여러 조건으로 정렬이 가능합니다.
3. **람다식 지원**: 간결하고 직관적인 코드 작성이 가능합니다.

### 한계
1. **클래스 외부 작성 필요**: 매번 정렬 기준을 정의해야 하는 번거로움이 있습니다.
2. **가독성 저하 가능성**: 복잡한 조건을 정의하면 코드가 길어질 수 있습니다.

---

## 정리
`Comparator`는 객체 정렬의 기준을 유연하게 정의할 수 있는 도구입니다.  
`compare()` 메소드를 오버라이드하거나 람다식을 사용해 간단히 구현할 수 있으며, 다중 조건 정렬과 같은 복잡한 정렬 작업에서도 매우 유용합니다.
