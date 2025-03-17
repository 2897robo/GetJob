import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] four = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void brute(int idx, int result) {
        if(idx == n) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i=0; i<4; i++) {
            if(four[i] > 0) {
                four[i]--;
                int newResult = calculate(result, arr[idx], i);
                brute(idx+1, newResult);
                four[i]++;
            }
        }
    }

    public static int calculate(int a, int b, int op) {
        switch(op) {
            case 0: return a+b;
            case 1: return a-b;
            case 2: return a*b;
            case 3: return a/b;
            default: return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<4; i++) {
            four[i] = Integer.parseInt(st.nextToken());
        }

        brute(1, arr[0]);

        bw.write(max + "\n" + min);
        bw.flush();
        br.close();
        bw.close();
    }
}
