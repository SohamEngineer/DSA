import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        String s = "egg";
        String t = "add";

        // Step 1: Isomorphic strings must have the same length
        if (s.length() != t.length()) {
            System.out.println(false);
            return;
        }

        // Stores mapping from s -> t
        // Example: e -> a, g -> d
        HashMap<Character, Character> mapST = new HashMap<>();

        // Stores mapping from t -> s
        // Example: a -> e, d -> g
        // This is needed to make sure the mapping is one-to-one
        HashMap<Character, Character> mapTS = new HashMap<>();

        // Traverse both strings at the same time
        for (int i = 0; i < s.length(); i++) {

            // Get character from both strings at the same index
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Check mapping from s -> t
            // If c1 was already mapped to a different character,
            // then the strings are not isomorphic
            if (mapST.containsKey(c1) && mapST.get(c1) != c2) {
                System.out.println(false);
                return;
            }

            // Check mapping from t -> s
            // If c2 was already mapped to a different character,
            // then the mapping is not one-to-one
            if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) {
                System.out.println(false);
                return;
            }

            // Create/update the mappings
            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }

        // If no conflict was found, strings are isomorphic
        System.out.println(true);
    }
}