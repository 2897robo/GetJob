import java.util.*;
import java.io.*;

public class Main {
    static int n, answer = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        nQueen(0);

        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void nQueen(int depth) {
        if(depth == n) {
            answer++;
            return;
        }

        for(int i=0; i<n; i++) {
            arr[depth] = i;
            if(isPossible(depth)) {
                nQueen(depth+1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for(int i=0; i<col; i++) {
            if(arr[col] == arr[i]) return false;
            if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) return false;
        }

        return true;
    }
}
