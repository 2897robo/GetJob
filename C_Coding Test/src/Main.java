import java.util.*;
import java.io.*;

public class Main {
    static class Problem {
        int p;
        int l;

        public Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        TreeSet<Problem> ts = new TreeSet<> ((o1, o2) -> {
            if(o1.l == o2.l) {
                return o1.p - o2.p;
            }
            return o1.l - o2.l;
        });
        Map<Integer, Integer> map = new HashMap<> ();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            map.put(p, l);
            ts.add(new Problem(p, l));
        }

        int m = Integer.parseInt(br.readLine());
        while(m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            switch(cmd) {
                case "recommend" : {
                    int x = Integer.parseInt(st.nextToken());
                    if(x==1) {
                        sb.append(ts.last().p).append("\n");
                    } else {
                        sb.append(ts.first().p).append("\n");
                    }
                    break;
                }
                case "add" : {
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    ts.add(new Problem(p, l));
                    map.put(p, l);
                    break;
                }
                case "solved" : {
                    int p = Integer.parseInt(st.nextToken());
                    int l = map.get(p);
                    ts.remove(new Problem(p, l));
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close(); bw.close();

    }
}
