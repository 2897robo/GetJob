# **ArrayList - Java 컬렉션 프레임워크**

![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

---

## **1. ArrayList란?**
`ArrayList`는 Java 컬렉션 프레임워크에서 가장 많이 사용되는 **가변 크기 배열 기반 리스트**입니다.  
기존의 `Array`(배열)과 달리 **동적으로 크기를 조정할 수 있으며**, 요소를 추가하거나 삭제할 수 있습니다.

---

## **2. ArrayList의 특징**
1. **내부 구조: 동적 배열(Dynamic Array)**
    - 배열을 기반으로 요소를 저장하며, 크기가 가득 차면 자동으로 **배열 크기를 증가**시킴.
    - 초기 크기보다 더 많은 요소가 추가되면 **배열 크기를 1.5배로 확장**함.

2. **랜덤 액세스 가능 (`O(1)`)**
    - 배열처럼 인덱스를 기반으로 직접 요소에 접근할 수 있어 **조회 속도가 빠름**.

3. **요소 추가 (`O(1)`, 평균적으로)**
    - 새로운 요소를 리스트의 끝에 추가할 경우 평균적으로 `O(1)`의 시간 복잡도를 가짐.
    - 하지만 **배열 확장이 필요한 경우 O(n)**의 시간이 걸림.

4. **삽입/삭제 비용 (`O(n)`)**
    - 특정 인덱스에 요소를 삽입하거나 삭제하면, 뒤의 요소들을 **한 칸씩 이동**해야 함.
    - 따라서 삽입/삭제 연산은 **O(n)의 시간 복잡도를 가짐**.

---

## **3. ArrayList의 주요 메서드**
### **✅ 기본적인 사용법**
```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ArrayList 생성
        ArrayList<String> list = new ArrayList<>();

        // 요소 추가 (add)
        list.add("Java");
        list.add("Python");
        list.add("C++");

        // 특정 위치에 요소 추가 (insert)
        list.add(1, "JavaScript");

        // 요소 조회 (get)
        System.out.println("1번째 요소: " + list.get(1));  // JavaScript

        // 요소 수정 (set)
        list.set(2, "Go");

        // 요소 삭제 (remove)
        list.remove("C++");  // 값으로 삭제
        list.remove(1);  // 인덱스로 삭제

        // 리스트 크기 (size)
        System.out.println("리스트 크기: " + list.size());

        // 리스트 전체 출력
        System.out.println("현재 리스트: " + list);
    }
}
```

**📝 실행 결과**
```
1번째 요소: JavaScript
리스트 크기: 2
현재 리스트: [Java, Go]
```

---

## **4. ArrayList의 성능 분석**
| 연산 | 시간 복잡도 | 설명 |
|------|-----------|-----------------------------|
| `get(index)` | **O(1)** | 인덱스를 이용한 랜덤 접근 |
| `add(element)` | **O(1)** *(평균)* | 리스트의 끝에 추가 |
| `add(index, element)` | **O(n)** | 중간에 삽입할 경우 요소 이동 필요 |
| `remove(index)` | **O(n)** | 요소 삭제 후 나머지 요소 이동 필요 |
| `contains(element)` | **O(n)** | 특정 요소 검색 시 전체 탐색 |

---

## **5. ArrayList vs LinkedList**
| 비교 항목 | `ArrayList` | `LinkedList` |
|----------|------------|-------------|
| 내부 구조 | 동적 배열 | 이중 연결 리스트 |
| 인덱스 접근 | **빠름 (O(1))** | 느림 (O(n)) |
| 중간 삽입/삭제 | 느림 (O(n)) | **빠름 (O(1))** |
| 메모리 사용 | 배열 크기만큼 필요 | 포인터 추가로 더 많은 메모리 사용 |
| 검색 속도 | **빠름 (O(n))** | 느림 (O(n)) |

**👉 결론:**
- **랜덤 접근이 많고, 요소 추가/삭제가 적다면 `ArrayList`가 유리`**
- **삽입/삭제가 많다면 `LinkedList`가 유리`**

---

## **6. ArrayList를 사용할 때 주의할 점**
1. **크기 조정 비용**
    - 크기가 초과될 경우 새로운 배열을 할당하고 기존 요소를 복사하는 과정이 필요함.
    - 초기에 예상 크기를 지정하면 성능 최적화 가능 → `new ArrayList<>(100)`

2. **동기화(Synchronization)**
    - `ArrayList`는 **Thread-safe하지 않음**, 다중 스레드 환경에서는 `Collections.synchronizedList()` 또는 `CopyOnWriteArrayList`를 사용해야 함.
   ```java
   List<String> syncList = Collections.synchronizedList(new ArrayList<>());
   ```

---

## **7. ArrayList의 실제 활용 예제**
### **✅ 1️⃣ 리스트 정렬**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 3));

        // 오름차순 정렬
        Collections.sort(numbers);
        System.out.println("오름차순: " + numbers);

        // 내림차순 정렬
        numbers.sort(Collections.reverseOrder());
        System.out.println("내림차순: " + numbers);
    }
}
```
**📝 실행 결과**
```
오름차순: [1, 2, 3, 5, 8]
내림차순: [8, 5, 3, 2, 1]
```

두 코드 모두 `ArrayList`를 정렬하는 방식이지만, 내부적으로 동작하는 방식에 차이가 있어.

---

### **1️⃣ `Collections.sort(numbers);` (오름차순 정렬)**
```java
Collections.sort(numbers);
```
- **작동 방식**:
    - `Collections.sort(List<T> list)`는 **Timsort**(합병 정렬과 삽입 정렬을 결합한 정렬 알고리즘)를 사용하여 **리스트를 오름차순으로 정렬**한다.
    - 내부적으로 `list.sort(null)`을 호출하여 `Comparable` 인터페이스의 `compareTo()` 메서드를 사용해 정렬한다.

- **실행 과정**
    1. `numbers` 리스트가 `Comparable`을 구현하는 `Integer` 타입이므로 기본적으로 `compareTo()`를 사용해 **오름차순 정렬**을 수행.
    2. 내부적으로 `TimSort` 알고리즘이 사용되며, **최악의 경우 `O(n log n)`, 최선의 경우 `O(n)`**의 성능을 가짐.

- **예제**
```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 3));
Collections.sort(numbers);
System.out.println("오름차순: " + numbers);  // [1, 2, 3, 5, 8]
```

---

### **2️⃣ `numbers.sort(Collections.reverseOrder());` (내림차순 정렬)**
```java
numbers.sort(Collections.reverseOrder());
```
- **작동 방식**:
    - `numbers.sort(Comparator c)`를 호출하여, `Collections.reverseOrder()`가 반환하는 **내림차순 정렬용 `Comparator` 객체**를 사용함.
    - `Comparator.reverseOrder()`는 **기본 정렬 순서를 반대로 뒤집는 Comparator**를 제공함.

- **실행 과정**
    1. `numbers.sort(Collections.reverseOrder())` 호출.
    2. `reverseOrder()`로 인해 `compare(a, b)`가 기존 `compareTo()`와 반대로 동작하도록 설정됨.
    3. `TimSort` 알고리즘을 사용하여 **내림차순 정렬**을 수행.

- **예제**
```java
numbers.sort(Collections.reverseOrder());
System.out.println("내림차순: " + numbers);  // [8, 5, 3, 2, 1]
```

---

### **📌 두 정렬 방식의 차이점**
| 비교 항목 | `Collections.sort(numbers);` | `numbers.sort(Collections.reverseOrder());` |
|-----------|-----------------------------|---------------------------------|
| **정렬 기준** | 기본 정렬(오름차순) | 내림차순 |
| **사용된 알고리즘** | TimSort (`O(n log n)`) | TimSort (`O(n log n)`) |
| **Comparator 사용 여부** | `Comparable`의 `compareTo()`를 사용 | `Collections.reverseOrder()`를 사용 |
| **메서드 호출 방식** | `Collections.sort(List<T> list)` | `List.sort(Comparator<? super T> c)` |

---

### **📢 결론**
1. **`Collections.sort(list)`**
    - 리스트의 기본 정렬(오름차순)을 수행.
    - 내부적으로 `list.sort(null)`을 호출하여 `Comparable` 인터페이스를 활용.

2. **`list.sort(Collections.reverseOrder())`**
    - `Comparator.reverseOrder()`를 사용해 기본 정렬 순서를 반대로 변경하여 내림차순 정렬.
    - 성능 차이는 없지만, **Comparator를 활용하는 방식이라 유연성이 더 좋음**.

---
### **✅ 추가적인 차이점**
- `Collections.sort(list)`는 **Java 7 이전에도 사용 가능**하지만,  
  `list.sort(Comparator c)`는 **Java 8부터 가능**한 방식이야.
- `Comparator`를 직접 정의하면 **사용자 정의 정렬 기준**을 적용할 수도 있어.

---
### **💡 커스텀 Comparator 예제**
```java
List<String> names = new ArrayList<>(Arrays.asList("Kim", "Lee", "Park", "Choi"));

