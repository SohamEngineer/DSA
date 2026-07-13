# Sequential Digits

## Problem Statement

An integer has **sequential digits** if and only if each digit in the number is exactly **one greater** than the previous digit.

Given two integers `low` and `high`, return a **sorted list** of all the integers in the range `[low, high]` that have sequential digits.

---

## Example 1

### Input

```text
low = 100
high = 300
```

### Output

```text
[123, 234]
```

### Explanation

The sequential digit numbers between **100** and **300** are:

- 123
- 234

---

## Example 2

### Input

```text
low = 1000
high = 13000
```

### Output

```text
[1234, 2345, 3456, 4567, 5678, 6789, 12345]
```

---

## Approach

Instead of generating every number between `low` and `high`, we use the fixed string:

```text
123456789
```

Every sequential digit number is simply a **substring** of this string.

For every possible length between the number of digits in `low` and `high`:

1. Extract all substrings of that length.
2. Convert each substring into an integer.
3. If the number lies within the given range, add it to the answer.

Since there are only **36 possible sequential numbers**, this approach is extremely efficient.

---

## Algorithm

1. Create a string:

```text
digits = "123456789"
```

2. Find the number of digits in `low` and `high`.

3. For every possible length:

   - Generate every substring of that length.
   - Convert it to an integer.
   - Check if it lies between `low` and `high`.
   - If yes, add it to the result list.

4. Return the result.

---

## Dry Run

### Input

```text
low = 100
high = 500
```

### Step 1

```text
digits = "123456789"
```

Length of `low` = 3

Length of `high` = 3

Only consider substrings of length **3**.

---

### Step 2

| Substring | Number | In Range? |
|-----------|--------|-----------|
| 123 | 123 | ✅ |
| 234 | 234 | ✅ |
| 345 | 345 | ✅ |
| 456 | 456 | ✅ |
| 567 | 567 | ❌ |
| 678 | 678 | ❌ |
| 789 | 789 | ❌ |

---

### Final Answer

```text
[123, 234, 345, 456]
```

---

## Java Solution

```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        String digits = "123456789";
        List<Integer> ans = new ArrayList<>();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();

        for (int len = lowLen; len <= highLen; len++) {

            for (int i = 0; i + len <= 9; i++) {

                int num = Integer.parseInt(digits.substring(i, i + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}
```

---

## Time Complexity

Let **D** be the maximum number of digits (at most **9**).

- Outer loop runs for at most **9** lengths.
- Inner loop runs at most **9** times.

Overall:

```text
Time Complexity: O(1)
```

Since the maximum number of generated sequential numbers is fixed (**36**).

---

## Space Complexity

```text
Space Complexity: O(1)
```

Ignoring the output list, only a few variables are used.

---

## Why This Approach Works

Every sequential digit number is a contiguous substring of:

```text
123456789
```

Examples:

```text
12
123
2345
45678
6789
123456789
```

Therefore, generating substrings guarantees that:

- Digits are consecutive.
- Numbers are already generated in sorted order.
- No extra validation is needed.

---

## Key Concepts

- String Manipulation
- Sliding Window (Substring Generation)
- Integer Parsing
- Simulation

---

## Tags

`String` `Simulation` `Sliding Window` `LeetCode 1291`