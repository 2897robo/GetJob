import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] sArr = br.readLine().split("-");
        int answer = 0;
        int sum = 0;

        for(int i=0; i<sArr.length; i++) {
            sum = 0;
            String[] parts = sArr[i].split("\\+");
            for(String s : parts) {
                sum += Integer.parseInt(s);
            }
            if(i==0) answer = sum;
            else answer -= sum;
        }

        bw.write(answer + "\n");
        bw.flush();
        br.close(); bw.close();

    }
}
