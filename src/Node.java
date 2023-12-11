/**
 * Node class for Binary Search Tree
 * Includes data within the node, and pointers to the left and right children
 */
public class Node
{
    int data;
    Node left, right;

    public Node (int input)
    {
        data = input;
        left = null;
        right = null;
    }
}