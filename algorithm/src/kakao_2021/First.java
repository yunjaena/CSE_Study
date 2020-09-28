package kakao_2021;

public class First {
    private static final String ID = "=.=";

    public static String solution(String new_id) {
        String answer = "";
        String currentString = "";
        StringBuilder sb = new StringBuilder();
        // 1단계
        currentString = new_id.toLowerCase();

        // 2단계
        for (int i = 0; i < currentString.length(); i++) {
            char c = currentString.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        currentString = sb.toString();

        // 3단계

        boolean isBeforeDot = false;
        sb = new StringBuilder();
        for (int i = 0; i < currentString.length(); i++) {
            char c = currentString.charAt(i);
            if (c == '.') {
                isBeforeDot = true;
            } else if (isBeforeDot) {
                sb.append('.');
                sb.append(c);
                isBeforeDot = false;
            } else {
                sb.append(c);
                isBeforeDot = false;
            }
        }

        if (isBeforeDot)
            sb.append('.');


        // 4단계
            if (sb.length() != 0 && sb.charAt(0) == '.') {
                sb.deleteCharAt(0);
            }
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '.') {
                sb.deleteCharAt(sb.length() - 1);
            }

        currentString = sb.toString();

        // 5단계
        if (currentString.isEmpty())
            currentString = "a";

        // 6단게
        if (currentString.length() >= 16)
            currentString = currentString.substring(0, 15);

        sb = new StringBuilder(currentString);
        if (sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }

        currentString = sb.toString();

        // 7단계
        if (currentString.length() <= 2) {
            int a = 3 - currentString.length();
            for (int i = 0; i < a; i++) {
                currentString += currentString.charAt(currentString.length() - 1);
            }
        }


        return currentString;
    }

    public static void main(String[] args) {
        System.out.println(solution(ID));
    }
}
