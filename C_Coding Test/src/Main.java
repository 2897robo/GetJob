import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            for(int j=0; j<n; j++) {
                arr[j] = br.readLine();
            }

            Arrays.sort(arr);
            boolean b = false;
            for(int j=1; j<n; j++) {
                if(arr[j].startsWith(arr[j-1])) {
                    b=true;
                    break;
                }
            }

            System.out.println(b ? "NO" : "YES");
        }

    }
}
