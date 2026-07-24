// Bruth Force Solutions

class Solution {
    public int maxSubArray(int[] nums) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j= i; j<nums.length;j++){
                sum +=nums[j];
                max=Math.max(sum,max);
            }
        }
        return max;
    }
}

// Optimal Solutions (Kadane's Algorithm)
class Solution {
    public int maxSubArray(int[] nums) {
int max_sum=nums[0];
int current_sum=nums[0];
for(int i=1;i<nums.length;i++){
    current_sum =Math.max(nums[i],current_sum+nums[i]);
    max_sum=Math.max(max_sum,current_sum);
}
return max_sum;
    }
}
