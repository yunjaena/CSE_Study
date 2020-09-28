package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1744 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Integer> minusList;
    private static List<Integer> plusList;
    private static int answer;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        minusList = new ArrayList<>();
        plusList = new ArrayList<>();
        answer = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0)
                plusList.add(num);
            else
                minusList.add(num);
        }

        Collections.sort(plusList);
        Collections.sort(minusList);

        if (minusList.size() % 2 != 0)
            answer += minusList.get(minusList.size() - 1);

        if (minusList.size() > 1) {
            for (int i = 0; i < minusList.size() - 1; i += 2) {
                answer += (minusList.get(i) * minusList.get(i + 1));
            }
        }


        if (plusList.size() % 2 != 0)
            answer += plusList.get(0);

        if (plusList.size() > 1) {
            for (int i = plusList.size() - 1; i >= 1; i -= 2) {
                if (plusList.get(i) != 1 && plusList.get(i - 1) != 1)
                    answer += (plusList.get(i) * plusList.get(i - 1));
                else
                    answer += (plusList.get(i) + plusList.get(i - 1));
            }
        }

        System.out.println(answer);
        br.close();
    }
}


/*

10
-5
-4
-3
0
1
2
3
4
5
6

20 1 6 20 60
 */