import java.util.*;
import java.io.*;

public class Main {
    static int n, m, minBlindSpot = Integer.MAX_VALUE;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();

    static int[] dx = {-1, 0, 1, 0}; // 상우하좌
    static int[] dy = {0, 1, 0, -1};

    static int[][][] directions = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);
        System.out.println(minBlindSpot);
    }

    static void dfs(int depth, int[][] currentMap) {
        if (depth == cctvs.size()) {
            minBlindSpot = Math.min(minBlindSpot, countBlindSpots(currentMap));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        for (int[] dirs : directions[cctv.type]) {
            int[][] copiedMap = copyMap(currentMap);
            for (int dir : dirs) {
                watch(copiedMap, cctv.x, cctv.y, dir);
            }
            dfs(depth + 1, copiedMap);
        }
    }

    static void watch(int[][] map, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (isInBounds(nx, ny) && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                map[nx][ny] = 7; // 감시된 영역 표시
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static int[][] copyMap(int[][] origin) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, m);
        }
        return copy;
    }

    static int countBlindSpots(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) count++;
            }
        }
        return count;
    }

    static class CCTV {
        int x, y, type;
        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}