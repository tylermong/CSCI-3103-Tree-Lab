import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Recreated binary search tree class.
 * Includes methods for adding, removing, and searching for nodes.
 */
public class Tree implements Iterable<Integer>
{
    private Node root;

    public Tree()
    {
        root = null;
    }

    /**
     * Adds a node to the tree
     * @param valueToAdd the value to add to the tree
     */
    public void add(int valueToAdd)
    {
        // if the tree is empty, set the root to the new node
        if (root == null)
            root = new Node(valueToAdd);
            // otherwise, call the private add method
        else
            privateAdd(root, valueToAdd);
    }

    /**
     * Adds a node to the tree
     * @param current the current node (starts at root)
     * @param valueToAdd the value to add to the tree
     * @return the current node
     */
    private Node privateAdd(Node current, int valueToAdd)
    {
        // base case: leaf node
        if (current == null)
        {
            current = new Node(valueToAdd);
            return current;
        }

        // recursive case: not a leaf node
        // if the value to add is less than the current node, go left
        if (valueToAdd < current.data)
            current.left = privateAdd(current.left, valueToAdd);
            // if the value to add is greater than the current node, go right
        else if (valueToAdd > current.data)
            current.right = privateAdd(current.right, valueToAdd);
        // if the value to add is equal to the current node, do nothing (no duplicates)

        return current;
    }

    /**
     * Removes a node from the tree
     * @param valueToRemove the value to remove from the tree
     */
    public void remove(int valueToRemove)
    {
        root = privateRemove(root, valueToRemove);
    }

    // reference for cases: youtu.be/wcIRPqTR3Kc
    private Node privateRemove(Node current, int valueToRemove)
    {
        // base case: empty tree
        if (current == null)
            return current;

        // recursive case: not empty tree
        // if the value to remove is not equal to the current node, keep going
        if (current.data != valueToRemove)
        {
            // if the value to remove is less than the current node, go left
            if (valueToRemove < current.data)
                current.left = privateRemove(current.left, valueToRemove);
                // if the value to remove is greater than the current node, go right
            else
                current.right = privateRemove(current.right, valueToRemove);
        }
        // if the value to remove is equal to the current node
        else
        {
            // case 1: leaf node -- just delete the node
            if (current.left == null && current.right == null)
            {
                current = null;
                return current;
            }
            // case 2: one child -- replace the node with its child - point the child's parent to the child's child
            else if (current.left == null || current.right == null)
            {
                if (current.left == null)
                    current = current.right;
                else
                    current = current.left;
                return current;
            }
            // case 3: two children -- find the next biggest node (leftmost node in right subtree) -> replace the node with this
            else // if (current.left != null && current.right != null)
            {
                Node nextBiggest = current.right;
                while (nextBiggest.left != null)
                    nextBiggest = nextBiggest.left;

                current.data = nextBiggest.data;
                current.right = privateRemove(current.right, nextBiggest.data);
                return current;
            }
        }
        return current;
    }

    /**
     * Searches for a node in the tree
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    public boolean search(int valueToFind)
    {
        return privateSearch(root, valueToFind);
    }

    /**
     * Searches for a node in the tree
     * @param current the current node (starts at root)
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    private boolean privateSearch(Node current, int valueToFind)
    {
        // empty tree
        if (current == null)
            return false;

        // if the value to find is equal to the current node, return true
        if (current.data == valueToFind)
            return true;
            // if the value to find is less than the current node, go left
        else if (valueToFind < current.data)
            return privateSearch(current.left, valueToFind);
            // if the value to find is greater than the current node, go right
        else
            return privateSearch(current.right, valueToFind);
    }

    /**
     * Prints the tree
     */
    public void print()
    {
        privatePrint(root);
    }

    /**
     * Prints the tree
     * @param current the current node (starts at root)
     */
    private void privatePrint(Node current)
    {
        // recursively traverse the left subtree, then print the current node, then recursively traverse the right subtree
        if (current != null)
        {
            privatePrint(current.left);
            System.out.println(current.data);
            privatePrint(current.right);
        }
    }

    // Lab
    @Override
    public Iterator<Integer> iterator()
    {
        return new treeIterator();
    }

    /**
     * Iterator class for tree
     */
    private class treeIterator implements Iterator<Integer>
    {
        private Stack<Node> stack = new Stack<>();
        private Node current;

        /**
         * Constructor for treeIterator
         */
        public treeIterator()
        {
            current = root;
            while (current != null)
            {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty())
                current = stack.pop();
        }

        /**
         * Returns the next node in the tree
         * @return the next node in the tree
         */
        public Integer next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            Integer currentData = current.data;

            if (current.right != null)
            {
                stack.push(current);
                current = current.right;
                while (current.left != null)
                {
                    stack.push(current);
                    current = current.left;
                }
            }
            else
            {
                while(!stack.isEmpty() && stack.peek().right == current)
                {
                    current = stack.pop();
                }
                if (stack.isEmpty())
                    current = null;
                else
                    current = stack.pop();
            }
            return currentData;
        }

        /**
         * Checks to see if there is a next node in the tree
         * @return true if there is a next node, false if not
         */
        public boolean hasNext()
        {
            return current != null || !stack.isEmpty();
        }
    }
}
