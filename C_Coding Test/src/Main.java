import java.util.*;
import java.io.*;

public class Main {
    static int[][] video;
    static StringBuilder sb = new StringBuilder();

    public static void divideAndConquer(int x, int y, int size) {
        if(sameNum(x, y, size)) {
            sb.append(video[x][y]);
            return;
        }

        sb.append("(");

        int newSize = size / 2;
        divideAndConquer(x, y, newSize);
        divideAndConquer(x, y + newSize, newSize);
        divideAndConquer(x + newSize, y, newSize);
        divideAndConquer(x + newSize, y + newSize, newSize);

        sb.append(")");
    }

    public static boolean sameNum(int x, int y, int size) {
        int color = video[x][y];
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(video[i][j] != color) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        video = new int[n][n];

        for(int i=0; i<n; i++) {
            String tmp = br.readLine();
            for(int j=0; j<n; j++) {
                video[i][j] = tmp.charAt(j) - '0';
            }
        }

        divideAndConquer(0, 0, n);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
