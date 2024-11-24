import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void modifyArray(int[] arr) {
        arr[0] = 10; // 배열 내부 값 변경
        arr = new int[] {20, 30}; // 참조 자체 변경
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        modifyArray(array);
        System.out.println(array); // 출력: 10 (내부 값 변경 반영)
        // 그러나 array는 여전히 {1, 2, 3}을 가리킴.
    }
}
