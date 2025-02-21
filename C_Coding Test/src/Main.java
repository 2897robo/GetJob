import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 숫자의 개수
        int[] sequence = new int[n]; // 목표 수열
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>(); // 스택 역할
        StringBuilder sb = new StringBuilder(); // 연산 기록 (+, - 저장)

        int current = 1; // 스택에 push할 숫자
        boolean isPossible = true; // 수열을 만들 수 있는지 여부

        for (int num : sequence) {
            // 목표 숫자까지 push
            while (current <= num) {
                stack.push(current++);
                sb.append("+\n");
            }
            // pop 해야 하는 숫자가 맞는지 확인
            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            } else {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            bw.write(sb.toString());
        } else {
            bw.write("NO\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}