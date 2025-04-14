## 🧩 MVC (Model-View-Controller) 패턴 in Spring

### 📌 개요
MVC는 소프트웨어의 관심사를 3개의 영역으로 나누어 **유지보수성과 확장성**을 높이는 디자인 패턴이다.

- **Model**: 데이터 및 비즈니스 로직
- **View**: 사용자 인터페이스
- **Controller**: 요청과 응답 흐름 제어, 비즈니스 로직 호출

Spring에서는 MVC 패턴을 프레임워크 차원에서 완벽하게 지원한다.

---

### 🛠️ 구조 요약
```
[Client] ---> [DispatcherServlet]
                |
         [HandlerMapping]
                |
         [Controller]
                |
         [Service]
                |
         [Repository]
                |
         [DB]
                |
        [Model -> ViewResolver -> View]
```

---

### ✅ 코드 예시

#### 1. Controller
```java
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
```

#### 2. Service
```java
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getName());
    }
}
```

#### 3. Repository
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA가 구현체를 자동으로 생성
}
```

#### 4. View (Thymeleaf Template)
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>User Info</title>
</head>
<body>
<h1>사용자 정보</h1>
<p>ID: <span th:text="${user.id}"></span></p>
<p>이름: <span th:text="${user.name}"></span></p>
</body>
</html>
```

---

### 🔍 핵심 포인트
- Spring의 **DispatcherServlet**이 MVC의 진입점
- `@Controller`, `@Service`, `@Repository`는 Spring이 관리하는 Bean (의존성 자동 주입)
- ViewResolver를 통해 View 이름을 실제 파일 경로로 매핑

---

### ✨ 결론
Spring MVC는 웹 애플리케이션을 **효율적으로 분리**하여 관리할 수 있게 해준다. 이 구조는 개발, 테스트, 유지보수 시 명확한 역할 분담과 책임 경계를 제공하여 협업과 확장에 매우 유리하다.
