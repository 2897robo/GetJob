# 🌿 AOP (Aspect-Oriented Programming) 정리

> 📚 참고: [https://b-programmer.tistory.com/388](https://b-programmer.tistory.com/388)

---

## ✅ AOP란?

**AOP (Aspect-Oriented Programming)**, 즉 *관점 지향 프로그래밍*은 **OOP(객체 지향 프로그래밍)의 한계를 보완하기 위해** 등장한 개념이다. 특히 OOP로는 해결하기 어려운 "**횡단 관심사(Cross-Cutting Concerns)**"를 코드의 핵심 로직과 분리해서 모듈화할 수 있게 해준다.

---

## 🚦 OOP의 한계와 AOP의 필요성

OOP는 객체 단위로 책임을 나눠 비즈니스 로직을 잘 분리하지만, 다음과 같은 기능은 여러 클래스에 중복되기 쉽다:

- 로깅
- 트랜잭션 처리
- 보안
- 성능 모니터링
- 예외 처리

이러한 공통 기능을 "**횡단 관심사**"라고 부르며, AOP는 이들을 별도의 모듈로 관리해 **중복 제거 + 관심사 분리**를 실현한다.

---

## 🧩 핵심 개념

| 용어 | 설명 |
|------|------|
| **Aspect** | 횡단 관심사를 모듈화한 단위 (ex: 트랜잭션 관리) |
| **Advice** | 부가기능이 실행되는 실제 동작 코드 (ex: 로깅 메서드) |
| **JoinPoint** | Advice가 적용될 수 있는 지점 (ex: 메서드 호출 시점) |
| **Pointcut** | Advice를 어디에 적용할지 정의 (ex: 특정 패키지, 어노테이션 기준) |
| **Weaving** | Advice + 핵심 로직을 결합하는 과정 (컴파일, 로딩, 런타임 등) |

---

## ⚙️ Advice 종류

- `@Before`: 메서드 실행 전에 수행
- `@After`: 메서드 실행 후 항상 수행
- `@AfterReturning`: 메서드가 정상적으로 리턴된 후 수행
- `@AfterThrowing`: 메서드에서 예외 발생 시 수행
- `@Around`: 메서드 실행 전/후 모두 수행, 메서드 호출 여부도 제어 가능

---

## 🧭 Pointcut 표현식 예시

```java
// execution: 메서드 실행 시점 지정
@Pointcut("execution(* com.example.service.*.*(..))")
public void serviceMethods() {}

// within: 특정 패키지 내부 모든 클래스 대상
@Pointcut("within(com.example.service.*)")
public void serviceLayer() {}

// @annotation: 어노테이션 기준
@Pointcut("@annotation(com.example.annotation.Loggable)")
public void loggableMethod() {}
```

---

## 🧪 AOP 적용 예시

```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[Before] 호출된 메서드: " + joinPoint.getSignature());
    }
}
```

---

## 🏦 횡단 관심사 예시 (은행 시스템)

| 관심사 종류 | 예시 |
|-------------|------|
| 핵심 관심사 | 입금, 출금, 이체 |
| 횡단 관심사 | 보안 검사, 예외 처리, 트랜잭션 관리 |

---

## 🧠 AOP 장점 요약

- ✅ **중복 제거**: 공통 로직을 Aspect로 통합
- ✅ **관심사 분리**: 핵심 비즈니스 로직과 보조 기능 분리
- ✅ **유지보수 향상**: 한 곳만 수정하면 전체에 적용됨
- ✅ **결합도 낮춤**: 핵심 클래스는 본연의 책임에만 집중
- ✅ **선언적 프로그래밍**: 흐름이 아닌 "무엇을 할 것인가"만 정의
- ✅ **확장성 높음**: 새로운 Aspect만 추가하면 기능 확장 가능

---

## ⚠️ AOP 단점

- ❗ **디버깅 어려움**: 런타임에 프록시가 생성되므로 흐름 추적 어려움
- ❗ **학습 난이도**: JoinPoint, Weaving 등 개념이 어렵게 느껴질 수 있음
- ❗ **성능 이슈**: 프록시 객체와 위빙 과정에서 약간의 오버헤드
- ❗ **스프링 의존성**: 스프링 AOP는 스프링 환경에 종속됨

---

## 🧱 프록시 종류

| 프록시 방식 | 설명 |
|--------------|------|
| **JDK 동적 프록시** | 인터페이스 기반, `Proxy` 클래스와 `InvocationHandler` 사용 |
| **CGLIB** | 구체 클래스 상속 기반, 바이트코드 조작으로 프록시 생성 |

👉 `@EnableAspectJAutoProxy(proxyTargetClass = true)`를 통해 CGLIB 사용 강제 가능

---

## ✅ 정리

- AOP는 **횡단 관심사를 효과적으로 분리**하고, **핵심 로직에 집중할 수 있게** 해주는 스프링 핵심 기능 중 하나다.
- 중복 제거와 유지보수성 향상을 위해 꼭 필요한 개념이며,
- 하지만 **과도한 AOP 사용은 코드 추적을 어렵게 만들 수 있으므로**, 목적에 맞게 신중히 사용해야 한다.

