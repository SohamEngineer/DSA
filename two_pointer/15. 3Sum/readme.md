# 3Sum

## Problem

Given an integer array `nums`, find all unique triplets:

```text
nums[i] + nums[j] + nums[k] = 0
```

The solution must not contain duplicate triplets.

### Example

```text
Input:
nums = [-1, 0, 1, 2, -1, -4]

Output:
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

The order of the triplets does not matter.

---

# Approach

The main idea is:

1. Sort the array.
2. Fix one element using `i`.
3. Use two pointers (`left` and `right`) to find the other two elements.
4. Skip duplicates.

This converts the problem from a brute-force `O(n³)` solution into an `O(n²)` solution.

---

# Step 1 — Sort the Array

```java
Arrays.sort(nums);
```

For example:

```text
[-1, 0, 1, 2, -1, -4]
```

becomes:

```text
[-4, -1, -1, 0, 1, 2]
```

Sorting is extremely important because it allows us to use the **Two Pointer** technique.

---

# Step 2 — Fix One Element

We use:

```java
for (int i = 0; i < n - 2; i++)
```

Here, `nums[i]` is the first number of the triplet.

For example:

```text
[-4, -1, -1, 0, 1, 2]
 ↑
 i
```

Once `nums[i]` is fixed, we only need to find:

```text
nums[left] + nums[right] = -nums[i]
```

---

# Step 3 — Initialize Two Pointers

For every `i`:

```java
int left = i + 1;
int right = n - 1;
```

For example:

```text
[-4, -1, -1, 0, 1, 2]
 ↑    ↑              ↑
 i  left           right
```

We then calculate:

```java
int sum = nums[i] + nums[left] + nums[right];
```

---

# Step 4 — Check the Sum

There are three possibilities.

## Case 1 — `sum == 0`

We found a valid triplet.

```java
if (sum == 0) {
    result.add(
        Arrays.asList(
            nums[i],
            nums[left],
            nums[right]
        )
    );
}
```

Then move both pointers:

```java
left++;
right--;
```

Why both?

Because we already found a valid combination, so we need to search for another combination.

---

## Case 2 — `sum < 0`

The sum is too small.

Because the array is sorted, we need a **larger value**.

Move:

```java
left++;
```

Example:

```text
[-4, -1, -1, 0, 1, 2]
 ↑    ↑           ↑
 i   left       right
```

If the sum is negative, moving `left` to the right gives us a larger number.

---

## Case 3 — `sum > 0`

The sum is too large.

We need a smaller number.

Move:

```java
right--;
```

Because the array is sorted, moving `right` left gives us a smaller value.

---

# Step 5 — Avoid Duplicate `i`

This is very important.

Suppose the sorted array contains:

```text
[-1, -1, 0, 1, 2]
```

If we use the first `-1` and then use the second `-1` as another starting point, we may generate the same triplets again.

Therefore:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

Meaning:

> If the current starting number is the same as the previous starting number, skip it.

---

# Step 6 — Avoid Duplicate `left`

After finding a valid triplet:

```java
left++;
right--;
```

We then skip duplicate values:

```java
while (left < right && nums[left] == nums[left - 1]) {
    left++;
}
```

For example:

```text
[-1, 0, 0, 0, 1]
     ↑
   left
```

If we already used `0`, using another `0` in the same position would create a duplicate triplet.

So we skip it.

---

# Step 7 — Avoid Duplicate `right`

Similarly:

```java
while (left < right && nums[right] == nums[right + 1]) {
    right--;
}
```

This prevents the same value on the right side from generating duplicate triplets.

---

# Dry Run

Consider:

```text
nums = [-1, 0, 1, 2, -1, -4]
```

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

### `i = 0`

```text
nums[i] = -4
```

Pointers:

```text
-4  -1  -1  0  1  2
 ↑                   ↑
 i                 right
    ↑
   left
```

Calculate:

```text
-4 + (-1) + 2 = -3
```

Sum is negative:

```text
sum < 0
```

So:

```text
left++
```

Eventually, there is no combination for `-4`.

---

### `i = 1`

```text
nums[i] = -1
```

Now:

```text
[-4, -1, -1, 0, 1, 2]
     ↑              ↑
     i            right
      ↑
     left
```

Try:

```text
-1 + (-1) + 2 = 0
```

We found:

```text
[-1, -1, 2]
```

Add it to the result.

Then:

```text
left++;
right--;
```

---

Continue searching.

Eventually:

```text
-1 + 0 + 1 = 0
```

We find:

```text
[-1, 0, 1]
```

Final result:

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

---

# Java Solution

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate starting values
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(
                        Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                        )
                    );

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        return result;
    }
}
```

---

# Why Sorting Is Necessary

Without sorting, we cannot reliably decide which pointer to move.

For a sorted array:

```text
sum < 0
```

means we need a bigger value, so:

```text
left++;
```

And:

```text
sum > 0
```

means we need a smaller value, so:

```text
right--;
```

That is the main reason the Two Pointer approach works.

---

# Brute Force vs Optimized

## Brute Force

We could use three loops:

```text
i
 └── j
      └── k
```

This checks every possible triplet.

Time complexity:

```text
O(n³)
```

This becomes too slow for large arrays.

## Optimized

Our approach:

```text
Sort
  ↓
Fix i
  ↓
Two pointers
```

Time complexity:

```text
O(n²)
```

This is significantly better.

---

# Complexity

### Sorting

```text
O(n log n)
```

### Two Pointer Search

For each `i`, the `left` and `right` pointers move across the array:

```text
O(n)
```

This happens for approximately `n` values of `i`:

```text
O(n²)
```

Therefore, the total complexity is:

```text
O(n log n) + O(n²)
```

which simplifies to:

```text
O(n²)
```

### Space Complexity

Ignoring the output:

```text
O(1)
```

The algorithm itself uses only a few variables.

The returned `result` naturally requires space for the triplets.

---

# Key DSA Pattern

This problem combines three important concepts:

```text
1. Sorting
2. Two Pointers
3. Duplicate Handling
```

The pattern to remember is:

```text
sort(nums)

for each i:

    left = i + 1
    right = n - 1

    while left < right:

        sum = nums[i] + nums[left] + nums[right]

        if sum == target:
            save answer
            left++
            right--

        else if sum < target:
            left++

        else:
            right--
```

For `3Sum`, the target is:

```text
0
```

The most important part is not memorizing the code. Understand **why sorting lets you decide whether to move `left` or `right`**.
