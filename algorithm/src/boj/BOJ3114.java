package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3114 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int N  = 1502;
    private static int R;
    private static int C;
    private static int mapA[][];
    private static int mapB[][];
    private static int dp[][];

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);
        mapA = new int[N][N];
        mapB = new int[N][N];
        dp = new int[N][N];

        for (int i = 1; i <= R; i++) {
            s = br.readLine().split(" ");
            for (int j = 1; j <= s.length; j++) {
                char mapTerritory = s[j - 1].charAt(0);
                int count = Integer.parseInt(s[j - 1].substring(1));
                if (mapTerritory == 'A') {
                    mapA[i][j] = count;
                } else {
                    mapB[i][j] = count;
                }
            }
        }


        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                mapA[i][j] += mapA[i][j - 1];
                mapB[i][j] += mapB[i][j - 1];
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                mapA[i][j] += mapA[i - 1][j];
                mapB[i][j] += mapB[i - 1][j];
            }
        }


        for (int j = 1; j <= C; j++) {
            dp[1][j] = mapA[R][j] - mapA[1][j];
        }


        for (int i = 1; i <= C; i++) {
            dp[i][1] = mapA[R][1] - mapA[i][1];
        }


        for (int i = 2; i <= R; i++) {
            for (int j = 2; j <= C; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]
                        + mapB[i - 1][j] - mapB[i - 1][j - 1]
                        + mapA[R][j] - mapA[R][j - 1] - mapA[i][j] + mapA[i][j - 1]);

                if (i - 2 >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]
                            - (mapA[i][j] - mapA[i][j - 1] - mapA[i - 1][j] + mapA[i - 1][j - 1]));
                }

                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]
                        + mapB[i - 1][j] - mapB[i - 1][j - 1]
                        + mapA[R][j] - mapA[R][j - 1] - mapA[i][j] + mapA[i][j - 1]);
            }
        }

        System.out.println(dp[R][C]);
        br.close();
    }

    private static void printMap() {
//        System.out.println("A :");
//        for (int i = 1; i <= R; i++) {
//            for (int j = 1; j <= C; j++) {
//                System.out.print(mapA[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        System.out.println("B :");
//        for (int i = 1; i <= R; i++) {
//            for (int j = 1; j <= C; j++) {
//                System.out.print(mapB[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        System.out.println("DP :");
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
