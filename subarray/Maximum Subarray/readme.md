# Maximum Subarray (LeetCode 53)

## Problem Statement

Given an integer array `nums`, find the contiguous subarray with the largest sum and return its sum.

### Example

**Input**
```text
nums = [-2,1,-3,4,-1,2,1,-5,4]
```

**Output**
```text
6
```

**Explanation**

The subarray `[4, -1, 2, 1]` has the largest sum, which is `6`.

---

# Approach 1: Brute Force

## Idea

Generate every possible contiguous subarray.

- Start from every index.
- Keep adding elements one by one.
- Update the maximum sum whenever a larger sum is found.

## Algorithm

1. Initialize `max` with `Integer.MIN_VALUE`.
2. Iterate through every starting index.
3. Maintain a running sum.
4. Extend the subarray one element at a time.
5. Update the maximum sum.

## Java Code

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}
```

### Time Complexity

```
O(n²)
```

### Space Complexity

```
O(1)
```

---

# Approach 2: Optimal (Kadane's Algorithm)

## Idea

At every index, decide:

- Start a new subarray from the current element.
- Extend the previous subarray.

Take whichever gives the larger sum.

Formula:

```text
currentSum = max(nums[i], currentSum + nums[i])
```

Keep track of the maximum value obtained during traversal.

## Algorithm

1. Initialize:
   - `currentSum = nums[0]`
   - `maxSum = nums[0]`
2. Traverse the array from index `1`.
3. Update `currentSum`.
4. Update `maxSum`.
5. Return `maxSum`.

## Java Code

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(1)
```

---

# Dry Run

Consider:

```text
nums = [-2,1,-3,4,-1,2,1,-5,4]
```

| Index | nums[i] | currentSum | maxSum |
|------:|---------:|-----------:|--------:|
| 0 | -2 | -2 | -2 |
| 1 | 1 | 1 | 1 |
| 2 | -3 | -2 | 1 |
| 3 | 4 | 4 | 4 |
| 4 | -1 | 3 | 4 |
| 5 | 2 | 5 | 5 |
| 6 | 1 | 6 | 6 |
| 7 | -5 | 1 | 6 |
| 8 | 4 | 5 | 6 |

**Answer:** `6`

---

# Complexity Comparison

| Approach | Time Complexity | Space Complexity |
|----------|-----------------|------------------|
| Brute Force | O(n²) | O(1) |
| Kadane's Algorithm | O(n) | O(1) |

---

# Key Learning

- Brute Force checks every possible contiguous subarray.
- Kadane's Algorithm uses Dynamic Programming intuition by keeping only the best subarray ending at the current index.
- Kadane's Algorithm is the optimal solution with **O(n)** time and **O(1)** extra space.

---

## Topics

- Arrays
- Dynamic Programming
- Kadane's Algorithm
- Greedy

---

**LeetCode:** 53. Maximum Subarray