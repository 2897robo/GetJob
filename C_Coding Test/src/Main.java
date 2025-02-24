import java.util.*;
import java.io.*;

public class Main {
    static class Doc {
        private int index;
        private int priority;

        public Doc(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // 문서 개수
            int m = Integer.parseInt(st.nextToken()); // 찾고자 하는 문서 위치

            ArrayDeque<Doc> queue = new ArrayDeque<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder()); // 중요도 내림차순

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new Doc(i, priority));
                priorityQueue.offer(priority);
            }

            int printOrder = 0; // 출력 순서

            while (!queue.isEmpty()) {
                Doc current = queue.poll();

                // 현재 문서의 중요도가 현재 큐에서 가장 높은 우선순위인지 확인
                if (current.priority == priorityQueue.peek()) {
                    priorityQueue.poll();
                    printOrder++;

                    if (current.index == m) { // 우리가 찾던 문서라면 종료
                        sb.append(printOrder + "\n");
                        break;
                    }
                } else {
                    queue.offer(current); // 중요도가 낮다면 다시 큐의 뒤로 보냄
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