// 문자열 길이에 따라 정렬 (짧은 순서대로)
names.sort(Comparator.comparingInt(String::length));
System.out.println("길이순 정렬: " + names);  // [Kim, Lee, Park, Choi]

// 길이순 내림차순 정렬
names.sort(Comparator.comparingInt(String::length).reversed());
System.out.println("길이순 내림차순: " + names);  // [Park, Choi, Kim, Lee]
```
이렇게 하면 **길이 순으로 정렬**도 가능해.

---
### **📌 정리**
- `Collections.sort(list)` → 오름차순 정렬 (`Comparable` 사용)
- `list.sort(Comparator c)` → 유연한 정렬 가능 (`Comparator` 사용)
- `list.sort(Collections.reverseOrder())` → 내림차순 정렬
- `Comparator.comparing()`을 활용하면 **커스텀 정렬 가능**

---

### **✅ 2️⃣ 중복 제거**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Java", "Python", "Java", "C++", "Python"));

        HashSet<String> uniqueSet = new HashSet<>(list);
        ArrayList<String> uniqueList = new ArrayList<>(uniqueSet);

        System.out.println("중복 제거 후: " + uniqueList);
    }
}
```
**📝 실행 결과**
```
중복 제거 후: [Java, Python, C++]
```

---

## **8. 결론**
- **ArrayList**는 **배열 기반**으로 빠른 조회(`O(1)`)와 동적 크기 조절이 가능한 리스트 구현체.
- **요소 추가/삭제가 많으면 성능이 떨어질 수 있음** → `LinkedList` 고려.
- **Thread-safe 하지 않음** → 동기화가 필요하면 `Collections.synchronizedList()` 사용.
- **정렬, 검색, 중복 제거** 등 다양한 활용 가능.

---
