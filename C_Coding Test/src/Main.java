import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Integer> answer = new ArrayList<>();
        while(n-- > 0) {
            answer.add(Integer.parseInt(br.readLine()));
        }

        List<Integer> tmp = answer;
        Collections.sort(tmp);

        for(int i=0; i<answer.size(); i++) {

        }
    }
}
