import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int depth, int start, boolean[] used) {
        if (depth == m) {
            for (int num : answer) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = start; i < n; i++) {
            if (visited[i] || arr[i] == prev) continue;
            visited[i] = true;
            answer[depth] = arr[i];
            prev = arr[i];

            dfs(depth + 1, i, used);

            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 사전순을 위해 정렬
        dfs(0, 0, visited);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}