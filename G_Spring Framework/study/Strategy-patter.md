# 🤜 Anti-OOP vs 전략(Strategy) 패턴 비교 정리

## 🔍 개념 요약

| 구분 | Anti-OOP 리팩토링 접근 | 전략(Strategy) 패턴 |
|------|--------------------------|------------------------|
| 목표 | if-else, switch 제거로 객체지향 강화 | 알고리즘(전략)을 객체로 캡슐화하여 유연하게 교체 |
| 핵심 아이디어 | 분기문을 인터페이스 다형성으로 대체 | 다양한 알고리즘을 동일한 인터페이스로 통합 |
| 중심 키워드 | 역할 분리, 책임 주도 설계, 추상화 | 알고리즘 캡슐화, 런타임 전략 변경 |
| 장점 | 중복 제거, 응집도 향상, SRP 충족 | 코드 유연성, 테스트 용이성, OCP 충족 |
| 사용 방식 | 중복된 분기 로직을 도메인 행위로 분리 | 실행 로직을 각 전략 객체로 추출 |

---

## 🧠 전략 패턴이란?

> **같은 문제를 해결하는 여러 알고리즘(전략)을 각각 클래스로 캡슐화**하고,
> **클라이언트는 인터페이스를 통해 해당 전략을 선택해 사용하는 패턴**

```java
public interface DiscountStrategy {
    int calculateDiscount(int price);
}

public class NaverDiscount implements DiscountStrategy {
    public int calculateDiscount(int price) {
        return (int)(price * 0.1);
    }
}

public class FancafeDiscount implements DiscountStrategy {
    public int calculateDiscount(int price) {
        return Math.min(price, 1000);
    }
}
```

```java
public class DiscountService {
    private DiscountStrategy strategy;

    public DiscountService(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public int getDiscountPrice(int price) {
        return strategy.calculateDiscount(price);
    }
}
```

---

## 💥 Anti-OOP 패턴이란?

> **절차적 분기 로직이 도배된 if-else 지옥**에서 벗어나기 위한 객체지향적 리팩토링 방식
> 전략 패턴을 사용하는 방향성과 거의 같지만, 더 현실적인 도메인 중심 리팩토링을 포함

### 예시

```java
public class PaymentService {
    public long getDiscountAmount(String code, long amount) {
        if ("NAVER".equals(code)) {
            return amount * 0.1;
        } else if ("FANCAFE".equals(code)) {
            return Math.min(1000, amount);
        }
        return 0;
    }
}
```

> 위 코드는 SRP, OCP 위반.

### 리팩토링 방향
- 할인 정책을 인터페이스로 추출 (e.g. `DiscountPolicy`)
- 할인 정책 구현 클래스를 분리 (e.g. `NaverDiscountPolicy`, `FancafeDiscountPolicy`)
- 할인 정책 선택은 팩토리나 enum으로 추상화 → 전략 패턴과 유사한 구조 도달

---

## 🎯 핵심 차이점

| 항목 | Anti-OOP | 전략 패턴 |
|------|----------|------------|
| 시작점 | 분기문 제거와 도메인 설계에서 출발 | 알고리즘 교체 요구에서 출발 |
| 포커스 | 중복 제거 + 도메인 책임 중심 | 유연한 알고리즘 변경 가능성 중심 |
| 도입 시점 | 레거시 코드 리팩토링 과정에서 자연스럽게 등장 | 설계 초기부터 고려 가능 |
| OCP 만족도 | 부분적 만족 (팩토리 if 분기 남음) | 완전 만족 (전략 교체로 해결) |

---

## ✅ 결론

- Anti-OOP 리팩토링은 "**전략 패턴으로 가는 여정**"이라고 볼 수 있음
- 전략 패턴은 객체지향의 **다형성과 OCP**를 실현하는 강력한 도구
- 두 개념 모두 "if 지옥"을 없애고, **의미 있는 객체로 책임을 분리**하려는 철학을 공유함

> ✅ 결국 둘 다 "좋은 객체지향 설계로 가는 길"이며, Anti-OOP는 전략 패턴의 현실적인 도입점이라 보면 된다.

[참고자료](https://redutan.github.io/2016/03/31/anti-oop-if)
