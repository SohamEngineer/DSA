# Remove Duplicates from Sorted Array

## Problem

Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates **in-place** such that each unique element appears only once.

Return the number of unique elements `k`.

The first `k` positions of `nums` should contain the unique elements.

### Example

```text
Input:
nums = [1, 1, 2]

Output:
2

Modified array:
[1, 2, _]
```

Another example:

```text
Input:
nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]

Output:
5

Modified array:
[0, 1, 2, 3, 4, _, _, _, _, _]
```

---

## Approach — Two Pointers

Because the array is **sorted**, duplicate values are next to each other.

We use two pointers:

```text
i → position of the last unique element
j → scans the array
```

Initially:

```java
int i = 0;
```

Then `j` moves from left to right.

### Step 1 — Compare `nums[i]` and `nums[j]`

```java
if (nums[i] != nums[j])
```

If they are different, we found a new unique element.

### Step 2 — Move `i`

```java
i++;
```

Now `i` represents the position where the new unique element should be stored.

### Step 3 — Copy the element

```java
nums[i] = nums[j];
```

This places the new unique value in the correct position.

### Step 4 — Return the count

Since `i` is an index:

```java
return i + 1;
```

---

## Dry Run

Consider:

```text
nums = [1, 1, 2, 2, 3]
```

Initially:

```text
i = 0
j = 0

[1, 1, 2, 2, 3]
 ↑
 i
```

### `j = 0`

```text
nums[i] == nums[j]

1 == 1
```

Nothing happens.

---

### `j = 1`

```text
nums[i] == nums[j]

1 == 1
```

Still a duplicate.

---

### `j = 2`

```text
nums[i] != nums[j]

1 != 2
```

Move `i`:

```text
i++
```

Now:

```text
i = 1
```

Copy:

```java
nums[i] = nums[j];
```

Array becomes:

```text
[1, 2, 2, 2, 3]
    ↑
    i
```

---

### `j = 3`

```text
nums[i] == nums[j]

2 == 2
```

Duplicate, so do nothing.

---

### `j = 4`

```text
nums[i] != nums[j]

2 != 3
```

Move `i`:

```text
i = 2
```

Copy `3`:

```text
[1, 2, 3, 2, 3]
       ↑
       i
```

Finally:

```text
i = 2
```

Therefore:

```java
return i + 1;
```

Result:

```text
3
```

The first 3 elements are:

```text
[1, 2, 3]
```

---

## Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int i = 0;

        for (int j = 0; j < nums.length; j++) {

            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
```

---

## Why Does This Work?

Because the array is sorted.

For example:

```text
[1, 1, 1, 2, 2, 3, 3]
```

All `1`s are together, all `2`s are together, etc.

Therefore, whenever:

```java
nums[i] != nums[j]
```

we know that `nums[j]` is a **new unique value**.

The `i` pointer keeps track of where the next unique value should go.

---

## Complexity

### Time Complexity

```text
O(n)
```

The array is traversed once.

### Space Complexity

```text
O(1)
```

No extra array, `HashSet`, or other data structure is used.

---

## Key DSA Pattern

This is a classic **Two Pointer** problem.

Remember:

```text
i → stores/points to the last unique element
j → searches for the next unique element
```

The general pattern is:

```java
for (int j = 0; j < nums.length; j++) {

    if (condition) {
        i++;
        nums[i] = nums[j];
    }
}
```

This technique is useful when you need to process a **sorted array in-place** without using extra memory.
