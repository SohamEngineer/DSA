# All Nodes Distance K in Binary Tree

## Problem Statement

Given the `root` of a binary tree, a `target` node, and an integer `k`, return an array of the values of all nodes that are exactly `k` distance away from the target node.

You can return the answer in **any order**.

---

## Example

### Input

```text
Root = [3,5,1,6,2,0,8,null,null,7,4]
Target = 5
K = 2
```

### Output

```text
[7,4,1]
```

### Explanation

Nodes `7`, `4`, and `1` are exactly **2 edges** away from the target node `5`.

---

## Approach

Since a binary tree only allows movement from **parent → child**, we first convert it into an **undirected graph** by storing the parent of every node.

The solution consists of two BFS traversals:

1. Traverse the tree and store every node's parent.
2. Start BFS from the target node.
3. During BFS, explore:
   - Left child
   - Right child
   - Parent
4. Maintain a visited map to prevent revisiting nodes.
5. Stop when the current level reaches `k`.
6. The remaining nodes in the queue are exactly `k` distance away.

---

## Algorithm

### Step 1: Store Parent of Every Node

- Traverse the tree using BFS.
- Store each child's parent in a HashMap.

```text
Parent Map:

Child -> Parent
```

---

### Step 2: Start BFS from Target

- Push the target node into the queue.
- Mark it as visited.

---

### Step 3: Traverse Level by Level

Repeat until the queue becomes empty:

- Process one level at a time.
- If current level equals `k`, stop.
- Visit:
  - Left child
  - Right child
  - Parent

Ignore already visited nodes.

---

### Step 4: Collect Answer

After reaching level `k`, every node remaining in the queue is at distance `k`.

Return their values.

---

## Dry Run

Consider the following tree:

```text
          3
        /   \
       5     1
      / \   / \
     6   2 0   8
        / \
       7   4
```

### Parent Mapping

```text
5 -> 3
1 -> 3
6 -> 5
2 -> 5
0 -> 1
8 -> 1
7 -> 2
4 -> 2
```

### BFS Traversal

**Level 0**

```text
Queue = [5]
```

---

**Level 1**

Visit:

- Parent → 3
- Left → 6
- Right → 2

```text
Queue = [3,6,2]
```

---

**Level 2**

Visit:

From 3 → 1

From 6 → None

From 2 → 7,4

```text
Queue = [1,7,4]
```

Since distance = **2**, stop.

Answer:

```text
[1,7,4]
```

---

## Java Solution

```java
// Paste your Java solution here
```

---

## Time Complexity

| Operation | Complexity |
|-----------|------------|
| Parent Mapping | O(N) |
| BFS Traversal | O(N) |

Overall:

```text
Time Complexity: O(N)
```

where **N** is the number of nodes.

---

## Space Complexity

| Data Structure | Complexity |
|----------------|------------|
| Parent HashMap | O(N) |
| Visited HashMap | O(N) |
| Queue | O(N) |

Overall:

```text
Space Complexity: O(N)
```

---

## Key Concepts Used

- Binary Tree
- Breadth First Search (BFS)
- HashMap
- Queue
- Graph Traversal
- Parent Mapping
- Level Order Traversal

---

## Learning

- A binary tree can be treated as an undirected graph by storing parent references.
- BFS is ideal for finding all nodes at a fixed distance.
- A visited set prevents revisiting nodes and avoids infinite loops.
- Parent mapping enables traversal in all three directions: left, right, and parent.

---

## Tags

`Binary Tree` `BFS` `Graph` `Queue` `HashMap` `LeetCode 863`