# ✅ 어댑터(Adapter) 패턴이란?

> 서로 호환되지 않는 인터페이스를 가진 클래스들을 함께 작동할 수 있게 연결해주는 디자인 패턴

- 즉, **중간에 어댑터 객체를 둬서 호환되지 않는 인터페이스를 맞춰주는 역할**
- 전원 콘센트 어댑터처럼 **형식을 바꿔주는 중간다리**

---

## 💡 언제 쓰는가?
- 기존 시스템을 변경하지 않고 새로운 기능이나 라이브러리를 연결하고 싶을 때
- 서로 다른 인터페이스를 가진 클래스를 함께 사용해야 할 때

---

## 🔧 예시 (순수 Java)

```java
// ✅ 기존 시스템 (Target 인터페이스)
public interface MediaPlayer {
    void play(String filename);
}

// ✅ 새롭게 도입된 시스템 (Adaptee)
public class AdvancedMediaPlayer {
    public void playMp4(String filename) {
        System.out.println("Playing mp4 file: " + filename);
    }
}

// ✅ 어댑터
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer = new AdvancedMediaPlayer();

    @Override
    public void play(String filename) {
        advancedMediaPlayer.playMp4(filename);
    }
}

// ✅ 클라이언트 코드
public class AudioPlayer {
    private MediaPlayer mediaPlayer;

    public AudioPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void play(String filename) {
        mediaPlayer.play(filename);
    }
}

// ✅ 사용
public class Main {
    public static void main(String[] args) {
        MediaPlayer adapter = new MediaAdapter();
        AudioPlayer player = new AudioPlayer(adapter);

        player.play("video.mp4");
    }
}
```

---

## 🧠 스프링에서의 어댑터 패턴 사용 예 (HandlerAdapter)

```java
public interface HandlerAdapter {
    boolean supports(Object handler);
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler);
}
```

- DispatcherServlet은 컨트롤러의 타입이 다 달라도 일관되게 실행할 수 있어야 함
- 그래서 어댑터가 `supports()`로 지원 여부 판단 → `handle()`로 실행
- 다양한 형태의 핸들러(예: `@Controller`, `HttpRequestHandler`)를 **일관된 방식으로 호출**할 수 있게 해줌

```java
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof SimpleController;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return ((SimpleController) handler).handleRequest(request, response);
    }
}
```

---

## 📌 장단점 요약

| 장점 | 단점 |
|------|------|
| 기존 코드 수정 없이 확장 가능 | 코드가 늘어나고 구조가 복잡해질 수 있음 |
| 새로운 인터페이스와 쉽게 연결 가능 | Adapter가 너무 많아질 수 있음 |
| 객체 간 결합도 낮춤 (느슨한 결합) | — |

---

## ✅ 정리
- 어댑터는 **호환되지 않는 인터페이스를 연결해주는 다리**
- 스프링 MVC의 `HandlerAdapter`가 대표적인 사용 예
- 외부 라이브러리나 레거시 시스템과 연결할 때 매우 유용

