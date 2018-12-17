package com.sjtu.yifei.algorithm;

/**
 * 类描述：二叉树
 * 二叉查找树（Binary Search Tree，简称BST）是一棵二叉树，它的左子节点的值比父节点的值要小，右节点的值要比父节点的值大。它的高度决定了它的查找效率。
 * 在理想的情况下，二叉查找树增删查改的时间复杂度为O(logN)（其中N为节点数），最坏的情况下为O(N)。当它的高度为logN+1时，我们就说二叉查找树是平衡的。
 * <p>
 * 创建人：yifei
 * 创建时间：2018/12/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BSTreeTest {

    public static void main(String[] strings) {
        /**
         *                    20
         *               15        25
         *            10    18  24    30
         *         8     12
         */
        BSTree<Integer> tree = createBSTree(new int[]{20, 15, 25, 10, 18, 24, 30, 8, 12});
        if (tree != null) {
            System.out.print("先序遍历： ");
            preOrderTraverse(tree.root);
            System.out.println();

            tree.deleteNode(15);
            System.out.print("先序遍历： ");
            preOrderTraverse(tree.root);
            System.out.println();
        }
    }

    /**
     * 创建一颗查找二叉树BSTree
     * @param array
     * @return
     */
    private static BSTree<Integer> createBSTree(int[] array) {
        if (array.length > 0) {
            BSTree<Integer> tree = new BSTree<>(array[0]);//创建一棵树
            if (array.length > 1) {
                for (int i = 1; i < array.length;i ++) {
                    tree.addNode(array[i]);
                }
            }
            return tree;
        }
        return null;
    }

    private static void preOrderTraverse(BSTree.BSTreeNode<Integer> node) {
        if (node != null && node.data != null) {
            System.out.print(node.data + " ");
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    private static void midOrderTraverse(BSTree.BSTreeNode<Integer> node) {
        if (node != null && node.data != null) {
            preOrderTraverse(node.left);
            System.out.print(node.data + " ");
            preOrderTraverse(node.right);
        }
    }

    private static void lastOrderTraverse(BSTree.BSTreeNode<Integer> node) {
        if (node != null && node.data != null) {
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
            System.out.print(node.data + " ");
        }
    }

}
