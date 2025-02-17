class SharedResource {
    private boolean available = false;

    public synchronized void produce() {
        while (available) {
            try {
                wait(); // 소비자가 처리할 때까지 대기
            } catch (InterruptedException e) {}
        }
        System.out.println("생산 완료!");
        available = true;
        notify(); // 소비자 스레드를 깨움
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait(); // 생산자가 만들 때까지 대기
            } catch (InterruptedException e) {}
        }
        System.out.println("소비 완료!");
        available = false;
        notify(); // 생산자 스레드를 깨움
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce();
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.consume();
            }
        });

        producer.start();
        consumer.start();
    }
}
