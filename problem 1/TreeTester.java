/**
 * Tests the Tree class.
 *
 * @author  Tyler Mong
 * @version 2.1
 * @since   2023-12-11
 */
public class TreeTester
{
    public static void main(String[] args)
    {
        Tree firstTree = new Tree();

        // Tests printing empty tree
        System.out.println("[Printing empty tree]");
        firstTree.print();
        firstTree.depthFirstPrint();
        firstTree.breadthFirstPrint();

        // Tests searching empty tree
        System.out.println("\n[Searching empty tree]");
        System.out.println(firstTree.search(5));
        System.out.println(firstTree.depthFirstSearch(5));
        System.out.println(firstTree.breadthFirstSearch(5));

        // Tests adding to tree, then prints it
        System.out.println("\n[Adding to tree]");
        firstTree.add(5);
        firstTree.add(3);
        firstTree.add(7);
        firstTree.add(13);
        firstTree.add(10);
        firstTree.add(1);
        firstTree.add(1);
        firstTree.add(6);
        firstTree.print();

        // Tests depth-first printing (lab)
        System.out.println("\n[Depth-first printing]");
        firstTree.depthFirstPrint();

        // Tests breadth-first printing (lab)
        System.out.println("\n[Breadth-first printing]");
        firstTree.breadthFirstPrint();

        // Tests removing from tree, then prints it
        System.out.println("\n[Removing leaf node]");
        firstTree.remove(1);
        firstTree.print();

        System.out.println("\n[Removing node with one child]");
        firstTree.remove(13);
        firstTree.print();

        System.out.println("\n[Removing node with two children]");
        firstTree.remove(7);
        firstTree.print();

        System.out.println("\n[Removing root node]");
        firstTree.remove(5);
        firstTree.print();

        // Tests searching for a node, prints true/false
        System.out.println("\n[Searching for nodes]");
        System.out.println(firstTree.search(6));
        System.out.println(firstTree.search(12));

        // Tests depth-first searching (lab)
        System.out.println("\n[Depth-first searching]");
        System.out.println(firstTree.depthFirstSearch(6));
        System.out.println(firstTree.depthFirstSearch(12));

        // Tests breadth-first searching (lab)
        System.out.println("\n[Breadth-first searching]");
        System.out.println(firstTree.breadthFirstSearch(6));
        System.out.println(firstTree.breadthFirstSearch(12));
    }
}
