# Two Sum II — Input Array Is Sorted

## Problem

Given a **1-indexed** array of integers `numbers` that is sorted in non-decreasing order, find two numbers such that they add up to a specific `target`.

Return the indices of the two numbers.

### Rules

* The array is sorted.
* Exactly one solution exists.
* The same element cannot be used twice.
* The returned indices must be **1-indexed**.

### Example

```text
Input:
numbers = [2, 7, 11, 15]
target = 9

Output:
[1, 2]
```

Because:

```text
2 + 7 = 9
```

---

## Approach — Two Pointers

Since the array is already **sorted**, we can solve this efficiently using two pointers.

We use:

```text
i → points to the first element
j → points to the last element
```

Initially:

```java
int i = 0;
int j = numbers.length - 1;
```

For:

```text
[2, 7, 11, 15]
 ↑           ↑
 i           j
```

---

## How It Works

Calculate:

```java
int sum = numbers[i] + numbers[j];
```

There are three possibilities.

### Case 1 — `sum == target`

We found the answer.

```java
if (sum == target) {
    return new int[]{i + 1, j + 1};
}
```

Notice `i + 1` and `j + 1`.

The problem uses **1-based indexing**, while Java arrays use **0-based indexing**.

For example:

```text
Java index:      0   1   2   3
1-based index:   1   2   3   4
```

Therefore:

```java
i + 1
j + 1
```

is required.

---

### Case 2 — `sum < target`

The current sum is too small.

Because the array is sorted, increasing the left pointer gives us a larger number.

```java
if (sum < target) {
    i++;
}
```

Example:

```text
[2, 7, 11, 15]
 ↑           ↑

2 + 15 = 17
```

If the target were `20`, the sum is too small, so move `i`:

```text
[2, 7, 11, 15]
    ↑        ↑
    i        j
```

---

### Case 3 — `sum > target`

The current sum is too large.

Because the array is sorted, decreasing the right pointer gives us a smaller number.

```java
if (sum > target) {
    j--;
}
```

Example:

```text
[2, 7, 11, 15]
 ↑           ↑

2 + 15 = 17
```

If the target were `9`, the sum is too large, so move `j`:

```text
[2, 7, 11, 15]
 ↑        ↑
 i        j
```

Now:

```text
2 + 11 = 13
```

Continue until the target is found.

---

## Dry Run

Consider:

```text
numbers = [2, 7, 11, 15]
target = 9
```

### Step 1

```text
i = 0
j = 3

2 + 15 = 17
```

`17 > 9`, so:

```text
j--
```

Now:

```text
i = 0
j = 2
```

---

### Step 2

```text
2 + 11 = 13
```

`13 > 9`, so:

```text
j--
```

Now:

```text
i = 0
j = 1
```

---

### Step 3

```text
2 + 7 = 9
```

We found the answer.

Java indices:

```text
i = 0
j = 1
```

But the problem requires 1-based indices:

```text
[1, 2]
```

---

## Java Solution

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {

            int sum = numbers[i] + numbers[j];

            if (sum == target) {
                return new int[]{i + 1, j + 1};
            }

            if (sum < target) {
                i++;
            }

            if (sum > target) {
                j--;
            }
        }

        return new int[]{-1, -1};
    }
}
```

---

## Why Two Pointers Work

The important thing is that the array is **sorted**.

```text
[2, 7, 11, 15]
```

If:

```text
numbers[i] + numbers[j] < target
```

we need a **larger sum**.

Moving `i` right gives us a larger value.

If:

```text
numbers[i] + numbers[j] > target
```

we need a **smaller sum**.

Moving `j` left gives us a smaller value.

Therefore, we never need to check every possible pair.

---

## Complexity

### Time Complexity

```text
O(n)
```

Each pointer moves only in one direction, so together they make at most `n` movements.

### Space Complexity

```text
O(1)
```

Only two pointers and one variable are used.

---

## Key DSA Pattern

This is a classic **Two Pointer** problem.

Remember this pattern for a sorted array:

```text
left  → starts from beginning
right → starts from end
```

Then:

```text
sum < target → left++
sum > target → right--
sum == target → answer
```

### Important

This solution works because the array is **sorted**.

For an unsorted array, this exact two-pointer approach does not work. In that case, a `HashMap` is usually the better approach for achieving `O(n)` time.
