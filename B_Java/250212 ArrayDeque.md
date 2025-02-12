# ArrayDeque<E> (배열 기반 덱)

![Java Collections Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

`ArrayDeque<E>`는 **Queue**와 **Deque**(Double-Ended Queue, 양방향 큐)를 구현한 클래스이다.  
배열을 기반으로 동작하며, **FIFO(Queue)**와 **LIFO(Stack) 동작을 모두 지원**한다.

---

## **1️⃣ ArrayDeque 개요**
- `Deque` 인터페이스의 **배열 기반(Array-based) 구현체**
- **큐(Queue)와 스택(Stack)으로 모두 활용 가능**
- **양방향에서 삽입/삭제가 O(1)** (중간 삽입/삭제는 느림)
- `null` 값 삽입 불가능 (예외 발생)
- `ArrayList`와 다르게 크기를 동적으로 조절함

---

## **2️⃣ ArrayDeque 내부 구조**
- 내부적으로 **원형 배열(Circular Array)** 사용
- `addFirst()`, `addLast()` → O(1)
- `removeFirst()`, `removeLast()` → O(1)
- 중간 삽입, 삭제 → O(n) (비효율적)

---

## **3️⃣ ArrayDeque 사용법**
### **🔹 기본 선언**
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        // 앞뒤로 삽입 가능
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);

        System.out.println(deque); // [3, 1, 2]
    }
}
```

---

## **1️⃣ ArrayDeque는 양방향 큐(Deque)**
- **Queue (FIFO - 선입선출)**
    - `offerLast()` / `pollFirst()` 를 사용하면 **FIFO 방식**으로 동작
- **Stack (LIFO - 후입선출)**
    - `push()` / `pop()`을 사용하면 **LIFO 방식**으로 동작

---

## **2️⃣ FIFO (Queue)로 사용하는 방법**
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();

        // 뒤에서 추가 (Queue의 enqueue)
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        // 앞에서 제거 (Queue의 dequeue)
        System.out.println(queue.poll()); // 1
        System.out.println(queue.poll()); // 2
        System.out.println(queue.poll()); // 3
    }
}
```
✔ `offer()` ➝ 뒤에서 추가 (`offerLast()`)  
✔ `poll()` ➝ 앞에서 제거 (`pollFirst()`)  
✔ **결과**: `1 → 2 → 3` (FIFO)

---

## **3️⃣ LIFO (Stack)로 사용하는 방법**
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        // 뒤에서 추가 (Stack의 push)
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // 뒤에서 제거 (Stack의 pop)
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
    }
}
```
✔ `push()` ➝ 뒤에서 추가 (`addFirst()`)  
✔ `pop()` ➝ 뒤에서 제거 (`removeFirst()`)  
✔ **결과**: `3 → 2 → 1` (LIFO)

---

## **4️⃣ 핵심 정리**
| 사용 방식 | 추가 메서드 | 제거 메서드 | 동작 방식 |
|---|---|---|---|
| **Queue (FIFO)** | `offerLast()` | `pollFirst()` | **먼저 들어온 요소가 먼저 나감** |
| **Stack (LIFO)** | `push()` | `pop()` | **나중에 들어온 요소가 먼저 나감** |

---

## **4️⃣ ArrayDeque vs 다른 자료구조**
| 특징 | `ArrayDeque` | `LinkedList` | `ArrayList` |
|---|---|---|---|
| **내부 구조** | 원형 배열 | 이중 연결 리스트 | 동적 배열 |
| **Queue 지원** | ✅ O(1) | ✅ O(1) | ❌ |
| **Stack 지원** | ✅ O(1) | ✅ O(1) | ❌ |
| **중간 삽입/삭제** | ❌ O(n) | ✅ O(1) | ❌ O(n) |
| **메모리 효율성** | ✅ 배열 기반(낮음) | ❌ 연결 리스트(높음) | ✅ 배열 기반(낮음) |

✔ **ArrayDeque는 Queue와 Stack의 장점을 모두 가짐!**  
✔ 중간 삽입/삭제가 필요하면 `LinkedList`가 더 적절함.  
✔ 빠른 접근이 필요하면 `ArrayList`가 더 적절함.

---

## **5️⃣ 결론**
✅ `ArrayDeque`는 **Queue & Stack을 동시에 지원하는 빠른 자료구조**  
✅ **양방향 삽입/삭제가 O(1)**, 하지만 **중간 삽입/삭제는 O(n)**  
✅ `Stack`보다 빠르고, `LinkedList`보다 메모리 효율적  
✅ **멀티스레드 환경에서는 `ConcurrentLinkedDeque`를 사용해야 안전**

---
