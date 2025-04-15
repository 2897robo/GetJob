# REST vs GraphQL vs gRPC 코드 기반 비교 문서

이 문서는 같은 기능을 세 가지 방식(REST, GraphQL, gRPC)으로 구현한 코드 예시를 기반으로, **각 방식의 차이점**을 세부적으로 비교하고 설명합니다.

---

## 1️⃣ 공통 도메인: User (사용자 정보)
```java
// User.java (공통 도메인 모델)
public class User {
    private Long id;
    private String name;
    private int age;

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getter들 생략
}
```

---

## 2️⃣ REST 방식

### ✅ Controller (Spring Boot)
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = List.of(
        new User(1L, "giuk", 26),
        new User(2L, "chulsoo", 60)
    );

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
```

### 🧠 특징 및 설명
- URL에 자원 식별자(id)를 포함하고 `@PathVariable`로 바인딩
- JSON 형태로 반환, HTTP Status Code (`200 OK`, `404 Not Found`)를 명시적으로 사용
- **장점**: 간단하고 직관적이며 브라우저에서 테스트하기 쉬움
- **단점**: 데이터가 고정되고, 오버페칭/언더페칭 발생 가능

---

## 3️⃣ GraphQL 방식

### ✅ Schema (schema.graphqls)
```graphql
type Query {
    user(id: ID!): User
}

type User {
    id: ID!
    name: String
    age: Int
}
```

### ✅ Resolver
```java
@Component
public class UserResolver implements GraphQLQueryResolver {
    public User getUser(Long id) {
        return List.of(
            new User(1L, "giuk", 26),
            new User(2L, "chulsoo", 60)
        ).stream()
         .filter(user -> user.getId().equals(id))
         .findFirst().orElse(null);
    }
}
```

### 🧠 특징 및 설명
- 클라이언트가 원하는 필드만 요청 가능:
```graphql
{
  user(id: 1) {
    name
    age
  }
}
```
- **장점**: 오버페칭/언더페칭 방지, 하나의 endpoint(`/graphql`)만 존재
- **단점**: 학습 비용, 에러 핸들링이 명확하지 않음 (HTTP Status 대신 null 응답)

---

## 4️⃣ gRPC 방식

### ✅ proto 정의 (user.proto)
```proto
syntax = "proto3";

service UserService {
  rpc GetUser (UserRequest) returns (UserResponse);
}

message UserRequest {
  int64 id = 1;
}

message UserResponse {
  int64 id = 1;
  string name = 2;
  int32 age = 3;
}
```

### ✅ Server 구현
```java
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse user = UserResponse.newBuilder()
            .setId(1)
            .setName("giuk")
            .setAge(26)
            .build();

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
```

### 🧠 특징 및 설명
- 양방향 스트리밍과 고성능 바이너리 전송 (Protocol Buffer)
- **장점**: 빠른 속도, 타입 안정성, 마이크로서비스 간 통신에 적합
- **단점**: 브라우저 테스트 불가, 디버깅 어려움, HTTP2 기반이므로 학습 필요

---

## 💡 요약 비교표
| 항목 | REST | GraphQL | gRPC |
|------|------|---------|------|
| 요청방식 | URL 경로 기반 | 쿼리 기반 (POST) | 메서드 호출 (HTTP/2) |
| 응답 포맷 | JSON | JSON | ProtoBuf |
| 오버페칭 방지 | ❌ | ✅ | ✅ |
| 성능 | 보통 | 보통~빠름 | 매우 빠름 |
| 학습난이도 | 낮음 | 중간 | 높음 |
| 브라우저 테스트 | 가능 | 가능 | 불가능 |
| 마이크로서비스 적합도 | 중 | 중 | 매우 높음 |

---

## 🔚 결론
- 프론트와 자주 협업해야 하는 환경에서는 **GraphQL**
- 단순 웹 애플리케이션이나 외부 API 제공시엔 **REST**
- 마이크로서비스 간 내부 통신에는 **gRPC**
