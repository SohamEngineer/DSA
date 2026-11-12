class Solution {
    public int maxProduct(int[] nums) {

        int bestproduct = nums[0];
        int minproduct = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int v1 = bestproduct * nums[i];
            int v2 = nums[i];
            int v3 = minproduct * nums[i];

            int newMax = Math.max(v1, Math.max(v2, v3));
            int newMin = Math.min(v1, Math.min(v2, v3));

            bestproduct = newMax;
            minproduct = newMin;

            ans = Math.max(bestproduct, ans);
        }

        return ans;
    }
}