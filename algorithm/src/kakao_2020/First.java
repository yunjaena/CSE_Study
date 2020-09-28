package kakao_2020;

public class First {
    public static final String LETTER = "abcabcabcabcdededededede";

    public static int solution(String s) {
        int answer = s.length();
        for (int i = 1; i < s.length(); i++) {
            int length = getZipLength(s, i);
            if (length < answer) answer = length;
        }
        return answer;
    }

    public static int getZipLength(String s, int splitLength) {
        if (s.length() <= splitLength) return s.length();
        String before = s.substring(0, splitLength);
        String zipString = "";
        int sameCount = 1;
        String currentString = "";
        for (int i = splitLength; i + splitLength <= s.length(); i += splitLength) {
            currentString = s.substring(i, i + splitLength);
            if (before.equals(currentString)) {
                sameCount++;
            } else {
                zipString += (sameCount > 1) ? sameCount + before : before;
                before = currentString;
                sameCount = 1;
            }
        }


        zipString += (sameCount > 1) ? sameCount + before : before;
        if (s.length() % splitLength != 0) {
            int lastLength = s.length() % splitLength;
            zipString += s.substring(s.length() - lastLength);
        }
        System.out.println(splitLength + " : " + zipString.length() + " : " + zipString);  //2abcdede
        return zipString.length();
    }

    public static void main(String[] args) {
        System.out.println(solution(LETTER));
    }
}
