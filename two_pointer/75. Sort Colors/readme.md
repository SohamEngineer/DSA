# Sort Colors — Dutch National Flag Algorithm

## 📌 Problem

Given an integer array `nums` containing only `0`, `1`, and `2`, sort the array **in-place** so that all `0`s come first, followed by `1`s, and then `2`s.

### Example

```text
Input:
[2, 0, 2, 1, 1, 0]

Output:
[0, 0, 1, 1, 2, 2]
```

---

## 💡 Algorithm

We use the **Dutch National Flag Algorithm**.

The algorithm uses **three pointers**:

* `low` → position where the next `0` should be placed
* `mid` → current element being examined
* `high` → position where the next `2` should be placed

### Pointer Structure

```text
0s        1s        Unknown        2s
|         |             |            |
0       low-1        mid ... high   high+1
```

Initially:

```text
low = 0
mid = 0
high = nums.length - 1
```

---

## 🔄 Algorithm Steps

While `mid <= high`:

### Case 1: `nums[mid] == 0`

The current element is `0`.

Swap it with the element at `low`.

```text
swap(nums[low], nums[mid])

low++
mid++
```

Why?

Because the `0` is now correctly placed in the `0s` section.

---

### Case 2: `nums[mid] == 1`

The current element is already in the correct relative section.

Simply move `mid`.

```text
mid++
```

---

### Case 3: `nums[mid] == 2`

The current element belongs at the end.

Swap it with the element at `high`.

```text
swap(nums[mid], nums[high])

high--
```

⚠️ **Do NOT increment `mid`.**

The element received from `high` has not been checked yet.

---

## 💻 Java Implementation

```java
class Solution {
    public void sortColors(int[] nums) {

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;
            }

            else if (nums[mid] == 1) {
                mid++;
            }

            else { // nums[mid] == 2

                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;

                // Don't increment mid
            }
        }
    }
}
```

---

## 🧠 Dry Run

Consider:

```text
nums = [2, 0, 2, 1, 1, 0]
```

Initially:

```text
low = 0
mid = 0
high = 5
```

### Step 1

`nums[mid] = 2`

Swap `mid` and `high`.

```text
[0, 0, 2, 1, 1, 2]
 ↑                 ↑
mid              high
```

`high--`

```text
high = 4
```

We don't increment `mid` because the new value at `mid` must be checked.

---

### Step 2

`nums[mid] = 0`

Swap `low` and `mid`.

```text
[0, 0, 2, 1, 1, 2]
 ↑  ↑
low mid
```

Then:

```text
low++
mid++
```

---

### Step 3

`nums[mid] = 2`

Swap with `high`.

```text
[0, 0, 1, 1, 2, 2]
       ↑        ↑
      mid      high
```

Then:

```text
high--
```

---

### Step 4

`nums[mid] = 1`

Simply:

```text
mid++
```

Continue until:

```text
mid > high
```

Final result:

```text
[0, 0, 1, 1, 2, 2]
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Every element is processed at most a constant number of times.

### Space Complexity

```text
O(1)
```

No additional array is required because sorting happens **in-place**.

---

## 🔑 Key Idea

Remember this simple rule:

```text
0 → move to LOW
1 → move MID
2 → move to HIGH
```

Or:

```text
0 → low++, mid++
1 → mid++
2 → high--
```

The important point is:

> When `nums[mid] == 2`, decrease `high` but do not increase `mid`, because the swapped element at `mid` has not been processed yet.

---

## 📚 Algorithm Pattern

This problem is an example of the:

### Dutch National Flag Algorithm

It is useful when an array contains **three categories of elements** and we want to partition them efficiently.

```text
[ 0s | 1s | unknown | 2s ]
       ↑       ↑       ↑
      low     mid     high
```

The algorithm sorts the array in:

```text
O(n) time
O(1) space
```

without using an additional sorting algorithm.
