class MyThread implements Runnable {
    public void run() {
        try {
            for(int i=0; i<5; i++) {
                System.out.println(Thread.currentThread().getName() + " 실행 중...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " 강제 종료됨!");
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread());
        t1.start();

        System.out.println("메인 스레드: t1이 종료될 때까지 대기");
        t1.join();  // 메인 스레드가 t1 스레드가 끝날 때까지 대기
        System.out.println("메인 스레드 종료");
    }
}
