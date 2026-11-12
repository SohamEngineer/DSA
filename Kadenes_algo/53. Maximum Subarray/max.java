class Solution {
    public int maxSubArray(int[] nums) {
        int bestsum=0;
        int ans=Integer.MIN_VALUE;
        for(int i=0 ;i<nums.length ;i++){
            int v1=bestsum+nums[i];
            int v2=nums[i];
            bestsum=Math.max(v1,v2);
            ans=Math.max(bestsum ,ans);
        }
        return ans;
    }
}