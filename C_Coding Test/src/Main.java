import java.util.*;
import java.io.*;

public class Main {
    static int n,m, answer;
    static List<Integer>[] graph;
    static boolean visited[];

    static void dfs(int now, int depth) {
        if(depth == 5) {
            answer = 1;
            return;
        }

        visited[now] = true;

        for(int next : graph[now]) {
            if(!visited[next]) {
                dfs(next, depth+1);
            }
        }

        visited[now] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<> ();
        }

        int a, b;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n];

        for(int i=0; i<n; i++) {
            Arrays.fill(visited, false);
            dfs(i, 1);
            if(answer == 1) break;
        }

        bw.write(answer + "");
        bw.flush();
        br.close();
        bw.close();
    }
}
