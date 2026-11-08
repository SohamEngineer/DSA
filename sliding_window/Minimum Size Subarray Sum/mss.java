class Solution {
    public int minSubArrayLen(int target, int[] nums) {
         int low=0 ,high =0 ,sum =0, res = Integer.MAX_VALUE;;
         int n= nums.length;
         while(high < n){
            sum =sum +nums[high];
            while (sum >=target){
                int len =high-low+1;
                res=Math.min(res,len);
                sum=sum - nums[low];
                low++;
            }
            high ++;
         }
return res == Integer.MAX_VALUE ? 0 : res;
    }
}