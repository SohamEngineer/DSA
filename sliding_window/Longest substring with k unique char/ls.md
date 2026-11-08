# Longest Substring with K Unique Characters

## Problem Statement

Given a string `s` and an integer `k`, find the length of the longest substring that contains exactly `k` distinct characters.

If no such substring exists, return `-1`.

### Example 1

**Input**
```text
s = "aabacbebebe"
k = 3
```

**Output**
```text
7
```

**Explanation**

The substring `"cbebebe"` has exactly 3 distinct characters: `c`, `b`, and `e`.

Its length is `7`, which is the maximum possible.

### Example 2

**Input**
```text
s = "aaaa"
k = 2
```

**Output**
```text
-1
```

**Explanation**

The string contains only one distinct character, so no substring can contain exactly 2 distinct characters.

---

## Approach: Sliding Window + HashMap

We use the **Sliding Window** technique to find the longest valid substring efficiently.

A `HashMap<Character, Integer>` stores each character and its frequency inside the current window.

### Variables

```java
int low = 0, high = 0;
int maxLen = -1;

Map<Character, Integer> map = new HashMap<>();
```

- `low`: Starting index of the current window.
- `high`: Ending index of the current window.
- `maxLen`: Stores the maximum valid substring length.
- `map`: Stores each character and its frequency in the window.

## Algorithm

### Step 1: Expand the window

Add the character at index `high` to the map.

```java
char ch = s.charAt(high);
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

If the character already exists, increase its frequency. Otherwise, insert it with frequency `1`.

### Step 2: Shrink the window when necessary

If the window contains more than `k` distinct characters, move `low` forward until the number of distinct characters becomes at most `k`.

```java
while (map.size() > k) {
    char left = s.charAt(low);

    map.put(left, map.get(left) - 1);

    if (map.get(left) == 0) {
        map.remove(left);
    }

    low++;
}
```

**Why remove a character from the map?**

When its frequency becomes zero, that character no longer exists in the current window. Removing it keeps `map.size()` accurate.

### Step 3: Update the maximum length

When the window contains exactly `k` distinct characters, calculate its length.

```java
if (map.size() == k) {
    maxLen = Math.max(maxLen, high - low + 1);
}
```

The window length is:

```text
high - low + 1
```

We use `Math.max()` to preserve the longest valid substring found so far.

### Step 4: Move the right pointer

```java
high++;
```

Repeat until `high` reaches the end of the string.

---

## Dry Run

Consider:

```text
s = "aabac"
k = 2
```

We need the longest substring containing exactly 2 distinct characters.

| Window | Distinct characters | Action |
|---|---:|---|
| `a` | 1 | Continue |
| `aa` | 1 | Continue |
| `aab` | 2 | Update `maxLen = 3` |
| `aaba` | 2 | Update `maxLen = 4` |
| `aabac` | 3 | Shrink window |

When `c` is added, the window has 3 distinct characters: `a`, `b`, and `c`.

The algorithm removes characters from the left until only 2 distinct characters remain.

The final valid window is `"ac"`, with length `2`.

The maximum length remains:

```text
maxLen = 4
```

The answer is `4`, corresponding to the substring `"aaba"`.

---

## Java Solution

```java
import java.util.*;

class Solution {
    public int longestKSubstr(String s, int k) {

        int low = 0, high = 0;
        int maxLen = -1;

        Map<Character, Integer> map = new HashMap<>();

        while (high < s.length()) {

            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > k) {

                char left = s.charAt(low);

                map.put(left, map.get(left) - 1);

                if (map.get(left) == 0) {
                    map.remove(left);
                }

                low++;
            }

            if (map.size() == k) {
                maxLen = Math.max(maxLen, high - low + 1);
            }

            high++;
        }

        return maxLen;
    }
}
```

---

## Complexity Analysis

### Time Complexity: `O(n)`

Each character enters the window once and leaves the window at most once.

Although there is a nested `while` loop, the two pointers only move forward, so the total time complexity is linear.

### Space Complexity: `O(min(n, k))`

The map stores at most `k` distinct characters in a valid window. During expansion, it may temporarily hold `k + 1` distinct characters before shrinking.

For a fixed character set, the auxiliary space is `O(1)`.

---

## Important DSA Pattern

**Sliding Window + Frequency Map**

Use this pattern when a problem asks for the longest or shortest substring satisfying a condition involving character frequencies or distinct characters.

Remember the logic:

```text
1. Expand the window using high.
2. Add the character to the frequency map.
3. While distinct characters > k:
       Remove characters from the left.
       Move low forward.
4. If distinct characters == k:
       Update the maximum length.
5. Move high forward.
```

**Key distinction:** The condition is *exactly* `k` distinct characters, not at most `k`. Therefore, update `maxLen` only when `map.size() == k`.
