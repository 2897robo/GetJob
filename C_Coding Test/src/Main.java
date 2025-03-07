import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] nArr = new int[n];
        for(int i=0; i<n; i++) {
            nArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nArr);

        int left = 1;
        int right = nArr[n-1] - nArr[0];
        int answer = 0;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int count = 1;
            int prevHouse = nArr[0];

            for(int i=1; i<n; i++) {
                if(nArr[i] - prevHouse >= mid) {
                    count++;
                    prevHouse = nArr[i];
                }
            }

            if(count >= c) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
