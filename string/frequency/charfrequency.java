import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        String str = "hello";
        char[] conv = str.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : conv) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Character ch : map.keySet()) {
            System.out.println(ch + " = " + map.get(ch));
        }
    }
}