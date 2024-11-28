# 자바의 Collection Framework

![](https://blog.kakaocdn.net/dn/mjVFA/btqZBcPCt5e/iwtcUaOcIBEQiCRXIvqEjK/img.jpg)

Java의 **Collection Framework**는 데이터의 집합을 효율적으로 관리하고 조작하기 위한 표준화된 클래스와 인터페이스의 모음입니다. 이 프레임워크는 **자료구조**와 **알고리즘**을 제공하여 개발자가 데이터를 쉽게 저장, 검색, 수정할 수 있도록 돕습니다.

---

## Collection Framework의 주요 구성 요소

Java Collection은 크게 다음과 같은 인터페이스와 클래스로 구성됩니다:

1. **인터페이스 (Interfaces)**: 데이터의 동작 방식을 정의합니다.
    - `Collection`
    - `List`
    - `Set`
    - `Queue`
    - `Map` (Collection 인터페이스를 직접 상속받지 않지만 주요 구성요소)

2. **구현 클래스 (Implementation Classes)**: 각 인터페이스의 동작을 구현한 클래스입니다.
    - 예: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap` 등.

---

## 주요 인터페이스와 클래스 설명

### 1. `List`
**List**는 순서가 있는 데이터의 집합으로, 중복된 요소를 허용합니다. 주요 구현 클래스는 다음과 같습니다:

#### `ArrayList`
- 내부적으로 배열을 사용해 데이터를 관리합니다.
- **빠른 검색**이 가능하지만, 삽입/삭제 시 성능이 저하될 수 있습니다.

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple"); // 중복 허용

        System.out.println("Fruits List: " + fruits);
        System.out.println("첫 번째 과일: " + fruits.get(0));

        fruits.remove("Banana");
        System.out.println("수정된 Fruits List: " + fruits);
    }
}
```

#### `LinkedList`
- **연결 리스트** 기반으로 데이터를 관리합니다.
- 삽입/삭제가 빠르지만, 검색은 느립니다.

```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Numbers: " + numbers);
        numbers.addFirst(5); // 맨 앞에 추가
        System.out.println("After addFirst: " + numbers);

        numbers.removeLast(); // 맨 뒤 요소 제거
        System.out.println("After removeLast: " + numbers);
    }
}
```

---

### 2. `Set`
**Set**은 중복을 허용하지 않는 데이터의 집합입니다. 주요 구현 클래스는 다음과 같습니다:

#### `HashSet`
- **해시 테이블**을 사용하여 데이터를 저장합니다.
- 순서를 보장하지 않습니다.

```java
import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // 중복 요소는 추가되지 않음

        System.out.println("Set: " + set);
    }
}
```

#### `TreeSet`
- 데이터를 **정렬된 순서**로 저장합니다.
- 내부적으로 **이진 트리**를 사용합니다.

```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);

        System.out.println("TreeSet (정렬된 데이터): " + treeSet);
    }
}
```

---

### 3. `Queue`
**Queue**는 FIFO(First In, First Out) 방식으로 데이터를 관리합니다.

#### `PriorityQueue`
- 요소를 **우선순위**에 따라 정렬합니다.

```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(30);
        queue.add(10);
        queue.add(20);

        System.out.println("Queue: " + queue);
        System.out.println("Poll (가장 작은 값 제거): " + queue.poll());
        System.out.println("Queue after poll: " + queue);
    }
}
```

---

### 4. `Map`
**Map**은 키와 값을 쌍으로 저장하는 데이터 구조입니다. 각 키는 고유하며, 값은 중복될 수 있습니다.

#### `HashMap`
- 데이터를 **키-값** 형태로 저장하며, 순서를 보장하지 않습니다.
- 검색과 삽입이 빠릅니다.

```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 2);
        map.put("Cherry", 5);

        System.out.println("Map: " + map);
        System.out.println("Apple의 개수: " + map.get("Apple"));

        map.remove("Banana");
        System.out.println("수정된 Map: " + map);
    }
}
```

#### `TreeMap`
- 키를 **정렬된 순서**로 저장합니다.

```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Banana", 2);
        treeMap.put("Apple", 3);
        treeMap.put("Cherry", 5);

        System.out.println("TreeMap (정렬된 데이터): " + treeMap);
    }
}
```

---

## Collection 클래스들의 주요 메소드

### `add()`와 `addAll()`
- `add(E e)`: 요소를 추가합니다.
- `addAll(Collection<? extends E> c)`: 다른 Collection의 모든 요소를 추가합니다.

```java
ArrayList<String> list1 = new ArrayList<>();
list1.add("A");
list1.add("B");

ArrayList<String> list2 = new ArrayList<>();
list2.add("C");
list2.addAll(list1);

System.out.println("List2: " + list2);
```

### `remove()`와 `clear()`
- `remove(Object o)`: 특정 요소를 제거합니다.
- `clear()`: 모든 요소를 제거합니다.

```java
list2.remove("A");
System.out.println("After remove: " + list2);

list2.clear();
System.out.println("After clear: " + list2);
```

### `contains()`
- `contains(Object o)`: 특정 요소가 포함되어 있는지 확인합니다.

```java
System.out.println("Contains 'A': " + list1.contains("A"));
```

### `size()`와 `isEmpty()`
- `size()`: Collection의 요소 개수를 반환합니다.
- `isEmpty()`: Collection이 비어 있는지 확인합니다.

```java
System.out.println("Size of list1: " + list1.size());
System.out.println("Is list1 empty: " + list1.isEmpty());
```

---

## 결론

Java의 Collection Framework는 데이터 저장, 검색, 수정, 정렬 등 다양한 작업을 간단하고 효율적으로 처리할 수 있도록 도와줍니다. 각 자료구조와 메소드의 특성을 이해하고, 적절한 상황에서 활용하는 것이 중요합니다.
