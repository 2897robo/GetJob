import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long h = 0;

        st = new StringTokenizer(br.readLine(), " ");
        long[] nArr = new long[n];
        long max = 0;
        for(int i=0; i<n; i++) {
            nArr[i] = Long.parseLong(st.nextToken());
            max = Math.max(nArr[i], max);
        }

        long left = 0;
        long right = max;

        while(left <= right) {
            long mid = left + (right - left) / 2;
            long sum = 0;

            sum = Arrays.stream(nArr)
                    .filter(i -> i > mid)
                    .map(i -> i - mid)
                    .sum();

            if(sum >= m) {
                h = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }


        bw.write(h + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}