Minimum Size Subarray Sum — LeetCode 209
A Java solution to LeetCode 209: Minimum Size Subarray Sum, using the sliding window (two-pointer) technique.
Problem
Given an array of positive integers nums and a positive integer target, return the minimum length of a contiguous subarray whose sum is greater than or equal to target. Return 0 if no such subarray exists.
Solution
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low = 0, high = 0, sum = 0;
        int res = Integer.MAX_VALUE;
        int n = nums.length;

        while (high < n) {
            sum += nums[high];

            while (sum >= target) {
                int len = high - low + 1;
                res = Math.min(res, len);

                sum -= nums[low];
                low++;
            }

            high++;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
Example
Input
target = 7
nums = [2, 3, 1, 2, 4, 3]
Output
2
Explanation: The shortest valid subarray is [4, 3]. Its sum is 7, and its length is 2.
How It Works
1. Move high to expand the window and add each new value to sum.
2. While sum >= target, record the current window length.
3. Shrink the window from the left by subtracting nums[low] and incrementing low.
4. Return the smallest recorded length, or 0 if no valid window was found.
Complexity
- Time: O(n) — each pointer advances through the array at most once.
- Space: O(1) — only a fixed number of variables are used.
Important Note
This sliding-window solution works because the array contains positive integers. It is not generally valid for arrays containing negative numbers.