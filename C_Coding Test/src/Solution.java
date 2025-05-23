import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int i=1; i<=t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = 0;
            while(st.hasMoreTokens()) {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp%2!=0) cnt+=tmp;
            }
            System.out.println("#" + i + " " + cnt);
        }

        br.close();
        bw.close();
    }
}
