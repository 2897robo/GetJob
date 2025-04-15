# Gradle vs Maven 비교 문서

## 1. 개요
Gradle과 Maven은 Java 기반 프로젝트에서 널리 사용되는 빌드 도구다. 둘 다 프로젝트의 빌드, 테스트, 의존성 관리, 배포 등을 자동화해주지만, 철학과 방식에서 많은 차이가 있다.

## 2. 철학적 차이
- **Maven**은 XML 기반의 선언형 방식이다. 무엇을 할지 명시하며, 빌드의 흐름은 Maven이 정해진 순서대로 처리한다.
- **Gradle**은 Groovy 또는 Kotlin DSL 기반의 명령형 방식이다. 사용자가 빌드 프로세스를 유연하게 제어할 수 있다.

## 3. 문법 및 설정 방식
### Maven
- 설정 파일: `pom.xml`
- XML 문법으로 정형화된 구조
- 간단하고 읽기 쉽지만, 반복되는 코드가 많고 유연성이 부족함

### Gradle
- 설정 파일: `build.gradle` 또는 `build.gradle.kts`
- Groovy 또는 Kotlin 기반의 DSL
- 코드로 빌드를 제어할 수 있어 반복 줄이기 쉬움

## 4. 성능 비교
- **Gradle은 빌드 캐시**, **증분 빌드**, **병렬 처리**를 지원해서 빠르다.
- Maven은 순차적 처리만 가능하고, 빌드 캐시 기능이 없다. 대규모 프로젝트에서 느릴 수 있음.

## 5. 의존성 관리
- 둘 다 중앙 저장소(Maven Central, JCenter 등)를 통해 의존성 관리 가능
- Gradle은 다중 저장소 구성, 버전 충돌 해결 등에서 더 유연함

## 6. 생태계 및 IDE 호환성
- IntelliJ, Eclipse 등 대부분의 IDE에서 둘 다 지원
- 오픈소스 프로젝트에서는 Maven이 아직도 많고, 점차 Gradle로 전환 추세

## 7. 사용 예시
### Maven
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Gradle
```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

## 8. 언제 어떤 걸 선택해야 할까?
| 상황 | 권장 도구 |
|------|-----------|
| 프로젝트가 단순하고 표준적임 | Maven |
| 유연한 빌드 설정이 필요함 | Gradle |
| 빌드 속도가 중요함 | Gradle |
| 팀원들이 Gradle DSL에 익숙하지 않음 | Maven |

## 9. 실무 적용 팁
- 새로 시작하는 Spring Boot 프로젝트에서는 Gradle이 더 일반적
- 오픈소스에 PR 보낼 때는 기존 도구 따라가기
- 빌드 속도가 중요하거나 멀티모듈이면 Gradle이 좋다

## 10. 결론
둘 중 무엇이 절대적으로 더 낫다고는 말할 수 없고, 프로젝트 특성과 팀 환경에 맞게 선택하는 것이 중요하다. 다만 Gradle은 현대적인 기능과 성능 측면에서 점점 더 많은 선택을 받고 있다.
