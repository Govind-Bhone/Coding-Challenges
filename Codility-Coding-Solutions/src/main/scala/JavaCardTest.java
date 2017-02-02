import java.util.HashMap;

/**
 * Created by govind.bhone on 2/1/2017.
 */
public class JavaCardTest {
    public static void main(String[] args) {
        JavaCardTest mysolution = new JavaCardTest();
        System.out.println(mysolution.solution("23A84Q", "K2Q25J"));
    }

    public int solution(String A, String B) {
        HashMap<Character, Integer> cardValues = new HashMap<>();

        cardValues.put('2', 2);
        cardValues.put('3', 3);
        cardValues.put('4', 4);
        cardValues.put('5', 5);
        cardValues.put('6', 6);
        cardValues.put('7', 7);
        cardValues.put('8', 8);
        cardValues.put('9', 9);
        cardValues.put('T', 10);
        cardValues.put('J', 11);
        cardValues.put('Q', 12);
        cardValues.put('K', 13);
        cardValues.put('A', 14);

        if (A == null || A.length() < 1 || A.length() > 1000) {
            return -1;
        }

        if (B == null || B.length() < 1 || B.length() > 1000) {
            return -1;
        }

        if (A.length() != B.length()) {
            return -1;
        }

        Integer aliceCount = 0;
        for (Integer i = 0; i < A.length(); i++) {
            Character AChar = A.charAt(i);
            Character BChar = B.charAt(i);

            Integer AInteger = cardValues.get(AChar);
            Integer BInteger = cardValues.get(BChar);

            if (AInteger == BInteger) continue;
            if (AInteger > BInteger) aliceCount++;
        }
        return aliceCount;
    }
}


