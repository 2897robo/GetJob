import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] subtractedParts = br.readLine().split("-"); // "-"를 기준으로 나눔

        int result = 0;
        for (int i = 0; i < subtractedParts.length; i++) {
            int sum = 0;
            String[] addedParts = subtractedParts[i].split("\\+"); // "+"로 나누기
            for (String num : addedParts) {
                sum += Integer.parseInt(num); // 숫자로 변환 후 합산
            }

            if (i == 0) result += sum; // 첫 번째 그룹은 무조건 더함
            else result -= sum; // 이후 그룹들은 괄호로 묶어서 빼줌
        }

        System.out.println(result);
    }
}
