# LinkedHashMap<K, V> 개요

![Java Collections](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

`LinkedHashMap<K, V>`는 `HashMap<K, V>`를 확장한 클래스이며, **입력 순서를 유지하는 특징이 있는 Map 구현체**입니다.  
기본적으로 `HashMap<K, V>`와 같은 `키-값` 저장 방식을 가지지만, **데이터가 추가된 순서를 유지**하거나 **최근 사용 순서(접근 순서)**를 유지하는 기능이 있습니다.

---

### 🛠 **LinkedHashMap의 주요 특징**
1. **입력 순서 유지 (Insertion Order)**
    - `HashMap<K, V>`는 내부적으로 **해시 테이블(Hash Table)**을 사용하기 때문에 **데이터 저장 순서가 보장되지 않음**.
    - 하지만 `LinkedHashMap<K, V>`는 **이중 연결 리스트(Doubly Linked List)**를 이용하여 **입력된 순서를 유지**.

2. **LRU (Least Recently Used) 캐시 기능 제공**
    - `LinkedHashMap<K, V>`는 생성자에서 `accessOrder = true`로 설정하면 **가장 최근에 접근한 순서**대로 요소를 정렬하는 **LRU 캐시 기능**을 사용할 수 있음.

3. **성능**
    - `HashMap<K, V>`와 동일한 **O(1)** 시간 복잡도로 **빠른 데이터 접근 속도**를 제공.
    - 하지만 추가적인 **연결 리스트 구조**를 유지해야 하므로 `HashMap<K, V>`보다 **메모리 사용량이 많음**.

---

### 📌 **LinkedHashMap<K, V> 선언 및 기본 사용법**
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // LinkedHashMap 선언 (기본적으로 입력 순서 유지)
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // 데이터 추가
        linkedHashMap.put("Apple", 1000);
        linkedHashMap.put("Banana", 500);
        linkedHashMap.put("Cherry", 1200);
        linkedHashMap.put("Date", 800);

        // 데이터 출력 (입력한 순서 유지됨)
        System.out.println(linkedHashMap);
        // 결과: {Apple=1000, Banana=500, Cherry=1200, Date=800}
    }
}
```

---

### 📌 **LinkedHashMap의 정렬 방식 (입력 순서 vs. 접근 순서)**
#### 1️⃣ **입력 순서 유지**
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapInsertionOrder {
    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(3, "C");
        map.put(1, "A");
        map.put(2, "B");

        System.out.println(map);
        // 결과: {3=C, 1=A, 2=B}  (입력한 순서 유지)
    }
}
```

#### 2️⃣ **LRU (Least Recently Used) 순서 유지**
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapAccessOrder {
    public static void main(String[] args) {
        // LRU 순서 유지 (accessOrder = true)
        Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put(3, "C");
        map.put(1, "A");
        map.put(2, "B");

        // 키 1과 3을 조회
        map.get(1);
        map.get(3);

        System.out.println(map);
        // 결과: {2=B, 1=A, 3=C}  (가장 최근에 접근한 키가 뒤로 이동)
    }
}
```
👉 `accessOrder = true`를 사용하면 **가장 최근에 접근한 순서대로 데이터가 정렬됨**.  
이 기능을 활용하면 **LRU 캐시 구현 가능**.

---

### 📌 **LinkedHashMap 주요 메서드**
| 메서드 | 설명 |
|--------|------|
| `put(K key, V value)` | 키-값 추가 |
| `get(K key)` | 특정 키의 값 조회 (접근 순서 모드에서는 순서가 변경됨) |
| `remove(K key)` | 특정 키의 값 삭제 |
| `size()` | 저장된 요소 개수 반환 |
| `containsKey(K key)` | 특정 키가 존재하는지 확인 |
| `containsValue(V value)` | 특정 값이 존재하는지 확인 |
| `clear()` | 모든 요소 삭제 |

---

### 📌 **LinkedHashMap vs. HashMap vs. TreeMap**
| 특징 | **LinkedHashMap<K, V>** | **HashMap<K, V>** | **TreeMap<K, V>** |
|------|------------------|-----------------|-----------------|
| **정렬 방식** | **입력 순서 유지** | 정렬 없음 (무작위 순서) | **키 기준 정렬** (오름차순) |
| **검색 속도** | O(1) | O(1) | O(log N) |
| **삽입 속도** | O(1) | O(1) | O(log N) |
| **메모리 사용량** | `HashMap<K, V>`보다 큼 | 적음 | 큼 |
| **LRU 캐시 기능** | 지원 | 지원 안 함 | 지원 안 함 |

---

### 📌 **LinkedHashMap 활용 예시**
#### 1️⃣ **캐시 (LRU 알고리즘 활용)**
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // LRU 모드 활성화
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity; // 용량 초과 시 가장 오래된 데이터 삭제
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache); // {1=A, 2=B, 3=C}

        cache.get(1); // 1을 조회하여 가장 최근 사용으로 이동
        cache.put(4, "D"); // 용량 초과로 가장 오래된 2가 삭제됨

        System.out.println(cache); // {3=C, 1=A, 4=D}
    }
}
```
👉 **LRU 캐시 구현 가능!**  
👉 가장 오래된 데이터가 자동으로 삭제됨.

---

### 📌 **결론**
- `LinkedHashMap<K, V>`는 `HashMap<K, V>`와 유사하지만, **입력 순서 또는 접근 순서를 유지**한다.
- 기본적으로 **입력 순서를 유지하며**, LRU 캐시 기능을 활성화할 수 있다.
- 빠른 데이터 접근이 가능하지만, **추가적인 연결 리스트 유지 비용**이 발생하여 `HashMap<K, V>`보다 약간의 메모리 오버헤드가 있다.
- **LRU 캐시, 순서가 중요한 데이터 저장소**에서 유용하게 사용된다.

---
