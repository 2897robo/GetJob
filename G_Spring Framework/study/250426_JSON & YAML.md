## 📦 JSON 핵심 정리

### 1. JSON이란?
- 서버 ↔ 클라이언트 통신 시 가장 많이 사용하는 데이터 포맷
- 텍스트 기반이라 사람이 읽기 쉽고
- 언어에 독립적이라 어디서나 사용 가능

### 2. JSON의 단점
- 파싱(Parsing) 오버헤드: 중첩 깊으면 느려짐
- 직렬화/역직렬화 성능 이슈: 항상 변환 과정 필요
- 이진 데이터 지원 불가: 문자열 인코딩해야 해서 느림
- 데이터 타입 제약: 숫자, 문자열, 불리언 정도만 지원

### 3. JSON의 대안 (더 빠른 포맷)
- **Protocol Buffers (Protobuf)**: 구글 개발, 빠른 바이너리 포맷
- **MessagePack**: JSON처럼 생겼지만 훨씬 압축적이고 빠름
- **BSON**: MongoDB에서 사용하는 JSON + Binary
- **Avro**: Hadoop에서 사용하는, 스키마 진화 지원 포맷

### 4. Jackson으로 JSON 처리

**ObjectMapper 기본 사용법**
```java
// Java Object → JSON
String json = new ObjectMapper().writeValueAsString(object);

// JSON → Java Object
Object obj = new ObjectMapper().readValue(json, Object.class);
```

**커스텀 설정**
- FAIL_ON_UNKNOWN_PROPERTIES = false: 모르는 필드 무시
- SerializationFeature.WRITE_DATES_AS_TIMESTAMPS = false: 날짜를 문자열로 변환
- Custom Serializer / Deserializer로 변환 방식 직접 제어 가능

**Response 통일 (ResponseBodyAdvice)**
- 컨트롤러 응답을 CommonResponse 같은 포맷으로 자동 래핑
- String 반환 시 예외 주의 (따로 처리 필요)

---

## 📙 YAML 핵심 정리

### 1. YAML이란?
- 사람 친화적이고 계층 구조를 표현하기 쉬운 데이터 포맷
- 스프링 부트에서 `application.yml`로 많이 사용

### 2. YAML vs Properties 차이

| 비교 항목 | Properties | YAML |
|:---|:---|:---|
| 문법 | key=value | 들여쓰기 + 콜론(:) |
| 계층 구조 | 접두어로 표현 (spring.datasource.url) | 들여쓰기로 자연스럽게 표현 |
| 리스트 표현 | 콤마(,)로 구분 | `-` 기호로 리스트 작성 |
| 가독성 | 간단한건 좋음, 복잡하면 불편 | 복잡해질수록 YAML이 유리 |
| 사용성 | 간단한 설정에 좋음 | 구조적인 설정에 적합 |

### 3. YAML 기본 예시
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db
    username: user
    password: pass

mylist:
  - apple
  - banana
  - orange
```
- 들여쓰기 기반 구조
- 리스트는 `-` 사용

### 4. Jackson으로 YAML 처리 (실무 팁)

**Maven 설정**
```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>2.13.0</version>
</dependency>
```

**사용법**
```java
// 읽기
ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
mapper.findAndRegisterModules(); // LocalDate 지원
Order order = mapper.readValue(new File("order.yaml"), Order.class);

// 쓰기
mapper.writeValue(new File("output.yaml"), order);
```
- Jackson + YAMLFactory를 쓰면 JSON 다루듯 YAML도 읽고 쓸 수 있음

---

## 📌 오늘 핵심 요약 정리
- JSON은 널리 쓰이지만 성능이슈 있음 → 바이너리 포맷 고려할 것
- Jackson으로 JSON/YAML 둘 다 쉽게 처리 가능
- YAML은 구조적인 설정에 훨씬 좋음
- ResponseBodyAdvice로 API 응답을 통일된 형식으로 래핑할 수 있음 (단, String 반환 주의)

---

✅ **오늘 공부한 내용 한 줄 요약**

> "Spring에서는 Jackson을 이용해 JSON/YAML 모두 다루고, ResponseBodyAdvice로 API 응답 포맷을 깔끔하게 통일한다."
