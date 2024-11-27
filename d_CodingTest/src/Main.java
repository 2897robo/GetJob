import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Set<String> words = new HashSet<>();
        for(int i=0; i<n; i++) {
            words.add(br.readLine());
        }
        br.close();

        List<String> wordsList = new ArrayList<>(words);

        Collections.sort(wordsList, (String a, String b) -> {
            return (a.length() == b.length()) ? a.compareTo(b) : a.length() - b.length();
        });

        for(String w : wordsList) {
            System.out.println(w);
        }
    }
}