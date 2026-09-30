class Solution {
    public int maxSubarraySum(int[] arr, int k) {

        int low = 0;
        int high = k - 1;
        int sum = 0;
        int result = Integer.MIN_VALUE;
        int n = arr.length;

        // Initial window
        for (int i = low; i <= high; i++) {
            sum += arr[i];
        }

        while (high < n) {

            result = Math.max(result, sum);

            low++;
            high++;

            if (high == n) {
                break;
            }

            sum = sum - arr[low - 1];
            sum = sum + arr[high];
        }

        return result;
    }
}