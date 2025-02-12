# TreeSet<E>

![Java Collections Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

## 개요
`TreeSet<E>`은 `NavigableSet` 인터페이스를 구현한 클래스이며, 내부적으로 **Red-Black Tree(레드-블랙 트리)** 자료구조를 기반으로 동작한다. **자동 정렬** 기능을 제공하며, 요소를 추가할 때마다 자동으로 **오름차순 정렬**된다.

## TreeSet<E>의 특징
1. **중복 허용 X**: `Set` 인터페이스를 구현하므로 중복된 요소를 저장할 수 없다.
2. **정렬된 상태 유지**: 추가된 데이터는 항상 오름차순 정렬된다.
3. **빠른 검색 성능**: 내부적으로 **이진 탐색 트리(Binary Search Tree, BST)**를 기반으로 하기 때문에, 대부분의 연산이 `O(log N)`의 시간 복잡도를 가진다.
4. **Comparator 지원**: 기본적으로 **자연 정렬(Natural Ordering)**을 따르지만, `Comparator`를 활용해 사용자 정의 정렬도 가능하다.
5. **Null 허용 여부**: `null` 값 저장 가능 여부는 `TreeSet`의 요소 타입과 비교 기준에 따라 다르다. 기본적으로 **`null`을 허용하지 않는다**.

---

## TreeSet<E> 선언 방법

```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        // 기본 생성자: 자연 정렬 사용 (오름차순 정렬)
        TreeSet<Integer> numbers = new TreeSet<>();
        
        // 초기값 추가
        numbers.add(5);
        numbers.add(3);
        numbers.add(7);
        numbers.add(1);
        numbers.add(9);
        
        System.out.println("TreeSet 요소: " + numbers); // [1, 3, 5, 7, 9]
    }
}
```

위 코드에서 `TreeSet`은 자동으로 **오름차순 정렬**된 상태를 유지한다.

---

## 주요 메서드

| 메서드 | 설명 |
|--------|-------------|
| `add(E e)` | 요소 추가 (중복된 요소는 추가되지 않음) |
| `remove(Object o)` | 특정 요소 삭제 |
| `contains(Object o)` | 해당 요소가 존재하는지 확인 |
| `first()` | 가장 작은 요소 반환 |
| `last()` | 가장 큰 요소 반환 |
| `higher(E e)` | 주어진 요소보다 큰 값 중 가장 작은 값 반환 |
| `lower(E e)` | 주어진 요소보다 작은 값 중 가장 큰 값 반환 |
| `ceiling(E e)` | 주어진 요소 이상인 값 중 가장 작은 값 반환 |
| `floor(E e)` | 주어진 요소 이하인 값 중 가장 큰 값 반환 |
| `pollFirst()` | 첫 번째 요소 반환 후 삭제 |
| `pollLast()` | 마지막 요소 반환 후 삭제 |

예제:
```java
import java.util.TreeSet;

public class TreeSetMethodsExample {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("가장 작은 값: " + numbers.first()); // 10
        System.out.println("가장 큰 값: " + numbers.last()); // 50
        System.out.println("20보다 큰 값 중 가장 작은 값: " + numbers.higher(20)); // 30
        System.out.println("25보다 작은 값 중 가장 큰 값: " + numbers.lower(25)); // 20
    }
}
```

---

## 사용자 정의 정렬 (Comparator 사용)
기본적으로 `TreeSet`은 오름차순 정렬을 따르지만, `Comparator`를 사용하여 정렬 방식을 변경할 수 있다.

```java
import java.util.*;

public class TreeSetCustomSorting {
    public static void main(String[] args) {
        // 내림차순 정렬을 적용한 TreeSet
        TreeSet<Integer> descendingSet = new TreeSet<>(Comparator.reverseOrder());
        descendingSet.add(10);
        descendingSet.add(30);
        descendingSet.add(20);
        descendingSet.add(50);
        descendingSet.add(40);
        
        System.out.println("내림차순 정렬된 TreeSet: " + descendingSet);
    }
}
```

출력 결과:
```
내림차순 정렬된 TreeSet: [50, 40, 30, 20, 10]
```

---

## TreeSet vs HashSet vs LinkedHashSet

| 특징 | TreeSet | HashSet | LinkedHashSet |
|--------|---------|---------|---------------|
| **저장 순서 유지** | X (자동 정렬됨) | X | O |
| **중복 허용 여부** | X | X | X |
| **검색 성능** | `O(log N)` (BST 기반) | `O(1)` (Hash 기반) | `O(1)` |
| **정렬 여부** | O (기본 오름차순 정렬) | X | X |
| **순차 접근 속도** | 느림 | 빠름 | 보통 |

---

## 사용 사례
- **범위 검색 (Range Query)**: 예를 들어, 특정 범위 내의 값을 빠르게 검색해야 할 때 `TreeSet`이 유용하다.
- **자동 정렬이 필요한 경우**: 데이터가 입력될 때마다 정렬이 유지되어야 하는 경우.
- **이진 탐색을 활용한 검색**: 특정 값보다 큰 값이나 작은 값을 찾는 작업이 필요할 때 (`higher()`, `lower()` 등 활용).

---

## 결론
`TreeSet<E>`은 내부적으로 **Red-Black Tree**를 사용하여 요소를 자동 정렬하며, 추가/삭제/검색 연산이 `O(log N)`으로 동작하는 효율적인 자료구조다. 범위 검색, 자동 정렬이 필요한 경우 적합하지만, 성능이 중요한 경우 `HashSet`이나 `LinkedHashSet`을 고려하는 것이 좋다.

---
