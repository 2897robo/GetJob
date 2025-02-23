import java.util.*;
import java.io.*;

public class Main {
    private static int precedence(char c) {
        if(c=='(' || c==')') return 0;
        if(c=='+' || c=='-') return 1;
        if(c=='*' || c=='/') return 2;
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for(int i=0; i<s.length();i++) {
            char c = s.charAt(i);

            if(Character.isLetter(c)) {
                sb.append(c);
            } else if(c=='(') {
                stack.push(c);
            } else if(c==')') {
                while(!stack.isEmpty() && stack.peek()!='(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        br.close(); bw.close();

    }
}
