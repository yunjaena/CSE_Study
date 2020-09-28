package kakao_2021;

import java.util.*;

public class Second {
    private static String[] ORDER = {"XYZ", "XWY", "WXA"};
    private static int[] COURSE = {2, 3, 4};
    private static boolean[] isVisited = new boolean[11];


    public static String[] solution(String[] orders, int[] course) {
        HashSet<String> hashSet = new HashSet<>();
        List<String> ans;

        for (int y = 0; y < course.length; y++) {
            // course < order 인지 검사
            if (!isPossible(orders, course[y]))
                continue;

            List<String> letters = new ArrayList<>();
            isVisited = new boolean[11];

            // 문자열 생성
            for (String s : orders) {
                generate(letters,s.toCharArray(), s.length(), course[y], 0);
            }
            // 중복 제거
            hashSet.addAll(getMost(letters));
        }

        ans = new ArrayList<>(hashSet);
        // 정렬

        Collections.sort(ans);

        // 반환
        return ans.toArray(new String[0]);
    }

    private static boolean isPossible(String[] orders, int i) {
        int count = 0;
        for (String s : orders) {
            if (s.length() >= i)
                count++;
        }
        return count >= 2;
    }

    // 가장 많이 중복되는 course 리스트를 반환
    public static List<String> getMost(List<String> list) {
        int maxContains = 0;
        List<String> answerString = new ArrayList<>();
        for (int y = 0; y < list.size(); y++) {
            int count = 0;
            for (int i = y; i < list.size(); i++) {
                if (list.get(i).contains(list.get(y)))
                    count++;
            }
            if (count == 1) continue;
            if (count > maxContains) {
                maxContains = count;
                answerString = new ArrayList<>();
                answerString.add(list.get(y));
            } else if (count == maxContains) {
                answerString.add(list.get(y));
            }
        }
        return answerString;
    }


    public static void generate(List<String> list, char[] charSet, int r, int c, int start) {
        if (c == 0) {
            String str = "";
            for (int i = 0; i < r; i++) {
                if (isVisited[i]) str += charSet[i];
            }
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            list.add(sorted);
        }
        for (int i = start; i < r; i++) {
            if (isVisited[i]) continue;
            isVisited[i] = true;
            generate(list,charSet, r, c - 1, i);
            isVisited[i] = false;
        }
    }


    public static void main(String[] args) {
//        solution(ORDER, COURSE);
        for (String s : solution(ORDER, COURSE)) {
            System.out.println(s);
        }
    }
}
