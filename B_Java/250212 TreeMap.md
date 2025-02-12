# TreeMap<K, V> 개요

![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

`TreeMap<K, V>`은 **Java의 `NavigableMap` 인터페이스를 구현**한 클래스이며, 내부적으로 **Red-Black Tree(레드-블랙 트리)** 를 사용하여 **Key를 자동으로 정렬**하는 특징이 있습니다.

---
## 📌 **TreeMap<K, V> 특징**
1. **정렬된 Key 저장**
    - `TreeMap`은 **Key를 오름차순(기본)**으로 정렬하여 저장합니다.
    - `Comparator`를 직접 지정하면 **사용자 정의 정렬 기준**도 가능.

2. **빠른 탐색과 검색 (O(log N))**
    - **이진 탐색 트리(Red-Black Tree) 기반**이므로, 탐색/삭제/삽입 성능이 **O(log N)**.

3. **Key를 기준으로 정렬된 순서 보장**
    - `HashMap`과 달리, **저장된 순서가 아니라 Key 순서대로 출력됨**.

4. **중복 Key 저장 불가**
    - Key는 **유일**해야 하며, 중복된 Key가 삽입되면 기존 값을 덮어씁니다.

---
## 📌 **TreeMap<K, V> 선언 및 사용법**
```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        // TreeMap 선언 (기본적으로 Key는 오름차순 정렬됨)
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // 요소 추가
        treeMap.put(3, "Apple");
        treeMap.put(1, "Banana");
        treeMap.put(2, "Cherry");
        treeMap.put(5, "Grape");
        treeMap.put(4, "Orange");

        // 출력 (Key 기준 정렬됨)
        System.out.println(treeMap);  
        // {1=Banana, 2=Cherry, 3=Apple, 4=Orange, 5=Grape}
    }
}
```
- **Key가 정렬된 상태로 유지됨** (`{1, 2, 3, 4, 5}`)
- **HashMap과 달리 입력 순서대로 저장되지 않음**

---
## 📌 **TreeMap 주요 메서드**
| 메서드 | 설명 |
|---------|--------------------------------------|
| `put(K key, V value)` | Key-Value 추가 |
| `get(K key)` | Key에 해당하는 Value 가져오기 |
| `remove(K key)` | 특정 Key 제거 |
| `firstKey()` | 가장 작은 Key 반환 |
| `lastKey()` | 가장 큰 Key 반환 |
| `higherKey(K key)` | Key보다 큰 값 중 최소 Key 반환 |
| `lowerKey(K key)` | Key보다 작은 값 중 최대 Key 반환 |
| `pollFirstEntry()` | 첫 번째 엔트리 반환 후 제거 |
| `pollLastEntry()` | 마지막 엔트리 반환 후 제거 |

---
## 📌 **TreeMap 정렬 커스터마이징**
### 1️⃣ **내림차순 정렬**
```java
import java.util.*;

public class DescendingTreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());

        treeMap.put(3, "Apple");
        treeMap.put(1, "Banana");
        treeMap.put(2, "Cherry");

        System.out.println(treeMap);  
        // {3=Apple, 2=Cherry, 1=Banana} (내림차순 정렬)
    }
}
```

### 2️⃣ **Comparator로 사용자 정의 정렬**
```java
TreeMap<String, Integer> treeMap = new TreeMap<>((s1, s2) -> s2.compareTo(s1));
```
- 위 코드에서는 Key가 **문자열 역순**(`Z-A`)으로 정렬됨.

---
## 📌 **TreeMap vs HashMap vs LinkedHashMap 비교**
|  | **TreeMap** | **HashMap** | **LinkedHashMap** |
|--------|----------------|---------------|------------------|
| 내부 구조 | **Red-Black Tree** | **Hash Table** | **Hash Table + Linked List** |
| Key 정렬 | ✅ **Key 자동 정렬** | ❌ 순서 없음 | ✅ **입력 순서 유지** |
| 성능 (탐색) | **O(log N)** | **O(1)** | **O(1)** |
| 성능 (삽입/삭제) | **O(log N)** | **O(1)** | **O(1)** |
| Null 허용 여부 | ❌ **Key는 Null 불가** | ✅ **Key, Value 모두 가능** | ✅ **Key, Value 모두 가능** |

---
## 📌 **TreeMap 사용 예제 (검색 기능 활용)**
```java
import java.util.TreeMap;

public class TreeMapSearchExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(10, "Ten");
        treeMap.put(20, "Twenty");
        treeMap.put(30, "Thirty");

        // 특정 Key보다 크거나 같은 Entry 찾기
        System.out.println(treeMap.ceilingEntry(15));  // 20=Twenty

        // 특정 Key보다 작은 Entry 찾기
        System.out.println(treeMap.lowerEntry(25));  // 20=Twenty
    }
}
```
---
## 📌 **TreeMap을 언제 사용할까?**
✅ **TreeMap이 적절한 경우**
- Key 값이 **정렬된 상태**로 유지되어야 할 때.
- 빠른 범위 검색 (`subMap()`, `headMap()`, `tailMap()`)이 필요할 때.
- Key를 기준으로 **최소값 / 최대값을 자주 찾을 때**.

❌ **TreeMap이 적절하지 않은 경우**
- Key의 정렬이 필요 없는 경우 → `HashMap` 사용 (`O(1)` 성능)
- 입력 순서를 유지해야 하는 경우 → `LinkedHashMap` 사용
- 멀티스레드 환경에서 동기화가 필요한 경우 → `ConcurrentSkipListMap` 사용

---
## 📌 **정리**
- `TreeMap<K, V>`는 **Key가 자동 정렬되는 Map**이며, 내부적으로 **Red-Black Tree** 사용.
- 탐색/삽입/삭제 속도는 **O(log N)** (HashMap보다 느리지만 정렬 기능 제공).
- `Comparator`를 지정하여 **사용자 정의 정렬** 가능.
- Key를 **범위 검색**할 수 있는 유용한 메서드 제공 (`higherKey()`, `lowerKey()`, `subMap()` 등).

---
