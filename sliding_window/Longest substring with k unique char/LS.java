
import java.util.*;

class Solution {
    public int longestKSubstr(String s, int k) {
        int low = 0, high = 0;
        int maxLen = -1;

        Map<Character, Integer> map = new HashMap<>();

        while (high < s.length()) {
            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > k) {
                char left = s.charAt(low);

                map.put(left, map.get(left) - 1);

                if (map.get(left) == 0) {
                    map.remove(left);
                }

                low++;
            }

            if (map.size() == k) {
                maxLen = Math.max(maxLen, high - low + 1);
            }

            high++;
        }

        return maxLen;
    }
}
