# Java Annotation

![](https://media.geeksforgeeks.org/wp-content/uploads/20211110125455/JavaAnnotations.jpg)

**Annotation**은 코드에 메타데이터를 추가하는 방법으로, 클래스, 메소드, 필드 등에 부가적인 정보를 제공하기 위해 사용됩니다.  
Annotation은 컴파일러와 런타임 환경에서 주로 사용되며, 개발자가 코드를 쉽게 이해하고 관리할 수 있도록 돕습니다.

---

## Annotation의 특징

1. **코드의 메타데이터 제공**  
   클래스, 메소드, 변수 등에 추가 정보를 첨부하여 특정 동작이나 처리를 지정합니다.

2. **컴파일러 지시문**  
   경고 억제, 코드 자동 생성 등 컴파일러가 처리하는 정보를 제공할 수 있습니다.

3. **런타임 동작 제어**  
   Reflection을 통해 런타임에서 Annotation 정보를 읽고 특정 동작을 수행할 수 있습니다.

4. **유연성**  
   사용자 정의 Annotation을 생성하여 개발 환경에 맞게 사용할 수 있습니다.

---

## 기본 제공 Annotation

Java는 기본적으로 다음과 같은 Annotation을 제공합니다:

### 1. **@Override**

메소드가 부모 클래스의 메소드를 **오버라이드**하고 있음을 명시합니다.  
컴파일러는 이를 통해 오버라이드가 정확히 이루어졌는지 확인합니다.

```java
class Parent {
    void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    @Override
    void display() {
        System.out.println("Child display");
    }
}
```

---

### 2. **@Deprecated**

해당 코드가 더 이상 사용되지 않음을 표시합니다.  
컴파일러는 이 Annotation이 붙은 요소를 사용하면 경고를 출력합니다.

```java
@Deprecated
class OldClass {
    void oldMethod() {
        System.out.println("This method is deprecated.");
    }
}

public class Test {
    public static void main(String[] args) {
        OldClass oldClass = new OldClass();
        oldClass.oldMethod(); // 경고: This method is deprecated
    }
}
```

---

### 3. **@SuppressWarnings**

컴파일러 경고를 억제합니다.  
매개변수로 억제할 경고의 종류를 지정할 수 있습니다.

```java
@SuppressWarnings("unchecked")
public void useRawType() {
    List list = new ArrayList(); // 경고 억제됨
    list.add("Unchecked warning suppressed");
}
```

---

### 4. **@FunctionalInterface**

해당 인터페이스가 **함수형 인터페이스**임을 명시합니다.  
함수형 인터페이스는 하나의 추상 메소드만 가져야 합니다.

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();
}
```

---

## 메타 Annotation

Java에서는 Annotation을 정의할 때 사용할 수 있는 **메타 Annotation**을 제공합니다.

### 1. **@Retention**

Annotation의 유지 기간을 지정합니다.

- `RetentionPolicy.SOURCE`: 소스 코드에서만 유지 (컴파일러에 의해 무시됨)
- `RetentionPolicy.CLASS`: 클래스 파일에 유지 (런타임에 사용 불가)
- `RetentionPolicy.RUNTIME`: 런타임에 유지 (Reflection으로 접근 가능)

```java
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value();
}
```

---

### 2. **@Target**

Annotation이 적용될 수 있는 위치를 지정합니다.

- `ElementType.TYPE`: 클래스, 인터페이스, 열거형에 적용
- `ElementType.METHOD`: 메소드에 적용
- `ElementType.FIELD`: 필드에 적용
- 기타: `CONSTRUCTOR`, `PARAMETER`, `LOCAL_VARIABLE`, `ANNOTATION_TYPE`

```java
@Target(ElementType.METHOD)
@interface MethodAnnotation {
}
```

---

### 3. **@Documented**

Annotation이 Javadoc에 포함되도록 지정합니다.

```java
@Documented
@interface DocumentationAnnotation {
}
```

---

### 4. **@Inherited**

Annotation이 서브클래스에 상속되도록 지정합니다.

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface InheritedAnnotation {
}

@InheritedAnnotation
class ParentClass {
}

class ChildClass extends ParentClass {
}
```

---

## 사용자 정의 Annotation

사용자 정의 Annotation은 개발자가 필요에 따라 생성하여 사용할 수 있습니다.

### 예제: 기본 Annotation 생성

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyCustomAnnotation {
    String value();
    int count() default 1;
}
```

### 사용자 정의 Annotation 사용

```java
public class CustomAnnotationExample {
    @MyCustomAnnotation(value = "Hello", count = 5)
    public void annotatedMethod() {
        System.out.println("Annotated Method Called");
    }
}
```

---

## Reflection을 이용한 Annotation 처리

Reflection을 통해 런타임에 Annotation 정보를 읽고 처리할 수 있습니다.

```java
import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void main(String[] args) throws Exception {
        Method method = CustomAnnotationExample.class.getMethod("annotatedMethod");

        if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
            MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);
            System.out.println("Value: " + annotation.value());
            System.out.println("Count: " + annotation.count());
        }
    }
}
```

출력:
```
Value: Hello
Count: 5
```

---

## Annotation 활용 사례

### 1. **JUnit 테스트**

JUnit에서는 Annotation을 사용하여 테스트 메소드를 지정합니다.

```java
import org.junit.Test;

public class JUnitExample {
    @Test
    public void testMethod() {
        System.out.println("This is a test method.");
    }
}
```

---

### 2. **Spring Framework**

Spring에서는 다양한 Annotation을 사용하여 설정 및 의존성 주입을 처리합니다.

```java
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void doWork() {
        System.out.println("Service is working.");
    }
}
```

---

### 3. **ORM (예: Hibernate)**

Hibernate에서는 Annotation을 사용하여 데이터베이스 매핑을 정의합니다.

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    // Getters and setters
}
```

---

## Annotation의 장점과 단점

### 장점

1. **가독성 향상**  
   코드와 메타데이터가 함께 있어 가독성이 높아집니다.

2. **유연성 제공**  
   다양한 기능을 쉽게 추가하고 설정할 수 있습니다.

3. **개발 시간 단축**  
   XML 설정 파일을 줄이고 간단한 코드를 작성할 수 있습니다.

### 단점

1. **런타임 오버헤드**  
   Reflection 사용 시 성능에 영향을 줄 수 있습니다.

2. **복잡성 증가**  
   잘못된 사용은 코드를 복잡하게 만들 수 있습니다.

---

Annotation은 Java 개발에서 필수적인 도구로, 코드의 유연성과 유지보수성을 높이는 데 큰 역할을 합니다. 이를 적절히 활용하면 코드의 품질을 크게 향상시킬 수 있습니다.