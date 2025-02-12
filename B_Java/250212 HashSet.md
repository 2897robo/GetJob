# HashSet 개요

![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

## 1. HashSet이란?
`HashSet`은 Java의 `Set` 인터페이스를 구현한 클래스입니다. `Set`의 주요 특징인 **중복을 허용하지 않으며, 저장 순서를 유지하지 않는 자료구조**를 그대로 상속받습니다.

### 주요 특징
- **중복 불허**: 동일한 요소를 중복 저장하지 않습니다.
- **저장 순서 유지 X**: 입력한 순서대로 데이터가 정렬되지 않습니다.
- **검색 성능 우수**: 내부적으로 `HashMap`을 사용하여 빠른 검색이 가능합니다.
- **한 개의 null 값만 허용**: `null` 값은 한 번만 저장할 수 있습니다.

만약 저장된 순서를 유지해야 한다면 `LinkedHashSet`을, 자동 정렬이 필요하다면 `TreeSet`을 사용해야 합니다.

## 2. HashSet 내부 동작 방식

`HashSet`은 객체를 저장할 때 `hashCode()` 메서드를 호출하여 **해시코드를 기반으로 저장 위치를 결정**합니다. 동일한 해시코드가 존재하면 `equals()` 메서드로 값을 비교하여 중복을 검사합니다.

### 중복 검사 과정
1. `hashCode()` 값을 확인하여 기존에 저장된 객체가 있는지 체크.
2. 동일한 `hashCode()`를 가진 객체가 있다면 `equals()`를 호출하여 비교.
3. `equals()` 결과가 `true`이면 중복으로 판단하여 저장하지 않음.
4. `false`이면 새로운 객체로 간주하여 저장.

## 3. HashSet 선언 및 생성
```java
import java.util.HashSet;

HashSet<Integer> set1 = new HashSet<>(); // 기본 생성자 사용
HashSet<Integer> set2 = new HashSet<>(10); // 초기 용량(capacity) 설정
HashSet<Integer> set3 = new HashSet<>(10, 0.75f); // 초기 용량과 load factor 설정
HashSet<Integer> set4 = new HashSet<>(set1); // 다른 Set의 모든 요소 복사
HashSet<Integer> set5 = new HashSet<>(Arrays.asList(1,2,3)); // 초기값 지정
```

## 4. 주요 메서드 및 사용법
### 4.1 요소 추가 (`add()`)
```java
HashSet<Integer> set = new HashSet<>();
set.add(1);
set.add(2);
set.add(3);
```
- 중복된 요소를 추가하려 하면 `false`를 반환합니다.

### 4.2 요소 삭제 (`remove()`, `clear()`)
```java
set.remove(1); // 특정 값 제거
set.clear(); // 모든 요소 제거
```

### 4.3 크기 확인 (`size()`)
```java
System.out.println(set.size());
```

### 4.4 특정 값 존재 여부 확인 (`contains()`)
```java
System.out.println(set.contains(1)); // 값 1이 존재하면 true 반환
```

### 4.5 전체 요소 출력
```java
System.out.println(set); // [1, 2, 3] 형태로 출력
```

### 4.6 Iterator를 이용한 순회
```java
Iterator<Integer> iter = set.iterator();
while(iter.hasNext()) {
    System.out.println(iter.next());
}
```

## 5. HashSet과 다른 Set의 차이점
| 컬렉션 | 중복 허용 | 정렬 지원 | 저장 순서 유지 |
|--------|----------|----------|--------------|
| `HashSet` | X | X | X |
| `LinkedHashSet` | X | X | O |
| `TreeSet` | X | O (오름차순) | O |

## 6. HashSet의 성능 및 사용 시 주의점
### **시간 복잡도**
| 연산 | 평균 시간 복잡도 |
|------|----------------|
| 추가 (`add()`) | O(1) |
| 삭제 (`remove()`) | O(1) |
| 검색 (`contains()`) | O(1) |

### **주의점**
- `hashCode()`와 `equals()` 메서드를 올바르게 오버라이딩해야 합니다.
- 순서가 중요한 경우 `LinkedHashSet`을 고려해야 합니다.

## 7. HashSet 사용 예제
```java
import java.util.HashSet;
import java.util.Iterator;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // 중복 추가, 무시됨

        System.out.println("HashSet 요소: " + set);

        // Iterator를 이용한 순회
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

## 8. 결론
`HashSet`은 중복을 허용하지 않는 컬렉션으로 빠른 검색이 가능하지만, 순서를 유지하지 않는다는 단점이 있습니다. 만약 순서를 유지하면서 중복을 제거하고 싶다면 `LinkedHashSet`, 자동 정렬이 필요하다면 `TreeSet`을 사용하는 것이 좋습니다.

---
