import java.util.*;
import java.io.*;

public class Main {
    static class Balloon {
        private int idx;
        private int next;

        public Balloon(int idx, int next) {
            this.idx = idx;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Deque<Balloon> bl = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            bl.offer(new Balloon(i+1, Integer.parseInt(st.nextToken())));
        }

        while(n-- > 0) {
            Balloon current = bl.poll();
            sb.append(current.idx).append(" ");

            if(bl.isEmpty()) break;

            int next = current.next;
            if(next > 0) {
                for(int i=1; i<next; i++) {
                    bl.addLast(bl.pollFirst());
                }
            } else {
                for(int i=0; i<Math.abs(next); i++) {
                    bl.addFirst(bl.pollLast());
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close(); bw.close();

    }
}
