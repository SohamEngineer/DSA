# 3Sum Closest

## Problem

Given an integer array `nums` and an integer `target`, find three integers in `nums` such that their sum is **closest to `target`**.

Return the sum of the three integers.

### Example

```text
Input:
nums = [-1, 2, 1, -4]
target = 1

Output:
2
```

Explanation:

```text
(-1) + 2 + 1 = 2
```

The possible sums include:

```text
-1 + 2 + 1 = 2
-1 + 2 - 4 = -3
-1 + 1 - 4 = -4
2 + 1 - 4 = -1
```

`2` is closest to the target `1`.

---

# Approach

The solution uses:

1. **Sorting**
2. **One fixed pointer**
3. **Two pointers**
4. **Difference comparison**

The basic idea is very similar to the `3Sum` problem.

Instead of looking for:

```text
sum == 0
```

we are looking for:

```text
sum closest to target
```

---

# Step 1 — Sort the Array

```java
Arrays.sort(nums);
```

For example:

```text
[-1, 2, 1, -4]
```

becomes:

```text
[-4, -1, 1, 2]
```

Sorting is important because it allows us to intelligently move the `left` and `right` pointers.

---

# Step 2 — Initialize `closest`

```java
int closest = nums[0] + nums[1] + nums[2];
```

We need an initial value.

For example:

```text
nums = [-4, -1, 1, 2]
```

Initially:

```text
closest = -4 + (-1) + 1
        = -4
```

We will replace `closest` whenever we find a sum that is closer to the target.

---

# Step 3 — Fix One Element

We use:

```java
for (int i = 0; i < n - 2; i++)
```

Here:

```text
nums[i]
```

is the first number of the three.

For example:

```text
[-4, -1, 1, 2]
 ↑
 i
```

Then we search for the other two numbers using `left` and `right`.

---

# Step 4 — Initialize Two Pointers

```java
int left = i + 1;
int right = n - 1;
```

For example:

```text
[-4, -1, 1, 2]
 ↑    ↑        ↑
 i   left    right
```

Then calculate:

```java
int sum = nums[i] + nums[left] + nums[right];
```

---

# Step 5 — Compare With Target

Suppose:

```text
target = 1
sum = 5
```

The difference is:

```text
|5 - 1| = 4
```

We want the smallest difference.

Therefore:

```java
if (Math.abs(sum - target) < Math.abs(closest - target)) {
    closest = sum;
}
```

The important idea is:

```text
absolute difference = |sum - target|
```

The smaller the difference, the closer the sum is to the target.

---

# Step 6 — Move the Pointers

After checking the current sum, we decide which pointer to move.

## If `sum < target`

The sum is too small.

Because the array is sorted, we need a larger number.

So:

```java
left++;
```

Example:

```text
[-4, -1, 1, 2]
 ↑    ↑     ↑
 i   left right
```

If:

```text
sum < target
```

move `left` right.

This increases the sum.

---

## If `sum > target`

The sum is too large.

We need a smaller number.

So:

```java
right--;
```

Because the array is sorted, moving `right` left gives us a smaller value.

---

# Step 7 — Exact Match

If:

```java
if (sum == target)
```

then we already have the best possible answer.

The difference is:

```text
|sum - target| = 0
```

Nothing can be closer than `0`.

Therefore:

```java
return sum;
```

immediately.

---

# Dry Run

Consider:

```text
nums = [-1, 2, 1, -4]
target = 1
```

After sorting:

```text
[-4, -1, 1, 2]
```

Initial:

```text
closest = -4 + (-1) + 1
        = -4
```

---

## `i = 0`

```text
nums[i] = -4
left = 1
right = 3
```

So:

```text
-4 + (-1) + 2 = -3
```

Difference:

```text
|-3 - 1| = 4
```

Current closest:

```text
-4
```

Difference:

```text
|-4 - 1| = 5
```

Since `4 < 5`:

```text
closest = -3
```

Since:

```text
-3 < 1
```

move:

```text
left++
```

---

## Next Combination

Now:

```text
-4 + 1 + 2 = -1
```

Difference:

```text
|-1 - 1| = 2
```

This is closer than `-3`.

So:

```text
closest = -1
```

Again:

```text
-1 < 1
```

Move:

```text
left++
```

---

## `i = 1`

Now:

```text
nums[i] = -1
```

Try:

```text
-1 + 1 + 2 = 2
```

Difference:

```text
|2 - 1| = 1
```

So:

```text
closest = 2
```

There is no better combination in this example.

Return:

```text
2
```

---

# Java Solution

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int n = nums.length;

        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate starting values
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // Exact match
                if (sum == target) {
                    return sum;
                }

                // Update closest sum
                if (Math.abs(sum - target) <
                    Math.abs(closest - target)) {

                    closest = sum;
                }

                // Move pointers
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closest;
    }
}
```

---

# Why Do We Move `left` or `right`?

This is the most important part of the problem.

Because the array is sorted:

```text
[-4, -1, 1, 2, 5]
```

Suppose:

```text
sum < target
```

The sum is too small.

Moving `right` left would make the sum even smaller, which is useless.

So we move:

```text
left++
```

to increase the sum.

Similarly:

```text
sum > target
```

means the sum is too large.

Moving `left` right would make the sum even larger.

So we move:

```text
right--
```

to decrease the sum.

Therefore:

```text
sum < target → left++
sum > target → right--
sum == target → return immediately
```

---

# Difference Between 3Sum and 3Sum Closest

## 3Sum

The goal is to find combinations where:

```text
sum == 0
```

Example:

```text
[-1, 0, 1]
```

because:

```text
-1 + 0 + 1 = 0
```

## 3Sum Closest

The goal is different:

```text
|sum - target|
```

should be as small as possible.

For example:

```text
target = 10

possible sums:

8  → difference 2
9  → difference 1
12 → difference 2
```

Therefore:

```text
9
```

is the closest.

---

# Duplicate Handling

Your code contains:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

This skips duplicate values for the fixed `i`.

For example:

```text
[-2, -2, 0, 2, 4]
```

If the first `-2` has already been processed, processing the second `-2` as another starting point is unnecessary.

### Important Difference From 3Sum

In `3Sum`, duplicate handling is essential because you are returning **all unique triplets**.

In `3Sum Closest`, duplicate skipping for `i` is **not required for correctness**. It can reduce unnecessary work, but the answer does not contain a list of triplets.

So this:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

is an optimization rather than a fundamental requirement.

---

# Complexity

### Sorting

```text
O(n log n)
```

### Two Pointer Search

For each `i`, `left` and `right` move through the array:

```text
O(n)
```

For approximately `n` values of `i`:

```text
O(n²)
```

Therefore:

```text
Time Complexity = O(n²)
```

### Space Complexity

Apart from the sorting implementation's internal stack/implementation details:

```text
O(1)
```

No additional data structure proportional to `n` is created by the algorithm.

---

# Key DSA Pattern

The pattern to remember is:

```text
Sort
  ↓
Fix i
  ↓
left = i + 1
right = n - 1
  ↓
Calculate sum
  ↓
Compare |sum - target|
  ↓
sum < target → left++
sum > target → right--
sum == target → return
```

The core idea is simple:

> **Use sorting to turn the problem into a controlled search instead of checking every possible triplet.**
