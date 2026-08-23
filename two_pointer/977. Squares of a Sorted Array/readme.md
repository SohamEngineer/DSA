# Squares of a Sorted Array

## Problem

Given an integer array `nums` sorted in **non-decreasing order**, return an array containing the squares of each number, also sorted in non-decreasing order.

### Example

```text
Input:
nums = [-4, -1, 0, 3, 10]

Output:
[0, 1, 9, 16, 100]
```

Because:

```text
(-4)² = 16
(-1)² = 1
0² = 0
3² = 9
10² = 100
```

After sorting:

```text
[0, 1, 9, 16, 100]
```

---

# Brute Force Approach

The simplest solution is:

1. Square every element.
2. Sort the resulting array.

```java
class Solution {
    public int[] sortedSquares(int[] nums) {

        int[] squ = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squ[i] = nums[i] * nums[i];
        }

        Arrays.sort(squ);

        return squ;
    }
}
```

### Complexity

```text
Time:  O(n log n)
Space: O(n)
```

The `Arrays.sort()` operation takes `O(n log n)` time.

---

# Optimized Approach

The input array is already sorted, so we should take advantage of that instead of sorting again.

The important observation is:

```text
Negative numbers → become positive after squaring
Positive numbers → become positive after squaring
```

For example:

```text
[-7, -3, -1, 2, 5]

Squares:

[49, 9, 1, 4, 25]
```

The negative numbers produce squares in **decreasing order**:

```text
49, 9, 1
```

The positive numbers produce squares in **increasing order**:

```text
4, 25
```

So we can:

1. Separate positive and negative numbers.
2. Square both groups.
3. Reverse the negative-square array.
4. Merge the two sorted arrays.

---

# Step 1 — Separate Positive and Negative Numbers

We use two arrays:

```java
int[] squ = new int[nums.length];
int[] squ2 = new int[nums.length];
```

`squ` stores non-negative numbers.

`squ2` stores negative numbers.

We use:

```java
int p = 0;
int q = 0;
```

to track their positions.

```java
for (int i = 0; i < nums.length; i++) {

    if (nums[i] >= 0) {
        squ[p++] = nums[i];
    } else {
        squ2[q++] = nums[i];
    }
}
```

Example:

```text
nums = [-4, -1, 0, 3, 10]
```

After separation:

```text
squ  = [0, 3, 10]
squ2 = [-4, -1]
```

---

# Step 2 — Square Both Arrays

Positive numbers:

```text
[0, 3, 10]
```

become:

```text
[0, 9, 100]
```

Negative numbers:

```text
[-4, -1]
```

become:

```text
[16, 1]
```

Notice that the negative squares are backwards:

```text
[16, 1]
```

---

# Step 3 — Reverse Negative Squares

We reverse `squ2`:

```text
[16, 1]
```

becomes:

```text
[1, 16]
```

Now both arrays are sorted:

```text
squ  = [0, 9, 100]
squ2 = [1, 16]
```

---

# Step 4 — Merge the Two Sorted Arrays

Now this becomes the same idea as the **Merge** step of Merge Sort.

We use:

```java
int i = 0;
int j = 0;
int index = 0;
```

Compare:

```java
if (squ[i] <= squ2[j])
```

If the positive square is smaller, put it into `result`.

Otherwise, put the negative square into `result`.

Example:

```text
squ  = [0, 9, 100]
squ2 = [1, 16]
```

Compare:

```text
0 < 1 → take 0
9 > 1 → take 1
9 < 16 → take 9
100 > 16 → take 16
```

Finally:

```text
[0, 1, 9, 16, 100]
```

---

# Java Solution

```java
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
```

---

# Dry Run

Consider:

```text
nums = [-7, -3, -1, 2, 5]
```

### Separate

```text
Positive:
[2, 5]

Negative:
[-7, -3, -1]
```

### Square

```text
Positive:
[4, 25]

Negative:
[49, 9, 1]
```

### Reverse Negative Squares

```text
[1, 9, 49]
```

Now:

```text
[4, 25]
[1, 9, 49]
```

### Merge

```text
1
4
9
25
49
```

Result:

```text
[1, 4, 9, 25, 49]
```

---

# Complexity

There are several linear passes through the array:

```text
Separate numbers → O(n)
Square numbers   → O(n)
Reverse          → O(n)
Merge             → O(n)
```

Therefore:

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

We use additional arrays for storing the positive values, negative values, and final result.

---

# Important DSA Concepts

This solution demonstrates several useful patterns:

### 1. Use the Sorted Property

Do not automatically sort an array just because the final answer needs to be sorted.

Ask:

> "Can I use the fact that the input is already sorted?"

Here, the answer is yes.

### 2. Negative Numbers

When squaring:

```text
-10² = 100
-5²  = 25
-2²  = 4
```

So the order becomes reversed.

```text
[-10, -5, -2]
```

becomes:

```text
[100, 25, 4]
```

### 3. Merge Two Sorted Arrays

After reversing the negative squares, we have two sorted arrays:

```text
positive squares → increasing
negative squares → increasing
```

We can merge them using two pointers.

---

# Better Alternative

Although the above solution is `O(n)`, it uses multiple arrays.

There is an even cleaner **two-pointer solution** that uses the original array and fills the result from the end.

The key idea is:

```text
Compare the absolute values at the left and right ends.

The larger absolute value produces the largest square.
```

For example:

```text
[-7, -3, -1, 2, 5]
 ↑              ↑

|-7| = 7
|5|  = 5
```

So:

```text
7² = 49
```

is the largest square and should be placed at the end of the result.

This gives an `O(n)` time solution with only **O(n)** space for the result array and avoids the extra separation/reversal arrays.
