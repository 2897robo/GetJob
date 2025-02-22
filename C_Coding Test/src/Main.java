import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        Deque<Character> stack = new ArrayDeque<>();
        int answer = 0;
        int tmp = 1;

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(c == '(') {
                stack.push(c);
                tmp *= 2;
            } else if(c== '[') {
                stack.push(c);
                tmp *= 3;
            } else if(c== ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }

                if(s.charAt(i-1) == '(') {
                    answer += tmp;
                }

                stack.pop();
                tmp /= 2;
            } else if(c==']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }

                if(s.charAt(i-1) == '[') {
                    answer += tmp;
                }

                stack.pop();
                tmp /= 3;
            }

        }

        if(!stack.isEmpty()) {
            answer = 0;
        }
        bw.write(answer+"\n");
        bw.flush();
        br.close(); bw.close();
    }
}
