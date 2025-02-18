import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());
        Map<String, Long> map = new TreeMap<> ();
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();
            String name = st.nextToken();
            if(map.containsKey(name)) {
                map.put(name, map.get(name)+1);
            } else {
                map.put(name, 1L);
            }
        }

        for(Map.Entry<String, Long> entry : map.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue() + "\n" );
        }
        bw.flush();
        br.close(); bw.close();
    }
}
