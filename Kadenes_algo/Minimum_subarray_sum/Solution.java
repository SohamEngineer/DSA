package Kadenes_algo.Minimum_subarray_sum;

public class Solution {
    public int minSubArraySum( int[] nums) {
      int bestending = 0;
int ans = Integer.MAX_VALUE;

for (int i = 0; i < n; i++) {
    int v1 = bestending + nums[i];
    int v2 = nums[i];

    bestending = Math.min(v1, v2);
    ans = Math.min(bestending, ans);
}  
    }
} {
    
}
