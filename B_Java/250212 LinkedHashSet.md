# LinkedHashSet<E>에 대한 설명

![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

## 1. LinkedHashSet<E>란?
`LinkedHashSet<E>`은 `HashSet<E>`의 확장된 버전으로, **중복을 허용하지 않으며 요소의 저장 순서를 유지하는** `Set` 컬렉션입니다. 이는 `HashSet<E>`이 요소를 저장할 때 **순서를 유지하지 않는** 것과 차별화되는 중요한 특징입니다.

## 2. LinkedHashSet의 주요 특징
- **중복을 허용하지 않음**: `HashSet<E>`과 동일하게 같은 값을 중복 저장할 수 없음.
- **저장 순서 유지**: 입력된 요소들의 순서를 유지하여 출력함.
- **해시 테이블과 이중 연결 리스트 사용**: `HashSet<E>`과 달리, 내부적으로 **해시 테이블과 이중 연결 리스트 (Doubly-Linked List)**를 사용하여 요소를 저장.
- **`null` 값 허용**: 단, `null` 값은 한 개만 저장할 수 있음.
- **빠른 조회 성능**: 일반적으로 O(1)의 평균 시간 복잡도로 요소를 검색할 수 있음.
- **느린 삽입/삭제 성능**: 저장 순서를 유지해야 하므로, `HashSet<E>`보다 삽입 및 삭제 연산이 약간 느릴 수 있음.

## 3. LinkedHashSet<E>의 내부 동작 원리
- `HashSet<E>`과 동일하게 **해시 테이블(Hash Table)**을 사용하여 요소를 저장하고 검색 속도를 최적화함.
- 추가적으로 **이중 연결 리스트(Doubly-Linked List)**를 활용하여 요소의 **삽입 순서**를 유지함.
- 따라서 `HashSet<E>`보다 메모리 사용량이 조금 더 많지만, 순서가 중요한 경우에 유리함.

## 4. LinkedHashSet<E> 선언 및 기본 사용법
```java
import java.util.LinkedHashSet;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>();

        // 값 추가
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // 중복된 값 추가 시 무시됨

        // 출력 (입력된 순서 유지)
        System.out.println(set); // [Apple, Banana, Cherry]
    }
}
```

### 💡 HashSet과의 차이점
```java
import java.util.HashSet;
import java.util.LinkedHashSet;

public class SetComparison {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        // 동일한 요소 추가
        hashSet.add("Banana");
        hashSet.add("Apple");
        hashSet.add("Cherry");
        
        linkedHashSet.add("Banana");
        linkedHashSet.add("Apple");
        linkedHashSet.add("Cherry");

        // 출력 결과 비교
        System.out.println("HashSet: " + hashSet);         // 순서가 일정하지 않음
        System.out.println("LinkedHashSet: " + linkedHashSet); // 삽입된 순서 유지
    }
}
```
#### 실행 결과 예시
```
HashSet: [Apple, Cherry, Banana]  // 순서가 랜덤
LinkedHashSet: [Banana, Apple, Cherry] // 삽입 순서 유지
```

## 5. 주요 메서드
| 메서드 | 설명 |
|--------|------|
| `add(E e)` | 요소 추가 (중복이면 무시됨) |
| `remove(Object o)` | 특정 요소 제거 |
| `clear()` | 모든 요소 제거 |
| `contains(Object o)` | 해당 요소가 존재하는지 여부 확인 |
| `size()` | 요소의 개수 반환 |
| `isEmpty()` | 컬렉션이 비어 있는지 확인 |
| `iterator()` | 반복자(Iterator)를 반환하여 요소를 순회 |

## 6. LinkedHashSet의 장단점
### ✅ 장점
- `HashSet`과 동일하게 중복을 허용하지 않음.
- 요소가 삽입된 **순서를 유지**하여 저장됨.
- 빠른 검색 성능(O(1)) 제공.

### ❌ 단점
- `HashSet`보다 **메모리를 더 많이 사용**(이중 연결 리스트를 유지하기 때문).
- `HashSet`보다 **삽입/삭제 속도가 느림**(연결 리스트 조작 필요).

## 7. LinkedHashSet 사용 예제 (실용 사례)
### 1) 중복 제거 후 순서 유지하기
```java
import java.util.LinkedHashSet;

public class RemoveDuplicates {
    public static void main(String[] args) {
        String[] words = {"banana", "apple", "cherry", "banana", "apple", "date"};
        LinkedHashSet<String> uniqueWords = new LinkedHashSet<>();
        
        for (String word : words) {
            uniqueWords.add(word);
        }

        System.out.println(uniqueWords); // [banana, apple, cherry, date]
    }
}
```

### 2) 최근 방문한 페이지 저장 (LRU 캐시와 비슷한 동작)
```java
import java.util.LinkedHashSet;

public class RecentPages {
    public static void main(String[] args) {
        LinkedHashSet<String> recentPages = new LinkedHashSet<>();
        
        recentPages.add("HomePage");
        recentPages.add("LoginPage");
        recentPages.add("Dashboard");
        recentPages.add("ProfilePage");
        
        System.out.println("최근 방문한 페이지: " + recentPages);
    }
}
```

## 8. 결론
- `LinkedHashSet<E>`은 `HashSet<E>`과 다르게 **요소의 삽입 순서를 유지**하면서도 `Set`의 특징을 유지하는 컬렉션이다.
- 중복 제거가 필요하지만 **입력 순서를 보장**해야 할 때 적절한 선택이다.
- 다만, 추가적인 메모리 오버헤드와 약간의 성능 저하가 발생할 수 있으므로, 순서 유지가 불필요하다면 `HashSet<E>`을 사용하는 것이 더 적절할 수 있다.

---
