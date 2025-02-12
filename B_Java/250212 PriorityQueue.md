# PriorityQueue<E>

![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

## 개요
`PriorityQueue<E>`는 Java의 `Queue` 인터페이스를 구현한 클래스 중 하나로, 요소들이 특정한 우선순위에 따라 정렬되는 특성을 가지고 있습니다. 일반적인 `Queue`와 달리 FIFO(First-In-First-Out) 방식이 아니라, **우선순위가 높은 요소가 먼저 제거(Poll)되는 방식**을 따릅니다.


## 주요 특징
- **기본적으로 최소 힙(Min-Heap) 구조를 사용하여 오름차순 정렬**
- **우선순위를 사용자 지정 가능 (Comparator 지원)**
- **`null` 요소 허용 안 함**
- **동기화되지 않음 (멀티스레드 환경에서는 `PriorityBlockingQueue` 사용 권장)**

## 선언 방법
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // 기본 오름차순 정렬
PriorityQueue<Integer> pqDesc = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬
PriorityQueue<String> pqCustom = new PriorityQueue<>(Comparator.comparing(String::length)); // 문자열 길이 기준 정렬
```

## 내부 동작 방식
- `PriorityQueue`는 **힙(Heap) 자료구조**를 기반으로 하며, **이진 최소 힙(Min-Heap)**이 기본 구현입니다.
- 요소를 삽입하면 힙 정렬(Heapify)을 수행하여 자동으로 정렬됩니다.
- 가장 높은 우선순위를 가지는 요소는 `peek()`을 통해 확인할 수 있습니다.
- `poll()`을 호출하면 우선순위가 가장 높은 요소가 제거되며, 나머지 요소가 다시 정렬됩니다.

## 주요 메서드
| 메서드 | 설명 |
|--------|------|
| `boolean add(E e)` | 요소 추가 (힙 재정렬) |
| `boolean offer(E e)` | 요소 추가 (예외 없이 실패 시 `false` 반환) |
| `E peek()` | 우선순위가 가장 높은 요소 확인 (삭제하지 않음) |
| `E poll()` | 우선순위가 가장 높은 요소 제거 및 반환 |
| `boolean remove(Object o)` | 특정 요소 제거 |
| `int size()` | 요소 개수 반환 |
| `boolean isEmpty()` | 큐가 비어있는지 확인 |
| `void clear()` | 모든 요소 제거 |

## 예제 코드
### 기본 오름차순 정렬 (Min-Heap)
```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 기본 오름차순
        
        pq.add(5);
        pq.add(1);
        pq.add(3);
        pq.add(10);
        pq.add(2);
        
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // 1, 2, 3, 5, 10 순으로 출력
        }
    }
}
```

### 내림차순 정렬 (Max-Heap)
```java
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        pq.add(5);
        pq.add(1);
        pq.add(3);
        pq.add(10);
        pq.add(2);
        
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // 10, 5, 3, 2, 1 순으로 출력
        }
    }
}
```

### 사용자 지정 우선순위 (Comparator 활용)
```java
import java.util.PriorityQueue;
import java.util.Comparator;

public class CustomPriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
        
        pq.add("apple");
        pq.add("banana");
        pq.add("kiwi");
        pq.add("grape");
        pq.add("watermelon");
        
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // kiwi, grape, apple, banana, watermelon 순으로 출력
        }
    }
}
```

## `PriorityQueue` vs `Queue` vs `Deque`
| 기능 | PriorityQueue | Queue (LinkedList) | Deque (ArrayDeque) |
|------|--------------|------------------|------------------|
| 기본 동작 | 우선순위에 따라 정렬 | FIFO | 양방향 삽입/삭제 가능 |
| 정렬 방식 | 힙 기반 정렬 | 정렬 없음 | 정렬 없음 |
| 삽입 시간 | O(log N) | O(1) | O(1) |
| 삭제 시간 | O(log N) | O(1) | O(1) |
| 랜덤 접근 | 불가능 | 불가능 | 불가능 |

## 활용 사례
- **작업 스케줄링 (Job Scheduling)**: 우선순위가 높은 작업을 먼저 처리하는 경우
- **다익스트라(Dijkstra) 알고리즘**: 최단 경로 탐색에 사용
- **이벤트 기반 시스템**: 이벤트의 중요도에 따라 우선순위 큐 활용
- **멀티스레딩 환경에서 작업 우선순위 관리**

## 주의할 점
- **정렬된 상태로 반복(iterate)하지 않음**: `for-each` 루프 사용 시 요소가 정렬된 상태로 유지되지 않음
- **요소 추가/제거 시 내부 정렬이 발생하여 성능에 영향**
- **멀티스레드 환경에서는 `PriorityBlockingQueue` 사용 필요**

## 결론
`PriorityQueue<E>`는 기본적으로 **최소 힙(Min-Heap) 기반의 우선순위 큐**를 제공하며, 사용자 정의 Comparator를 활용하여 다양한 우선순위 정렬을 적용할 수 있습니다. 주로 **작업 스케줄링, 다익스트라 알고리즘, 이벤트 처리 시스템** 등에서 많이 사용됩니다.

---
