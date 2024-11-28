# Java Generic

Java의 **Generic(제네릭)**은 클래스, 인터페이스, 메소드를 작성할 때 데이터 타입을 일반화하여, **타입 안정성**과 **코드 재사용성**을 향상시키는 기능입니다.

---

## Generic의 필요성

Generic이 등장하기 전에는 **Object** 타입을 활용해 다양한 데이터 타입을 처리했습니다. 그러나 이 방식은 아래와 같은 문제점을 가지고 있었습니다:

1. **타입 안정성 부족**: 모든 객체를 Object로 처리하기 때문에 런타임 시 잘못된 타입이 전달될 위험이 있습니다.
2. **캐스팅 필요**: 데이터를 사용할 때마다 명시적으로 타입 캐스팅해야 합니다.
3. **가독성 저하**: 다양한 타입을 처리하는 코드에서 타입 오류를 추적하기 어렵습니다.

### Generic 도입 전의 코드 예제

```java
import java.util.ArrayList;

public class NonGenericExample {
    public static void main(String[] args) {
        ArrayList list = new ArrayList(); // Object 타입으로 처리
        list.add("Hello");
        list.add(10); // 다른 타입도 추가 가능

        // 캐스팅 필요
        String str = (String) list.get(0); // 올바름
        int num = (int) list.get(1); // 올바름

        // 런타임 오류 발생 가능
        String error = (String) list.get(1); // ClassCastException 발생
    }
}
```

### Generic 도입 후의 코드 예제

```java
import java.util.ArrayList;

public class GenericExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(); // String 타입으로 제한
        list.add("Hello");
        // list.add(10); // 컴파일 오류 발생

        String str = list.get(0); // 캐스팅 불필요
        System.out.println("첫 번째 요소: " + str);
    }
}
```

---

## Generic의 장점

1. **타입 안정성(Type Safety) 제공**  
   컴파일 타임에 타입을 확인하여, 런타임 타입 오류를 줄입니다.

2. **타입 캐스팅 불필요**  
   명시적 타입 캐스팅을 제거하여 코드 가독성이 향상됩니다.

3. **코드 재사용성 증가**  
   다양한 데이터 타입에서 재사용할 수 있는 일반화된 코드를 작성할 수 있습니다.

---

## Generic 클래스

### Generic 클래스 정의

제네릭 클래스를 정의할 때는 클래스 이름 뒤에 꺽쇠 괄호(`<>`)를 사용하여 타입 매개변수를 지정합니다.

```java
public class Box<T> {
    private T item; // T는 타입 매개변수

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

### Generic 클래스 사용

```java
public class GenericClassExample {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>(); // T를 String으로 지정
        stringBox.setItem("Hello");
        System.out.println("String Box: " + stringBox.getItem());

        Box<Integer> intBox = new Box<>(); // T를 Integer로 지정
        intBox.setItem(123);
        System.out.println("Integer Box: " + intBox.getItem());
    }
}
```

---

## Generic 메소드

제네릭 메소드는 메소드 레벨에서 타입 매개변수를 사용합니다. 반환 타입 앞에 `<T>`를 선언하여 메소드에 사용할 타입 매개변수를 지정합니다.

```java
public class GenericMethodExample {
    // 제네릭 메소드 정의
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] stringArray = {"Apple", "Banana", "Cherry"};
        Integer[] intArray = {1, 2, 3};

        printArray(stringArray); // 문자열 배열 출력
        printArray(intArray);    // 정수 배열 출력
    }
}
```

---

## Bounded Generic (제한된 제네릭)

제네릭의 타입 매개변수에 **제한(bound)**를 설정할 수 있습니다. `extends` 키워드를 사용하여 특정 타입이나 그 하위 타입만 허용할 수 있습니다.

### 예제: 제한된 제네릭

```java
public class BoundedGenericExample<T extends Number> {
    private T value;

    public BoundedGenericExample(T value) {
        this.value = value;
    }

    public double square() {
        return value.doubleValue() * value.doubleValue();
    }

    public static void main(String[] args) {
        BoundedGenericExample<Integer> intExample = new BoundedGenericExample<>(5);
        System.out.println("Square: " + intExample.square());

        BoundedGenericExample<Double> doubleExample = new BoundedGenericExample<>(3.14);
        System.out.println("Square: " + doubleExample.square());

        // BoundedGenericExample<String> stringExample = new BoundedGenericExample<>("Test"); // 컴파일 오류
    }
}
```

---

## Wildcard (`?`)

제네릭에서 와일드카드(`?`)는 **알 수 없는 타입**을 표현할 때 사용됩니다.

1. `?`: 모든 타입을 허용합니다.
2. `? extends T`: T와 그 하위 타입을 허용합니다.
3. `? super T`: T와 그 상위 타입을 허용합니다.

### 와일드카드 예제

```java
import java.util.ArrayList;
import java.util.List;

public class WildcardExample {
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Banana");

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);

        printList(stringList); // 와일드카드로 모든 타입 허용
        printList(intList);
    }
}
```

---

## Raw Type

Generic을 사용하지 않고, 타입 매개변수를 명시하지 않은 상태를 **Raw Type**이라고 합니다. Raw Type은 Generic의 이점을 잃게 되므로 권장되지 않습니다.

### Raw Type의 문제점

```java
import java.util.ArrayList;

public class RawTypeExample {
    public static void main(String[] args) {
        ArrayList rawList = new ArrayList(); // Raw Type
        rawList.add("Hello");
        rawList.add(123);

        // 런타임 오류 가능성
        String str = (String) rawList.get(1); // ClassCastException 발생 가능
    }
}
```

---

## Generic의 한계

1. **Primitive 타입 사용 불가**  
   제네릭은 객체만 허용하며, `int`, `double` 등의 원시 타입은 사용할 수 없습니다. 이를 위해 **Wrapper 클래스**(`Integer`, `Double`)를 사용해야 합니다.

2. **런타임 타입 정보 소실**  
   컴파일 후 타입 정보가 제거되는 **타입 소거(Type Erasure)** 때문에 특정 상황에서 한계가 있습니다.

3. **Static 컨텍스트에서 사용 제한**  
   타입 매개변수는 인스턴스 수준에서만 사용할 수 있습니다. 따라서 static 변수나 메소드에서 사용이 불가능합니다.

---

## 결론

Java의 Generic은 코드의 안정성과 재사용성을 높여주는 강력한 기능입니다. 올바르게 사용하기 위해서는 Generic의 문법과 한계를 충분히 이해해야 하며, 다양한 데이터 타입을 처리해야 하는 상황에서 적극적으로 활용하면 효율적인 코드를 작성할 수 있습니다.
