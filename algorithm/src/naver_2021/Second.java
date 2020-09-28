package naver_2021;

import java.util.*;

public class Second {
    private static final int[][] BLOCK = {{0, 92}, {1, 20}, {2, 11}, {1, -81}, {3, 98}};

    public static int[] solution(int[][] blocks) {
        int[] answer = {};
        Integer dp[][] = new Integer[16][16];
        int currentIndex = -1;
        int i = 0;
        int maxHeight = 0;
        for (int y = 0; y < blocks.length; y++) {
            if (blocks[y][0] < currentIndex || i < blocks[y][0]) {
                i++;
            }
            dp[i][blocks[y][0]] = blocks[y][1];
            currentIndex = blocks[y][1];
            maxHeight = y + 1;
        }


        fill(dp, maxHeight);
        print(dp, maxHeight);
        return answer;
    }

    public static void print(Integer[][] dp, int maxHeight) {

        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < y + 1; x++) {
                System.out.print(dp[y][x]);
            }

            System.out.println();
        }
    }


    public static void fill(Integer[][] dp, int maxHeight) {
        while (!isFilled(dp, maxHeight)) {
            for (int y = 0; y < maxHeight; y++) {
                for (int x = 0; x < y + 1; x++) {
                    firstCondition(dp, x, y);
                    secondCondition(dp, x, y);
                    ThirdCondition(dp, x, y);
                }
            }
            print(dp, maxHeight);
        }
    }

    public static void firstCondition(Integer[][] dp, int x, int y) {
        if (isValidWithOutNull(dp, x, y - 1) && isValid(dp, x, y) && isValid(dp, x + 1, y)) {
            dp[y - 1][x] = dp[y][x] + dp[y][x + 1];
        }
    }

    public static void secondCondition(Integer[][] dp, int x, int y) {
        if (isValid(dp, x - 1, y - 1) && isValid(dp, x, y) && isValidWithOutNull(dp, x - 1, y)) {
            dp[y][x - 1] = dp[y - 1][x - 1] - dp[y][x];
        }
    }

    public static void ThirdCondition(Integer[][] dp, int x, int y) {
        if (isValid(dp, x, y - 1) && isValid(dp, x, y) && isValidWithOutNull(dp, x + 1, y)) {
            dp[y][x + 1] = dp[y - 1][x] - dp[y][x];
        }
    }

    public static boolean isValidWithOutNull(Integer[][] dp, int x, int y) {
        if (x < 0 || y < 0 || x > dp[0].length || y > dp[0].length || dp[y][x] != null)
            return false;
        return true;
    }

    public static boolean isValid(Integer[][] dp, int x, int y) {
        if (x < 0 || y < 0 || x > dp[0].length || y > dp[0].length || dp[y][x] == null)
            return false;
        return true;
    }

    public static boolean isFilled(Integer[][] dp, int maxHeight) {
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < y + 1; x++) {
                if (dp[y][x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int n : solution(BLOCK)) {
            System.out.println(n);
        }
    }
}
