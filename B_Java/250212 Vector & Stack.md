![Java Collection Framework](https://camo.githubusercontent.com/e9e790e3b06de82cd73140fe794d59a4c2c8ba461830853b019fb60074f53f86/68747470733a2f2f626c6f672e6b616b616f63646e2e6e65742f646e2f6d6a5646412f6274715a426350437435652f6977746355614f634942455169435258497671456a4b2f696d672e6a7067)

# **Stack과 Vector in Java**

## **1. Stack 클래스**
### **1.1 Stack이란?**
- `Stack`은 **LIFO(Last-In-First-Out, 후입선출)** 방식의 자료구조이다.
- `Vector` 클래스를 상속받아 구현된 동기화된(Stack은 synchronized) 컬렉션이다.
- 일반적으로 `Deque`(특히 `ArrayDeque`)이 `Stack`보다 더 성능이 좋고 자주 사용된다.

### **1.2 Stack 주요 메서드**
```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        stack.push(1);  // 스택에 요소 추가
        stack.push(2);
        stack.push(3);
        
        System.out.println(stack.pop());  // 3 (마지막 요소 제거 및 반환)
        System.out.println(stack.peek()); // 2 (제거 없이 최상위 요소 확인)
        System.out.println(stack.isEmpty()); // false
    }
}
```

### **1.3 Stack의 특징**
- **동기화(synchronized) 지원** → 멀티스레드 환경에서 안전하지만 성능이 떨어질 수 있음.
- **LIFO(Last In First Out) 방식**
- **`Vector`를 상속받기 때문에 일부 메서드가 불필요하게 포함됨** → 현대적인 개발에서는 `ArrayDeque`를 더 많이 사용함.

### **1.4 Stack 사용 예제**
#### **(1) DFS(깊이 우선 탐색)에서의 활용**
```java
import java.util.*;

public class DFSExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        while (!stack.isEmpty()) {
            System.out.println("방문 노드: " + stack.pop());
        }
    }
}
```

#### **(2) 괄호 검사 (Valid Parentheses)**
```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((c == ')' && top != '(') ||
                (c == '}' && top != '{') ||
                (c == ']' && top != '[')) return false;
        }
    }
    return stack.isEmpty();
}
```

### **1.5 Stack의 단점**
- `Vector`를 상속받기 때문에 **불필요한 동기화 기능**이 포함되어 있어 단일 스레드 환경에서는 비효율적임.
- **성능이 떨어짐** → 대신 `ArrayDeque`을 추천.

### **1.6 Stack을 대체할 수 있는 자료구조**
✅ `Deque<Integer> stack = new ArrayDeque<>();`  → `Stack` 대신 `ArrayDeque`을 사용하는 것이 더 성능이 좋고 효율적이다.

---

## **2. Vector 클래스**
### **2.1 Vector란?**
- `Vector`는 **List 인터페이스를 구현한 동기화된(synchronized) 컬렉션**
- `ArrayList`와 거의 유사하지만, 모든 메서드가 동기화되어 있음.
- 멀티스레드 환경에서 안전하지만, 단일 스레드 환경에서는 `ArrayList`가 더 성능이 좋음.

### **2.2 Vector 주요 메서드**
```java
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        
        System.out.println(vector.get(1));  // B
        vector.remove(1);
        System.out.println(vector);  // [A, C]
    }
}
```

### **2.3 Vector와 ArrayList 비교**
| 특성 | Vector | ArrayList |
|---|---|---|
| 동기화 | O (synchronized) | X (비동기, 필요하면 `Collections.synchronizedList` 사용) |
| 성능 | 상대적으로 느림 | 빠름 |
| 멀티스레드 지원 | O | X (직접 동기화 필요) |
| 확장 방식 | 용량이 부족할 때 2배 증가 | 용량이 부족할 때 1.5배 증가 |

### **2.4 Vector의 단점**
- `ArrayList`보다 **성능이 떨어짐**
- **동기화 오버헤드**가 존재 → 멀티스레드 환경에서 사용하지 않는다면 `ArrayList`를 쓰는 것이 더 유리함.

---

## **3. Stack vs. Vector**
| 특성 | Stack | Vector |
|---|---|---|
| 동기화 | O (synchronized) | O (synchronized) |
| 자료구조 | LIFO(스택 구조) | 동적 배열 (리스트) |
| 주요 기능 | `push()`, `pop()`, `peek()` | `add()`, `remove()`, `get()` |
| 추천 대체 클래스 | `ArrayDeque` | `ArrayList` |
| 사용 사례 | DFS, 괄호 검사, 함수 호출 스택 | 멀티스레드 환경에서 리스트 필요 시 |

---

## **4. 결론**
- `Stack`은 LIFO 방식이지만, `ArrayDeque`이 더 좋은 성능을 제공하므로 **대체 가능**
- `Vector`는 동기화 지원이 필요할 때만 사용하며, 일반적으로는 `ArrayList`를 더 많이 사용함
- **단일 스레드 환경에서는 `Stack` 대신 `ArrayDeque`, `Vector` 대신 `ArrayList`를 사용하는 것이 더 좋음!**

