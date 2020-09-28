package naver_2021;

public class First {
    private static final String M = "acbbcdc";
    private static final String K = "abc";

    public static String solution(String m, String k) {
        int keyIndex = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < m.length() ; i++){
            char current = m.charAt(i);
            if(keyIndex < k.length() && current == k.charAt(keyIndex)){
                keyIndex++;
                continue;
            }
            sb.append(current);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(M, K));
    }
}
