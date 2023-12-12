import java.util.Queue;
import java.util.LinkedList;

/**
 * Recreated binary search tree class.
 * Includes methods for adding, removing, and searching for nodes.
 */
public class Tree
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

    // Tree Lab
    /**
     * Prints the tree using depth-first traversal
     * Uses inorder traversal: left, root, right
     */
    public void depthFirstPrint()
    {
        privateDepthFirstPrint(root);
    }

    /**
     * Prints the tree using depth-first traversal
     * @param current the current node (starts at root)
     */
    private void privateDepthFirstPrint(Node current)
    {
        // recursively traverse the left subtree, then print the current node, then recursively traverse the right subtree
        if (current != null)
        {
            privateDepthFirstPrint(current.left);
            System.out.println(current.data);
            privateDepthFirstPrint(current.right);
        }
    }

    /**
     * Prints the tree using breadth-first traversal
     */
    public void breadthFirstPrint()
    {
        privateBreadthFirstPrint(root);
    }

    /**
     * Prints the tree using breadth-first traversal
     * @param root the root node
     */
    private void privateBreadthFirstPrint(Node root)
    {
        // empty tree
        if (root == null)
            return;

        // creates a queue to store nodes, adds the root node to it
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        // while the queue is not empty, remove the first node, print it
        while (!nodeQueue.isEmpty())
        {
            Node current = nodeQueue.poll();
            System.out.println(current.data);

            // then add its children to the queue and repeat
            if (current.left != null)
                nodeQueue.add(current.left);
            if (current.right != null)
                nodeQueue.add(current.right);
        }
    }

    /**
     * Searches for a node in the tree using depth-first traversal
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    public boolean depthFirstSearch(int valueToFind)
    {
        return privateDepthFirstSearch(root, valueToFind);
    }

    /**
     * Searches for a node in the tree using depth-first traversal
     * @param current the current node (starts at root)
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    private boolean privateDepthFirstSearch(Node current, int valueToFind)
    {
        // empty tree
        if (current == null)
            return false;

        // if the value to find is equal to the current node, return true
        if (current.data == valueToFind)
            return true;
        // if the value to find is less than the current node, go left
        else if (valueToFind < current.data)
            return privateDepthFirstSearch(current.left, valueToFind);
        // if the value to find is greater than the current node, go right
        else
            return privateDepthFirstSearch(current.right, valueToFind);
    }

    /**
     * Searches for a node in the tree using breadth-first traversal
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    public boolean breadthFirstSearch(int valueToFind)
    {
        return privateBreadthFirstSearch(root, valueToFind);
    }

    /**
     * Searches for a node in the tree using breadth-first traversal
     * @param current the current node (starts at root)
     * @param valueToFind the value to find in the tree
     * @return true if the value is found, false if not
     */
    private boolean privateBreadthFirstSearch(Node current, int valueToFind)
    {
        // empty tree
        if (current == null)
            return false;

        // creates a queue to store nodes, adds the root node to it
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(current);

        // while the queue is not empty, remove the first node, check if it's the value, return true if it is
        while (!nodeQueue.isEmpty())
        {
            Node currentNode = nodeQueue.poll();
            if (currentNode.data == valueToFind)
                return true;

            // then add its children to the queue and repeat
            if (currentNode.left != null)
                nodeQueue.add(currentNode.left);
            if (currentNode.right != null)
                nodeQueue.add(currentNode.right);
        }
        return false;
    }
}
