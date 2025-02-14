## **📌 1️⃣ 패키지 (Package)란?**
### **✅ 패키지란?**
- **관련 있는 클래스와 인터페이스를 묶어놓은 그룹**
- **이름 충돌을 방지**하고, **코드를 더 체계적으로 관리**할 수 있음
- 예를 들어, `java.util.*` 안에는 **컬렉션 프레임워크** 관련 클래스(`ArrayList`, `HashMap` 등)가 포함됨.

---

### **✅ 패키지 선언 및 사용법**
- **클래스의 최상단에 `package 패키지명;`을 선언**하면 해당 패키지에 속하게 됨.
- **다른 패키지에 있는 클래스를 사용하려면 `import`를 해야 함.**

#### **📌 예제 1: 패키지 선언**
```java
package com.example.myapp; // 해당 클래스는 com.example.myapp 패키지에 속함

public class MyClass {
    public void sayHello() {
        System.out.println("Hello from MyClass!");
    }
}
```

---

### **✅ import 문**
- **다른 패키지의 클래스를 사용하려면 `import` 필요**
- 예를 들어, `java.util.ArrayList`를 사용하려면:

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        System.out.println(list.get(0));
    }
}
```

---

### **✅ 패키지 구조 & 접근 제어자**
| 접근 제어자 | 같은 패키지 | 다른 패키지 (상속 O) | 다른 패키지 (상속 X) |
|------------|------------|-----------------|-----------------|
| **public** | O | O | O |
| **protected** | O | O | X |
| **default** (아무것도 안 적음) | O | X | X |
| **private** | X | X | X |

- **`public`**: 어디서든 접근 가능
- **`protected`**: 같은 패키지 또는 상속받은 클래스에서 접근 가능
- **`default`** (아무것도 안 붙이면): 같은 패키지에서만 접근 가능
- **`private`**: 해당 클래스 내부에서만 접근 가능

---

## **📌 2️⃣ 패키지 실습**
### **📌 실습 1: 패키지 만들기 & 클래스 사용**
#### **📌 1. `com.example.utils` 패키지에 유틸 클래스 만들기**
📌 `com/example/utils/Utils.java`
```java
package com.example.utils;

public class Utils {
    public static void printMessage(String message) {
        System.out.println("Message: " + message);
    }
}
```

#### **📌 2. `Main` 클래스에서 유틸 클래스 사용하기**
📌 `Main.java`
```java
import com.example.utils.Utils; // 패키지 내 클래스 가져오기

public class Main {
    public static void main(String[] args) {
        Utils.printMessage("Hello from package!");
    }
}
```

---

## **📌 3️⃣ 패키지 핵심 요약**
1️⃣ **패키지란?** 관련 있는 클래스를 묶어 관리하는 개념  
2️⃣ **import 문이 필요하다!** 다른 패키지에 있는 클래스를 사용할 땐 `import`  
3️⃣ **접근 제어자 주의** (`public`, `protected`, `default`, `private`)

---
