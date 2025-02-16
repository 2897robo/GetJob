# Java Generics (제네릭)

## 1️⃣ **제네릭이란?**
**제네릭(Generics)** 은 Java에서 컴파일 타임에 타입을 지정할 수 있도록 해주는 기능이다. 이를 통해 **타입 안정성을 보장**하고, **형 변환을 최소화**할 수 있다.

### ✅ **제네릭을 사용하는 이유**
1. **컴파일 타임 타입 체크**: 실행 전에 타입 오류를 방지할 수 있음
2. **코드 재사용성 증가**: 타입에 독립적인 코드 작성 가능
3. **형 변환(Casting) 제거**: 불필요한 `Object` 타입 캐스팅을 줄임

---

## 2️⃣ **제네릭 클래스 (Generic Class)**

### **📌 기본적인 제네릭 클래스 구현**
```java
class Box<T> { // T는 타입 파라미터 (Type Parameter)
    private T value;
    
    public void set(T value) {
        this.value = value;
    }
    
    public T get() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generics");
        System.out.println(stringBox.get()); // Hello Generics
    }
}
```

📌 `T`는 **타입 파라미터**로, `String`, `Integer` 등 실제 타입으로 대체됨.

---

## 3️⃣ **제네릭 메서드 (Generic Method)**
**제네릭 클래스가 아니더라도, 메서드에서만 제네릭을 사용할 수도 있음.**

### **📌 제네릭 메서드 예제**
```java
class Utility {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Java", "Generics", "Example"};
        
        Utility.printArray(intArray);
        Utility.printArray(strArray);
    }
}
```

📌 **`<T>`를 메서드 선언 앞에 추가하여 제네릭 메서드로 정의**

---

## 4️⃣ **제네릭 와일드카드 (Wildcard)**
제네릭에서 **? (와일드카드)** 를 사용하면 다양한 타입을 지원할 수 있음.

### ✅ **1. `? extends T` (상한 제한 Bounded Wildcard)**
- `? extends Number` → `Number` 또는 그 **하위 타입**(`Integer`, `Double`, `Float` 등)만 허용
- **읽기 전용 (Read-Only)**

```java
public static double sumList(List<? extends Number> list) {
    double sum = 0;
    for (Number num : list) {
        sum += num.doubleValue();
    }
    return sum;
}
```

### ✅ **2. `? super T` (하한 제한 Lower Bounded Wildcard)**
- `? super Integer` → `Integer` 또는 그 **상위 타입**(`Number`, `Object` 등)만 허용
- **쓰기 전용 (Write-Only)**

```java
public static void addNumbers(List<? super Integer> list) {
    list.add(10);
    list.add(20);
}
```

📌 **상한 제한(`extends`)은 읽기, 하한 제한(`super`)은 쓰기에 유용!**

---

## 5️⃣ **제네릭 인터페이스 (Generic Interface)**
```java
interface Pair<K, V> {
    K getKey();
    V getValue();
}

class PairClass<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public PairClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}
```

📌 **인터페이스에도 제네릭을 적용하여 여러 타입을 지원할 수 있음.**

---

## 6️⃣ **제네릭과 컬렉션 (Collections & Generics)**
Java의 **`List`, `Set`, `Map`** 등의 컬렉션은 제네릭을 활용하여 타입 안정성을 제공한다.

```java
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Generics");
String str = list.get(0); // 형 변환 없이 사용 가능
```

📌 **제네릭을 사용하면 컬렉션에서 형 변환을 제거하고 안전한 타입 체크가 가능!**

---

## 7️⃣ **제네릭과 `record` (Java 14+)**
Java 14 이상에서는 `record`를 활용하여 제네릭을 간결하게 표현 가능

```java
record Pair<K, V>(K key, V value) {}

public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<>("Alice", 90);
        System.out.println(p.key() + " : " + p.value());
    }
}
```

📌 **`record`는 불변(Immutable) 객체를 쉽게 만들 수 있어, DTO나 VO로 활용하기 좋음!**

---

## 8️⃣ **제네릭의 제한 사항**
❌ **제네릭 배열 생성 불가** → `T[] array = new T[10];` (❌ 컴파일 에러)
✔ 해결법: `@SuppressWarnings("unchecked")` + `Object[]` 활용

❌ **기본 타입(Primitive Type) 사용 불가** → `List<int>` (❌ 불가능) → `List<Integer>` 사용

❌ **정적(static) 필드에서 제네릭 사용 불가** → `static T value;` (❌ 컴파일 에러)

❌ **`instanceof`로 제네릭 타입 확인 불가** → `if (obj instanceof T) {}` (❌ 불가능)

---

## ✅ **정리: 백엔드 개발에서 제네릭을 사용하는 주요 사례**
1. **컬렉션 (List, Map, Set)에서 타입 안정성 유지**
2. **제네릭 메서드를 활용한 범용적인 유틸리티 클래스 작성**
3. **데이터 모델(Pair, DTO) 등을 `record`와 함께 간결하게 표현**
4. **`? extends T`, `? super T`를 활용하여 안전한 API 설계**

💡 **제네릭은 백엔드 개발에서 자주 사용되므로 기본적인 개념을 확실히 익혀두는 것이 중요!** 🚀

