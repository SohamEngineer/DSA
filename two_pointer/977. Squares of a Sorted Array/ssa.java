//Bruth Force Solution
// class Solution {
//     public int[] sortedSquares(int[] nums) {
//         int [] squ=new int [nums.length];
        
//         for (int i=0;i<nums.length ;i++){
//             squ[i] = nums[i] * nums[i];
//         }
//          Arrays.sort(squ);
//         return squ;
//     }
// }



class Solution {
    public int[] sortedSquares(int[] nums) {

        int[] squ = new int[nums.length];
        int[] squ2 = new int[nums.length];

        int p = 0;
        int q = 0;

        // Separate positive and negative numbers
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= 0) {
                squ[p++] = nums[i];
            } else {
                squ2[q++] = nums[i];
            }
        }

        // Square positive numbers
        for (int i = 0; i < p; i++) {
            squ[i] = squ[i] * squ[i];
        }

        // Square negative numbers
        for (int i = 0; i < q; i++) {
            squ2[i] = squ2[i] * squ2[i];
        }

        // Reverse negative squares
        int left = 0;
        int right = q - 1;

        while (left < right) {
            int temp = squ2[left];
            squ2[left] = squ2[right];
            squ2[right] = temp;

            left++;
            right--;
        }

        // Merge two sorted arrays
        int i = 0;
        int j = 0;
        int index = 0;

        int[] result = new int[p + q];

        while (i < p && j < q) {

            if (squ[i] <= squ2[j]) {
                result[index++] = squ[i++];
            } else {
                result[index++] = squ2[j++];
            }
        }

        // Remaining positive squares
        while (i < p) {
            result[index++] = squ[i++];
        }

        // Remaining negative squares
        while (j < q) {
            result[index++] = squ2[j++];
        }

        return result;
    }
}