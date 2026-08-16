# Stone Game IX

## Problem

You are given an integer array `stones`.

Alice and Bob take turns removing one stone at a time. The score is the sum of the values of the removed stones.

* Alice starts first.
* If the current sum becomes divisible by `3`, the player who made that move loses.
* If all stones are removed without anyone losing, the game is a draw.
* Both players play optimally.
* Return `true` if Alice can win; otherwise return `false`.

### Example

```text
Input:
stones = [2, 1]

Output:
true
```

Alice can remove `1`, making the sum `1`.
Bob must remove `2`, making the sum `3`, so Bob loses.

---

## Key Observation

We only care about the **remainder when a stone is divided by 3**.

Every number belongs to one of three groups:

```text
remainder 0 → cnt0
remainder 1 → cnt1
remainder 2 → cnt2
```

For example:

```text
stones = [2, 4, 5, 6, 8]

2 % 3 = 2
4 % 3 = 1
5 % 3 = 2
6 % 3 = 0
8 % 3 = 2

cnt0 = 1
cnt1 = 1
cnt2 = 3
```

The actual values do not matter. Only their remainders matter.

---

## Why Do We Count Remainders?

The running sum can have only three possible remainders:

```text
0, 1, 2
```

Suppose the current sum has remainder `1`.

To make the sum divisible by `3`, we need a stone with remainder `2`:

```text
1 + 2 = 3
```

Similarly:

```text
current remainder 2 + stone remainder 1
= 3
```

So the important battle is between:

```text
remainder 1 stones
        vs
remainder 2 stones
```

Stones whose remainder is `0` do not change the remainder.

---

## Algorithm

### Step 1 — Count the three types

```java
int cnt0 = 0;
int cnt1 = 0;
int cnt2 = 0;
```

For every stone:

```java
int x = stones[i] % 3;
```

Then increment the appropriate counter.

---

### Step 2 — Handle `cnt0`

If the number of remainder-`0` stones is even:

```java
if (cnt0 % 2 == 0)
```

Alice can win when both types of non-zero remainder stones exist:

```java
cnt1 >= 1 && cnt2 >= 1
```

So:

```java
return cnt1 >= 1 && cnt2 >= 1;
```

If `cnt0` is odd, the game becomes dependent on the difference between `cnt1` and `cnt2`.

Alice wins only when the difference is greater than `2`:

```java
return (cnt1 - cnt2) > 2 || (cnt2 - cnt1) > 2;
```

---

## Java Solution

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {

        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        // Count stones according to their remainder when divided by 3
        for (int i = 0; i < stones.length; i++) {

            int x = stones[i] % 3;

            if (x == 0) {
                cnt0++;
            } 
            else if (x == 1) {
                cnt1++;
            } 
            else {
                cnt2++;
            }
        }

        // Even number of remainder-0 stones
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }

        // Odd number of remainder-0 stones
        return (cnt1 - cnt2) > 2 || (cnt2 - cnt1) > 2;
    }
}
```

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array once.

### Space Complexity

```text
O(1)
```

Only three counters are used:

```text
cnt0
cnt1
cnt2
```

---

## Important Pattern

The main trick in this problem is:

> **When the losing condition depends on divisibility by 3, group numbers by `number % 3`.**

Instead of thinking about every actual stone value:

```text
1, 2, 4, 5, 7, 8, ...
```

think only about:

```text
0, 1, 2
```

This reduces the problem to counting three categories.
