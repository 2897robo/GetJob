import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static boolean found = false;

    public static boolean isGood(String s) {
        int len = s.length();
        for(int i=1; i<=len/2; i++) {
            String a = s.substring(len - i * 2, len - i);
            String b = s.substring(len - i);
            if(a.equals(b)) return false;
        }
        return true;
    }

    public static void dfs(String s) {
        if(found) return;

        if(s.length() == n) {
            System.out.println(s);
            found = true;
            return;
        }

        for(int i=1; i<=3; i++) {
            String next = s+i;
            if(isGood(next)) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        br.close();

        dfs("");
    }
}
