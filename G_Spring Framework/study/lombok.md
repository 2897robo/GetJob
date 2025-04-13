# Lombok 정리 (Based on Baeldung)

## ✅ Lombok이란?
- 자바에서 **보일러플레이트 코드(반복적인 코드)**를 줄이기 위해 만든 라이브러리
- 컴파일 시점에 **자동으로 getter, setter, 생성자, toString, equals, hashCode** 등을 생성
- **IDE에서 직접 작성하지 않아도 .class 파일에는 자동 생성됨**
- Gradle/Maven 빌드 도구에만 의존, 런타임 시점엔 별도 설정 필요 없음

---

## 📌 Lombok 설치
### Maven
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
    <scope>provided</scope>
</dependency>
```

### Gradle
```groovy
dependencies {
    compileOnly 'org.projectlombok:lombok:1.18.36'
    annotationProcessor 'org.projectlombok:lombok:1.18.36'
}
```

---

## 🎯 자주 쓰는 Lombok 어노테이션 정리

| 어노테이션 | 설명 |
|------------|------|
| `@Getter`, `@Setter` | 모든 필드에 getter/setter 자동 생성 |
| `@ToString` | `toString()` 자동 생성 (exclude 필드 지정 가능) |
| `@EqualsAndHashCode` | `equals()`와 `hashCode()` 자동 생성 (주의: JPA에서는 비추천) |
| `@NoArgsConstructor` | 파라미터 없는 생성자 생성 |
| `@AllArgsConstructor` | 모든 필드 포함한 생성자 생성 |
| `@RequiredArgsConstructor` | `final` 또는 `@NonNull` 필드만 포함한 생성자 생성 |
| `@Data` | `Getter + Setter + toString + equals + hashCode + RequiredArgsConstructor` |
| `@Value` | `@Data` + 불변 클래스 설정 (`final`, `private`) |
| `@Builder` | 빌더 패턴 자동 생성 |
| `@Slf4j` | `log.info()`, `log.debug()` 등 로깅 사용 가능 |
| `@Cleanup` | try-with-resources 대체 (자원 자동 반환) |
| `@Synchronized` | thread-safe 메소드 만들기 (내부 객체로 동기화) |
| `@SneakyThrows` | Checked Exception 명시 없이 던지기 |
| `@With` | 필드 하나만 바꿔 새로운 객체 반환하는 with 메소드 생성 |
| `@FieldNameConstants` | 필드명을 문자열 상수로 생성해주는 내부 static 클래스 생성 |
| `@Delegate` | 다른 객체의 메소드 위임 (composition 스타일 지원) |

---

## 🧠 사용 예시
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
public class User {
    private Long id;
    private String username;
    private String password;
}
```

```java
@Builder
public class ApiClientConfig {
    private String host;
    private int port;
    private boolean useHttps;
}
```

```java
@Slf4j
public class ExampleService {
    public void run() {
        log.info("실행됨");
    }
}
```

---

## ❗주의할 점
- JPA Entity에는 `@Data`, `@EqualsAndHashCode` 사용 주의
- 너무 무분별한 Lombok 사용은 디버깅/코드 가독성 저하 우려
- 팀 기준 정하고 사용하는 게 베스트

---

## ✅ delombok
- Lombok 제거된 순수 Java 코드 생성 가능
- `delombok` 툴로 추후 의존 제거도 가능함 → [https://projectlombok.org/features/delombok](https://projectlombok.org/features/delombok)

---

## 📚 참고
- 원문: [https://www.baeldung.com/intro-to-project-lombok](https://www.baeldung.com/intro-to-project-lombok)
- 공식: [https://projectlombok.org/](https://projectlombok.org/)
