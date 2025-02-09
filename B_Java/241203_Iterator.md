# Iterator 클래스와 for-each 문

https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

자바에서 **Iterator**와 **for-each 문**은 컬렉션(Collection)이나 배열의 요소를 순회(iteration)할 때 사용하는 주요 도구입니다.  
이 문서에서는 Iterator 클래스와 for-each 문에 대해 상세히 설명하고, 두 도구의 사용 사례와 차이점을 다룹니다.

---

## 1. Iterator 클래스

### 1.1 Iterator란?
Iterator는 **컬렉션의 요소를 순차적으로 접근**하면서 그 요소를 읽거나 삭제할 수 있도록 지원하는 인터페이스입니다.  
컬렉션 프레임워크의 표준 방식으로, 반복문과 달리 데이터 구조의 내부 구현에 관계없이 요소를 순회할 수 있습니다.

### 1.2 Iterator의 주요 메서드
```java
public interface Iterator<E> {
    boolean hasNext(); // 다음 요소가 있는지 확인
    E next(); // 다음 요소 반환
    void remove(); // 현재 요소 제거
}
```

#### 메서드 설명
1. **`hasNext()`**
    - 다음 요소가 존재하면 `true`를 반환합니다.
    - 다음 요소가 없으면 `false`를 반환합니다.
2. **`next()`**
    - 다음 요소를 반환하고, 커서를 한 칸 앞으로 이동합니다.
    - 순회 중 마지막 요소 이후에 호출하면 `NoSuchElementException`이 발생합니다.
3. **`remove()`**
    - `next()`로 반환된 **현재 요소를 컬렉션에서 제거**합니다.
    - 요소를 제거하지 않은 상태에서 `remove()`를 호출하면 `IllegalStateException`이 발생합니다.

---

### 1.3 Iterator 사용 예제
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Iterator 사용
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
            if (element.equals("Banana")) {
                iterator.remove(); // Banana 제거
            }
        }

        System.out.println("Updated list: " + list);
    }
}
```

#### 출력 결과
```
Apple
Banana
Cherry
Updated list: [Apple, Cherry]
```

---

### 1.4 Iterator의 장점과 단점

| **장점**                      | **단점**                             |
|-------------------------------|-------------------------------------|
| 컬렉션의 내부 구현과 무관하게 요소를 순회 가능 | 특정 위치의 요소에 직접 접근할 수 없음 |
| 안전한 요소 제거 가능 (동시 수정 방지) | 단방향 순회만 가능                   |
| 모든 컬렉션에서 일관된 사용 방식 | 동작이 제한적 (추가나 업데이트 불가) |

---

## 2. for-each 문

### 2.1 for-each 문이란?
for-each 문은 자바에서 배열이나 Iterable 객체(예: List, Set 등)를 **간단하게 순회**할 수 있도록 제공되는 반복문입니다.

#### 문법
```java
for (데이터_타입 변수 : 컬렉션_또는_배열) {
    // 반복할 코드
}
```

### 2.2 for-each 문 사용 예제

#### 배열 순회
```java
public class ForEachExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        for (int num : numbers) {
            System.out.println(num);
        }
    }
}
```

#### 컬렉션 순회
```java
import java.util.ArrayList;
import java.util.List;

public class ForEachCollectionExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
```

---

### 2.3 for-each 문과 Iterator의 차이점

| **특징**                    | **Iterator**                            | **for-each 문**                       |
|-----------------------------|----------------------------------------|--------------------------------------|
| **사용성**                   | 코드가 비교적 복잡 (명시적 선언 필요)         | 코드가 간결 (직관적인 순회 가능)           |
| **제어**                     | 요소 제거 가능 (`remove()` 사용)           | 요소 제거 불가                           |
| **범위**                     | 컬렉션에서만 사용 가능                       | 배열 및 컬렉션 모두에서 사용 가능            |
| **성능**                     | 성능은 동일하지만, 더 많은 메서드 호출 필요      | 반복문에 최적화되어 간단히 실행 가능          |

---

## 3. for-each 문 내부에서 요소 제거 문제

for-each 문은 **내부적으로 Iterator를 사용**해 요소를 순회하지만, 직접적으로 `remove()` 메서드를 호출할 수 없습니다.  
만약 요소를 제거하려고 하면 **`ConcurrentModificationException`**이 발생합니다.

### 문제 발생 예제
```java
import java.util.ArrayList;
import java.util.List;

public class ForEachRemoveExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        for (String fruit : fruits) {
            if (fruit.equals("Banana")) {
                fruits.remove(fruit); // 예외 발생!
            }
        }
    }
}
```

#### 해결 방법: Iterator로 대체
```java
Iterator<String> iterator = fruits.iterator();
while (iterator.hasNext()) {
    String fruit = iterator.next();
    if (fruit.equals("Banana")) {
        iterator.remove(); // 안전하게 요소 제거
    }
}
```

---

## 4. Iterable 인터페이스와 for-each 문

### 4.1 Iterable 인터페이스란?
컬렉션 클래스(List, Set 등)는 **`Iterable` 인터페이스를 구현**하므로 for-each 문에서 사용 가능합니다.

#### Iterable의 메서드
```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

#### 사용자 정의 클래스에 for-each 적용
사용자 정의 클래스에서 for-each 문을 사용하려면 **`Iterable` 인터페이스를 구현**해야 합니다.

```java
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCollection implements Iterable<String> {
    private String[] items = {"A", "B", "C"};

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < items.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items[index++];
            }
        };
    }

    public static void main(String[] args) {
        CustomCollection collection = new CustomCollection();
        for (String item : collection) {
            System.out.println(item);
        }
    }
}
```

---

## 5. 결론

- **Iterator:** 컬렉션의 요소를 순회하며 읽고 제거할 수 있는 유연한 방식.
- **for-each 문:** 간결하고 직관적인 방식으로 배열과 컬렉션의 요소를 순회 가능.

두 방식은 각자의 강점과 용도가 있으며, 컬렉션 요소 제거가 필요하면 Iterator를, 간단한 순회가 필요하면 for-each 문을 사용하는 것이 적절합니다.  