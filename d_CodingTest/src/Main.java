import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        Integer[] arr = new Integer[n.length()];
        for(int i=0; i<n.length(); i++) {
            arr[i] = n.charAt(i) - '0';
        }

        Arrays.sort(arr, Collections.reverseOrder());

        for(int i:arr) System.out.print(i);
    }
}