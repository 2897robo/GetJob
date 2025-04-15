# ✅ Getter 사용 지양 이유와 객체에 메시지를 보내야 하는 이유

## 📌 1. Getter란 무엇인가?
- **Getter**: 클래스 내부의 `private` 필드 값을 외부에서 읽기 위해 제공하는 메서드
```java
public class User {
    private String name;
    public String getName() {
        return name;
    }
}
```
- Java Bean 규약상 모든 필드에 대해 `getX()` 메서드를 만드는 것이 일반적이었지만, 이는 캡슐화 위반 및 설계의 문제를 야기함.

---

## ❌ 2. Getter 남용의 문제점

### 1. **캡슐화(Capsulation) 파괴**
- 객체 내부 상태를 외부에 그대로 노출 → 객체의 책임이 줄어듦.
- 구현 변경 시 외부 코드가 줄줄이 영향을 받음 (강한 결합도).

### 2. **객체의 책임이 무너짐**
- 외부에서 객체의 필드 값을 가져와 직접 로직을 수행함.
- 객체는 더 이상 "자기 일을 하는 객체"가 아닌 "데이터 구조체"가 됨.

### 3. **디미터 법칙 위반 가능성 증가**
- 객체 내부 필드 → 그 내부 필드의 메서드까지 체이닝하게 되는 경우 많음 (ex: `user.getAddress().getStreet().getName()`) → 유지보수 지옥.

### 4. **불변성 및 일관성 깨짐**
- 컬렉션 타입을 getter로 노출 시 외부에서 수정 가능성 있음.
```java
public List<Item> getItems() {
    return items; // 위험한 getter
}
```

---

## ✅ 3. 객체에 메시지를 보내라 (Tell, Don’t Ask)

### 객체지향적 설계 핵심 원칙:
> 객체에게 값을 달라고 묻지 말고, "무엇을 해야 할지"를 말해라!

### ❗예시 - 잘못된 설계
```java
if (user.getAge() > 18) {
    // ...로직
}
```

### ✅ 리팩토링
```java
if (user.isAdult()) {
    // ...로직
}
```
- 객체가 스스로 판단하도록 "행동"을 정의해두면 유연성과 응집도가 증가함.

---

## 🎯 4. Getter가 꼭 필요한 경우는?
- **단순 출력용** 데이터 전달 (e.g., DTO)
- **조회 목적**의 불변 객체 (getter만 존재하고, 수정 로직이 없음)
- **값 객체(Value Object)** 패턴

### ❗조회 시 주의점
- 리스트 등의 참조형 객체 반환 시에는 `불변 컬렉션`을 반환할 것:
```java
public List<Item> getItems() {
    return Collections.unmodifiableList(items);
}
```
- 혹은 깊은 복사 후 반환:
```java
return items.stream()
    .map(Item::copy)
    .collect(Collectors.toUnmodifiableList());
```

---

## 🧠 5. 좋은 Getter 대체 예시 모음

| 목적 | getter 방식 ❌ | 메시지 보내는 방식 ✅ |
|------|----------------|-------------------------|
| 사용자 성인 여부 | `user.getAge() > 18` | `user.isAdult()` |
| 상품 할인 여부 | `item.getDiscount() > 0` | `item.isDiscounted()` |
| 자동차 비교 | `car1.getSpeed() > car2.getSpeed()` | `car1.isFasterThan(car2)` |
| 등수 판단 | `player.getScore() == topScore` | `player.isTopScorer(topScore)` |

---

## 🧾 6. 결론 - getter는 죄가 없다, "설계가 문제다"
- getter는 잘 쓰면 편하고 안전하다 (특히 DTO)
```java
- public class UserDto {
    private final int id;
    private final String name;
    private final int age;

    public UserDto(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @JsonProperty("id")
    public int id() {
        return id;
    }

    @JsonProperty("name")
    public String name() {
        return name;
    }

    @JsonProperty("age")
    public int age() {
        return age;
    }
}
```
- 그러나 도메인 모델에서 비즈니스 로직과 결합되지 않고 "데이터 꺼내기만" 한다면 그것은 객체지향이 아님
- getter 남용 대신, 객체에게 행동을 부여하자
- **의도를 담은 메서드**로 표현하자

> 🙋‍♂️ 객체지향은 "내가 데이터를 가지고 와서 처리"하는 것이 아니라,
> **"객체에게 무엇을 하라고 명령"**하는 프로그래밍이다!

---

참고:
- 우아한테크코스 Tecoble: [getter를 사용하는 대신 객체에 메시지를 보내자](https://tecoble.techcourse.co.kr/post/2020-04-28-dont-use-getter/)
- 객체지향 생활체조 원칙
- 이펙티브 자바, 클린 코드, 객체지향의 사실과 오해 등
