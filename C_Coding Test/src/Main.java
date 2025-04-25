import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr = new int[9][9];
    static ArrayList<int[]> blanks = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void sudoku(int depth) {
        if(depth == blanks.size()) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        int[] current = blanks.get(depth);
        int row = current[0];
        int col = current[1];

        for(int num=1; num<=9; num++) {
            if(isValid(row, col, num)) {
                arr[row][col] = num;
                sudoku(depth+1);
                arr[row][col] = 0;
            }
        }
    }

    public static boolean isValid(int row, int col, int num) {
        for(int i=0; i<9; i++) {
            if(arr[row][i] == num) return false;
        }
        for(int i=0; i<9; i++) {
            if(arr[i][col] == num) return false;
        }

        int startRow = (row/3) * 3;
        int startCol = (col/3) * 3;

        for(int i = startRow; i<startRow+3; i++) {
            for(int j = startCol; j<startCol+3; j++) {
                if(arr[i][j] == num) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }
        br.close();

        sudoku(0);
    }
}
