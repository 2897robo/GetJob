# ✅ 싱글톤(Singleton) 패턴이란?

> 객체를 오직 하나만 생성해서 어디서든 공유하도록 보장하는 패턴

- 메모리 낭비를 막고, 공통된 자원을 사용할 때 충돌 없이 사용할 수 있게 해줌
- 보통 로그인 세션, DB 커넥션 풀, 설정 파일 등에서 자주 사용됨

---

## 🔧 일반적인 싱글톤 구현 예시 (순수 Java)

```java
public class SingletonService {

    // 1. static 영역에 단 하나만 존재하는 객체 생성
    private static final SingletonService instance = new SingletonService();

    // 2. 외부에서 new로 생성하지 못하도록 private 생성자
    private SingletonService() {}

    // 3. 인스턴스를 반환하는 public 메서드
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체의 로직 호출");
    }
}
```

#### 🔎 사용 방법

```java
SingletonService service1 = SingletonService.getInstance();
SingletonService service2 = SingletonService.getInstance();

System.out.println(service1 == service2); // true
```

---

## 🧠 스프링에서는?

스프링 컨테이너는 **기본적으로 Bean 객체를 싱글톤으로 관리**함.

### 🔥 예시

```java
@Service
public class MyService {
    public void serve() {
        System.out.println("서비스 로직 실행");
    }
}

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) { // DI로 주입받음
        this.myService = myService;
    }

    @GetMapping("/hello")
    public String hello() {
        myService.serve();
        return "Hello, Singleton!";
    }
}
```

> 🔎 `@Service`가 붙은 순간, 해당 클래스는 **싱글톤 Bean**으로 등록됨
> → `MyController`가 이를 주입받아 사용할 때 **항상 같은 인스턴스**가 주입됨

---

## 📌 싱글톤의 장단점 요약

| 장점 | 단점 |
|------|------|
| 메모리 절약 | 테스트가 어려움 (전역 상태 유지 위험) |
| 성능 향상 (객체 재사용) | DI 없이 직접 의존하면 코드 꼬임 발생 |
| 공통 설정, 리소스 공유 용이 | 멀티스레드 환경에서는 동기화 주의 필요 |

---

## 🧪 스프링에서 실제로 싱글톤인지 테스트

```java
@SpringBootTest
public class SingletonTest {

    @Autowired
    ApplicationContext ac;

    @Test
    void singletonBeanTest() {
        MyService service1 = ac.getBean(MyService.class);
        MyService service2 = ac.getBean(MyService.class);

        assertSame(service1, service2); // ✅ 같은 객체인지 확인
    }
}
```

---

## ✅ 정리

- 싱글톤은 객체를 **한 번만 생성**해서 앱 전체에서 재사용
- 스프링은 **기본적으로 Bean을 싱글톤**으로 관리해 우리가 별도 구현하지 않아도 됨
- 하지만, 싱글톤이 가진 문제점도 있음 (전역 상태, 테스트 어려움, 멀티스레드 문제 등)
- Stateless하게 관리하면 스프링의 싱글톤 전략을 안전하게 활용할 수 있음