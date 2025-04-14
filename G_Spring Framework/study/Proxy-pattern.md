# ✅ 프록시(Proxy) 패턴이란?

프록시 패턴은 어떤 객체에 대한 접근을 제어하기 위해 **대리자 객체(Proxy)**를 사용하는 구조적 디자인 패턴입니다.

> 즉, "원래 객체에 직접 접근하지 않고 대리 객체를 통해 접근"하는 방식입니다.

---

## 🧠 언제 쓰는 패턴인가?

- 객체 생성 비용이 클 때 (ex. 이미지, 네트워크 리소스)
- 보안상 직접 접근을 막아야 할 때
- 접근 전/후에 부가 로직을 추가하고 싶을 때
- 클라이언트와 실제 객체 사이에 인터페이스를 유지하고 싶을 때

---

## 🎯 프록시 패턴의 구조

```text
Client --> Proxy --> RealSubject (진짜 객체)
```

- **Subject**: RealSubject와 Proxy가 구현할 공통 인터페이스
- **RealSubject**: 실제로 비즈니스 로직을 수행하는 클래스
- **Proxy**: RealSubject에 대한 접근을 제어하는 대리자

---

## 🔧 Java 예시 (인터페이스 기반)

```java
public interface Service {
    void request();
}

public class RealService implements Service {
    @Override
    public void request() {
        System.out.println("[RealService] 실제 서비스 실행");
    }
}

public class ProxyService implements Service {
    private RealService realService;

    @Override
    public void request() {
        System.out.println("[Proxy] 접근 제어 및 로깅 시작");
        if (realService == null) {
            realService = new RealService();
        }
        realService.request();
        System.out.println("[Proxy] 접근 제어 및 로깅 종료");
    }
}
```

### 🔍 사용 코드
```java
public class Main {
    public static void main(String[] args) {
        Service service = new ProxyService();
        service.request();
    }
}
```

---

## 🌀 스프링에서의 프록시 활용

Spring은 AOP를 통해 프록시 패턴을 내부적으로 사용합니다.

- `@Transactional`, `@Async`, `@Cacheable` 같은 기능들
- 모두 **프록시 객체를 통해** 동작 (실제 메서드 호출 전에 부가 기능 실행)

### 예: `@Transactional`
```java
@Service
public class OrderService {
    @Transactional
    public void placeOrder() {
        // DB 트랜잭션 처리됨 (프록시가 감싸고 있음)
    }
}
```

---

## 📌 프록시 패턴의 장단점

| 장점 | 단점 |
|------|------|
| 접근 제어, 로깅, 지연 로딩 등 유용함 | 클래스 수 증가 가능 |
| 원본 객체에 영향 없이 기능 추가 가능 | 코드 구조가 복잡해질 수 있음 |
| 객체 생성을 나중에 미룰 수 있음 (Lazy loading) | 잘못 설계 시 유지보수 어려움 |

---

## ✅ 정리

- 프록시 패턴은 **원래 객체에 대한 접근을 제어**하는 데 사용
- 스프링의 많은 기능은 프록시를 기반으로 동작 (ex. AOP, 트랜잭션)
- **구조적 패턴**이며, **보안, 성능, 로깅, 트래픽 제어 등** 다양한 상황에서 유용
