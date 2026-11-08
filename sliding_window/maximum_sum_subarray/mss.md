# Maximum Subarray Sum of Fixed Size K

## Problem

Given an integer array `arr` and an integer `k`, find the **maximum sum of any contiguous subarray of size `k`**.

### Example

```text
Input:
arr = [2, 5, 1, 8, 2, 9, 1]
k = 3

Output:
19
```

### Explanation

All contiguous subarrays of size `3` are:

```text
[2, 5, 1] → 8
[5, 1, 8] → 14
[1, 8, 2] → 11
[8, 2, 9] → 19
[2, 9, 1] → 12
```

Therefore:

```text
Maximum Sum = 19
```

---

## Approach

We use the **Sliding Window** technique.

Instead of calculating the sum of every subarray from scratch, we:

1. Calculate the sum of the first `k` elements.
2. Store this as the initial maximum.
3. Move the window one position at a time.
4. Remove the element that leaves the window.
5. Add the new element entering the window.
6. Update the maximum sum.

### Example

```text
Array:
[2, 5, 1, 8, 2, 9, 1]

k = 3
```

Initial window:

```text
[2, 5, 1]
sum = 8
```

Move the window:

```text
[5, 1, 8]

New sum:
8 - 2 + 8 = 14
```

Move again:

```text
[1, 8, 2]

New sum:
14 - 5 + 2 = 11
```

Move again:

```text
[8, 2, 9]

New sum:
11 - 1 + 9 = 19
```

Maximum:

```text
19
```

---

## Java Implementation

```java
class Solution {
    public int maxSubarraySum(int[] arr, int k) {

        int n = arr.length;
        int sum = 0;

        // Calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int result = sum;

        // Slide the window
        for (int i = k; i < n; i++) {

            // Remove the element leaving the window
            sum -= arr[i - k];

            // Add the new element
            sum += arr[i];

            // Update maximum
            result = Math.max(result, sum);
        }

        return result;
    }
}
```

---

## Why Sliding Window?

### Brute Force

We could calculate the sum of every subarray of size `k`.

For example:

```text
[2, 5, 1]
[5, 1, 8]
[1, 8, 2]
...
```

Each window requires `k` operations.

### Time Complexity

```text
O(n × k)
```

This can become slow when `n` and `k` are large.

### Sliding Window

With the sliding window, we only perform two operations when moving the window:

```text
Remove old element
Add new element
```

Therefore:

```text
Time Complexity: O(n)
Space Complexity: O(1)
```

---

## Important Edge Case: Negative Numbers

Do **not** initialize the result to `0`.

Incorrect:

```java
int result = 0;
```

Consider:

```text
arr = [-5, -2, -8]
k = 2
```

Subarrays:

```text
[-5, -2] → -7
[-2, -8] → -10
```

Correct answer:

```text
-7
```

If we initialize:

```java
int result = 0;
```

the program would incorrectly return `0`.

Instead, initialize it using the first window:

```java
int result = sum;
```

---

## Alternative Implementation Using Low and High

The same sliding-window concept can also be implemented using two pointers:

```java
class Solution {
    public int maxSubarraySum(int[] arr, int k) {

        int low = 0;
        int high = k - 1;
        int sum = 0;
        int n = arr.length;

        // Calculate first window
        for (int i = low; i <= high; i++) {
            sum += arr[i];
        }

        int result = sum;

        while (high < n - 1) {

            // Move window
            low++;
            high++;

            // Remove previous first element
            sum -= arr[low - 1];

            // Add new element
            sum += arr[high];

            result = Math.max(result, sum);
        }

        return result;
    }
}
```

---

## Key Concept

The most important formula for a fixed-size sliding window is:

```text
newWindowSum = oldWindowSum
               - elementLeavingWindow
               + elementEnteringWindow
```

In code:

```java
sum -= arr[i - k];
sum += arr[i];
```

This avoids repeatedly calculating the entire window sum.

---

## Complexity

| Approach       |     Time |    Space |
| -------------- | -------: | -------: |
| Brute Force    | O(n × k) |     O(1) |
| Sliding Window | **O(n)** | **O(1)** |

---

## Pattern to Remember

Whenever a problem asks:

> Find something for every contiguous subarray/window of a fixed size `k`

Think about:

```text
Sliding Window
```

Typical problems:

* Maximum sum of subarray of size `k`
* Minimum sum of subarray of size `k`
* Average of every subarray of size `k`
* Maximum/minimum element in every window
* Count elements satisfying a condition in each window

---

## Tags

```text
#Java
#DSA
#SlidingWindow
#Array
#Subarray
#TwoPointers
#InterviewPreparation
```
