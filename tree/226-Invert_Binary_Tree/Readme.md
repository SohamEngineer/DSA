# Invert Binary Tree (LeetCode 226)

## Problem Statement

Given the `root` of a binary tree, invert the tree and return its root.

Inverting a binary tree means swapping the left and right child of every node in the tree.

---

## Example

### Input

```text
        4
      /   \
     2     7
    / \   / \
   1   3 6   9
```

### Output

```text
        4
      /   \
     7     2
    / \   / \
   9   6 3   1
```

---

# Approach: Breadth-First Search (Level Order Traversal)

## Idea

Instead of using recursion, we can traverse the tree level by level using a **Queue**.

For every node:

1. Remove the node from the queue.
2. Swap its left and right children.
3. Add its left child (if it exists) to the queue.
4. Add its right child (if it exists) to the queue.

Repeat until the queue becomes empty.

---

## Algorithm

1. If the tree is empty, return `null`.
2. Create a queue and insert the root node.
3. While the queue is not empty:
   - Remove the front node.
   - Swap its left and right child.
   - Add the left child to the queue if it exists.
   - Add the right child to the queue if it exists.
4. Return the root.

---

## Java Code

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }

        return root;
    }
}
```

---

# Dry Run

### Input

```text
        4
      /   \
     2     7
    / \   / \
   1   3 6   9
```

### Step 1

Process node **4**

Swap:

```text
        4
      /   \
     7     2
```

Queue:

```text
[7, 2]
```

---

### Step 2

Process node **7**

Swap:

```text
        4
      /   \
     7     2
    / \
   9   6
```

Queue:

```text
[2, 9, 6]
```

---

### Step 3

Process node **2**

Swap:

```text
        4
      /   \
     7     2
    / \   / \
   9   6 3   1
```

Queue:

```text
[9, 6, 3, 1]
```

---

### Step 4

Nodes **9**, **6**, **3**, and **1** are leaf nodes.

Swapping their left and right children has no effect.

Queue becomes empty.

---

### Final Output

```text
        4
      /   \
     7     2
    / \   / \
   9   6 3   1
```

---

# Complexity Analysis

### Time Complexity

```text
O(n)
```

Every node is visited exactly once.

### Space Complexity

```text
O(n)
```

In the worst case, the queue stores all nodes of one level of the tree.

---

# Why BFS Works

- Every node is processed exactly once.
- Swapping children immediately does not affect future processing because both children are still added to the queue.
- The traversal continues level by level until every node has been inverted.

---

# Alternative Approach

This problem can also be solved using **Depth-First Search (DFS)** with recursion.

### DFS Recursive Complexity

| Time | Space |
|------|-------|
| O(n) | O(h) |

where **h** is the height of the tree.

---

# Complexity Comparison

| Approach | Time | Space |
|----------|------|-------|
| BFS (Queue) | O(n) | O(n) |
| DFS (Recursion) | O(n) | O(h) |

---

# Key Learning

- Use a **Queue** to perform Level Order Traversal.
- Swap the left and right child of every node.
- Every node is visited only once, making the algorithm efficient with **O(n)** time complexity.
- This is an iterative solution and avoids recursion.

---

## Topics

- Binary Tree
- Breadth-First Search (BFS)
- Level Order Traversal
- Queue
- Tree Traversal

---

**LeetCode:** 226. Invert Binary Tree