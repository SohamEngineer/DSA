class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String digit = "123456789";
        List<Integer> ans = new ArrayList<>();
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int len = lowLen; len <= highLen; len++) {

            for (int i = 0; i + len <= 9; i++) {
                int num = Integer.parseInt(digit.substring(i, i + len));
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }

        }
            return ans;
    }
}