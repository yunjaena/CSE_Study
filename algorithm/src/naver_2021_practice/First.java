package naver_2021_practice;

public class First {
    private static final int K = 5;
    private static final int NEED[] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public static int solution(int k) {
        if(k <= 1)
            return 0;

        return 1;
    }

    public static void main(String[] args) {
        System.out.println(solution(K));
    }
}
