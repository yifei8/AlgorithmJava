package com.sjtu.yifei.algorithm;

import java.util.LinkedList;

/**
 * 类描述：红黑树测试
 * 创建人：yifei
 * 创建时间：2018/12/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RBTreeTest {
    public static void main(String[] strings) {
        /**
         *                    20
         *               15        25
         *            10    18  24    30
         *         8     12
         */
        RBTree<Integer> tree = createRBTree(new int[]{20, 15, 25, 10, 18, 24, 30, 8, 12});
        if (tree != null) {
            System.out.print("先序遍历： ");
            BFSTraverse(tree.root);
            System.out.println();

//            tree.rotateLeft(tree.searchNode(15));
//
//            System.out.print("先序遍历： ");
//            BFSTraverse(tree.root);
//            System.out.println();
        }
    }

    /**
     * 创建一颗查找二叉树RBTree
     *
     * @param array
     * @return
     */
    private static RBTree<Integer> createRBTree(int[] array) {
        if (array.length > 0) {
            RBTree<Integer> tree = new RBTree<>();//创建一棵树
            for (int i = 0; i < array.length; i++) {
                tree.addNode(array[i]);
            }
            return tree;
        }
        return null;
    }

    /**
     * 深度优先遍历Depth First Search
     * 深度遍历有重要的三种方法:先序遍历（preorder），中序遍历（inorder）和后序遍历（postorder）
     *
     * @param node
     */
    private static void preOrderTraverse(RBTree.RBTreeNode<Integer> node) {
        if (node != null && node.data != null) {
            System.out.print(node.data + " ");
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    /**
     * 广度优先遍历
     *
     * @param node
     */
    private static void BFSTraverse(RBTree.RBTreeNode<Integer> node) {
        LinkedList<RBTree.RBTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            RBTree.RBTreeNode<Integer> pop = queue.pop();
            System.out.print("(" + pop.data + "," + pop.isRed + ")" + " ");
            if (pop.left != null) {
                queue.add(pop.left);
            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }

    }


}
