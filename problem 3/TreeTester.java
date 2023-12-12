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

        // Tests searching empty tree
        System.out.println("\n[Searching empty tree]");
        System.out.println(firstTree.search(5));

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

        // Tests iterating through tree
        System.out.println("\n[Iterating through tree]");
        for (Integer node : firstTree)
            System.out.println(node);

        System.out.println("\n[Adding to tree, then iterating through it]");
        firstTree.add(20);
        firstTree.add(25);
        firstTree.add(22);
        firstTree.add(24);
        for (Integer node : firstTree)
            System.out.println(node);
    }
}
